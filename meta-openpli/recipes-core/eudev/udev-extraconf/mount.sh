#!/bin/sh
#
# Called from udev
#
# Attempt to mount any added block devices and umount any removed devices


MOUNT="/bin/mount"
PMOUNT="/usr/bin/pmount"
UMOUNT="/bin/umount"
for line in `grep -h -v ^# /etc/udev/mount.blacklist /etc/udev/mount.blacklist.d/*`
do
	if [ ` expr match "$DEVNAME" "$line" ` -gt 0 ];
	then
		logger "udev/mount.sh" "[$DEVNAME] is blacklisted, ignoring"
		exit 0
	fi
done

automount() {
	name="`basename "$DEVNAME"`"
	bus="`basename "$ID_BUS"`"

	# Figure out a mount point to use
	LABEL=${ID_FS_LABEL}

	if [[ -z "${LABEL}" ]]; then
		udevadm info /dev/$name | grep -q 'mmc'
		mmc=$?
		if [ "${mmc}" -eq "0" ]; then
			if [ ! -d "/media/mmc" ]; then
				LABEL="mmc"
			else
				LABEL="$name"
			fi
		elif [ "${bus}" == "ata" ]; then
			udevadm info /dev/$name | grep -q 'ahci\|pci\|sata'
			internal=$?
			if [ "${internal}" -eq "0" ]; then
				LABEL="hdd"
			elif [ ! -d "/media/usbhdd" ]; then
				LABEL="usbhdd"
			else
				LABEL="$name"
			fi
		else
			LABEL="$name"
		fi
	fi
	! test -d "/media/$LABEL" && mkdir -p "/media/$LABEL"
	# Silent util-linux's version of mounting auto
	if [ "x`readlink $MOUNT`" = "x/bin/mount.util-linux" ] ;
	then
		MOUNT="$MOUNT -o silent"
	fi

	# If filesystem type is vfat, change the ownership group to 'disk', and
	# grant it with  w/r/x permissions.
	case $ID_FS_TYPE in
	vfat|fat)
		MOUNT="$MOUNT -o umask=007,gid=`awk -F':' '/^disk/{print $3}' /etc/group`"
		;;
	# TODO
	*)
		;;
	esac

	if ! $MOUNT -t auto $DEVNAME "/media/$LABEL"
	then
		#logger "mount.sh/automount" "$MOUNT -t auto $DEVNAME \"/media/$LABEL\" failed!"
		rm_dir "/media/$LABEL"
	else
		logger "mount.sh/automount" "Auto-mount of [/media/$LABEL] successful"
		touch "/tmp/.automount-$LABEL"
	fi
}

rm_dir() {
	# We do not want to rm -r populated directories
	if test "`find "$1" | wc -l | tr -d " "`" -lt 2 -a -d "$1"
	then
		! test -z "$1" && rm -r "$1"
	else
		logger "mount.sh/automount" "Not removing non-empty directory [$1]"
	fi
}

# No ID_FS_TYPE for cdrom device, yet it should be mounted
name="`basename "$DEVNAME"`"
[ -e /sys/block/$name/device/media ] && media_type=`cat /sys/block/$name/device/media`

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ] && [ -n "$ID_FS_TYPE" -o "$media_type" = "cdrom" ]; then
	if [ -x "$PMOUNT" ]; then
		$PMOUNT $DEVNAME 2> /dev/null
	elif [ -x $MOUNT ]; then
		$MOUNT $DEVNAME 2> /dev/null
	fi

	# If the device isn't mounted at this point, it isn't
	# configured in fstab (note the root filesystem can show up as
	# /dev/root in /proc/mounts, so check the device number too)
	if expr $MAJOR "*" 256 + $MINOR != `stat -c %d /`; then
		grep -q "^$DEVNAME " /proc/mounts || automount
	fi
fi


if [ "$ACTION" = "remove" ] || [ "$ACTION" = "change" ] && [ -x "$UMOUNT" ] && [ -n "$DEVNAME" ]; then
	for mnt in `cat /proc/mounts | grep "$DEVNAME" | cut -f 2 -d " " `
	do
		$UMOUNT $mnt
	done

	# Remove empty directories from auto-mounter
	name="`basename "$DEVNAME"`"
	test -e "/tmp/.automount-$name" && rm_dir "/media/$name"
	test -e "/tmp/.automount-$LABEL" && rm_dir "/media/$LABEL"
	test -e "/tmp/.automount-mmc" && rm_dir "/media/mmc"
	test -e "/tmp/.automount-hdd" && rm_dir "/media/hdd"
	test -e "/tmp/.automount-usbhdd" && rm_dir "/media/usbhdd"
fi
