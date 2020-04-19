#!/bin/sh

log() {
	# comment to enable logging
	return

	if [ $# -eq 1 ]; then
		echo "$1" >> /home/root/mdev.log
	else
		echo "$MDEV: $1 $2" >> /home/root/mdev.log
	fi
}

notify() {
	# we don't really depend on the hotplug_e2_helper, but when it exists, call it
	if [ -x /usr/bin/hotplug_e2_helper ]; then
		/usr/bin/hotplug_e2_helper $ACTION /block/$MDEV $PHYSDEVPATH
	fi
}

get_blkid() {
	# run the result of blkid as a shell command
	eval `blkid /dev/${MDEV} | grep ${MDEV} | cut -d ':' -f 2`
	log ">" "device TYPE = $TYPE"
	log ">" "device UUID = $UUID"
	log ">" "device LABEL = $LABEL"
}

current_mountpoint() {
	# local variables
	local mountpoint

	# locate MDEV in /proc/mounts
	mountpoint=`grep $MDEV /proc/mounts | cut -d ' ' -f2`

	echo $mountpoint
}

fstab_mountpoint() {
	# local variables
	local mountpoint

	# check based on device
	mountpoint=`grep "^/dev/$MDEV\s" /etc/fstab | awk '{print $2}'`
	if [ -z "$mountpoint" ]; then
		# check based on label
		mountpoint=`grep "^LABEL=$LABEL\s" /etc/fstab | awk '{print $2}'`
		if [ -z "$mountpoint" ]; then
			# check based on UUID
			mountpoint=`grep "^UUID=$UUID\s" /etc/fstab | awk '{print $2}'`
		fi
	fi

	echo $mountpoint
}

create_mountpoint() {
	# local variables
	local mountpoint
	local removable
	local external
	local devicetype

	# first, check if our device is defined in fstab
	mountpoint=${fstab_mountpoint}

	# and if not
	if [ -z "$mountpoint" ]; then

		# and we have no label
		if [ -z "${LABEL}" ]; then
			# internal non removable device?
			removable=`cat /sys/block/$DEVBASE/removable`
			readlink -fn /sys/block/$DEVBASE/device | grep -qs 'pci\|ahci\|sata'
			external=$?
			if [ "${external}" -eq "0" ]; then
				# we assume it's the internal harddisk
				devicetype="hdd"
			else
				# mount mmc block devices on /media/mcc
				if [ ${DEVBASE:0:6} = "mmcblk" ]; then
					devicetype="mmc"
				else
					if [ "$MODEL" == "USB CF Reader   " ]; then
						devicetype="cf"
					elif [ "$MODEL" == "Compact Flash   " ]; then
						devicetype="cf"
					elif [ "$MODEL" == "USB SD Reader   " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "USB SD  Reader  " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "SD/MMC          " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "USB MS Reader   " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "SM/xD-Picture   " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "USB SM Reader   " ]; then
						devicetype="mmc"
					elif [ "$MODEL" == "MS/MS-Pro       " ]; then
						devicetype="mmc"
					else
						devicetype="usb"
					fi
				fi
			fi
		else
			devicetype="${LABEL}"
		fi
		log ">" "DEVICETYPE = $devicetype"

		mountpoint="/media/$devicetype"
	fi

	# return the determined mountpoint
	echo $mountpoint
}

samba_share() {
	# bail out if samba is not installed
	if [ ! -f /etc/init.d/samba.sh ]; then
		log "!" "samba is not installed, no share created"
		return
	fi

    # process the parameters
	local path=$1
	local mountpoint=`basename $1`
	local model=$2
	local share

	# some mountpoint name exceptions
	if [ "$mountpoint" == "hdd" ]; then
		mountpoint="Harddisk"
	fi

	log ">" "$ACTION SAMBA share for $mountpoint"

	# process the add/remove request
	if [ "$ACTION" == "add" ]; then
		# check if we already have a share for this path
		share=`find /etc/samba -name "*.conf" -exec grep "path\s*=\s*${path}" {} \;`
		if [ -z "$share" ]; then
			# do have a share template?
			if [ -f /etc/samba/shares/share.template ]; then
				# generate a share config for this mountpoint
				echo "[$mountpoint]" > /etc/samba/shares/${mountpoint}.conf
				echo "  comment = $model" >> /etc/samba/shares/${mountpoint}.conf
				echo "  path = $path" >> /etc/samba/shares/${mountpoint}.conf
				cat /etc/samba/shares/share.template >> /etc/samba/shares/${mountpoint}.conf
				log ">" "share for $path created"
			else
				log "!" "share creation failed, share template missing!"
				return
			fi
		else
			log "!" "share for $path already exists"
			return
		fi

	elif [ "$ACTION" == "remove" ]; then
		if [ -f /etc/samba/shares/${mountpoint}.conf ]; then
			rm /etc/samba/shares/${mountpoint}.conf
		fi
	else
		# unknown command, bail out
		return
	fi

	# do we have samba running?
	pidof -s smbd
	if [ $? -eq 0 ]; then
		# restart samba in the background
		log ">" "background restart of samba"
		/etc/init.d/samba.sh restart &
	fi
}

log "-------[ $ACTION $MDEV ] -------------"

case "$ACTION" in
	add|"")
		# make sure we have an action set
		[ -z "$ACTION" ] && ACTION="add"

		# blacklist boot device
		BOOTDEV=$(cat /proc/cmdline | sed -e 's/^.*root=\/dev\///' -e 's/ .*$//')
		log ">" "BOOTDEV = $BOOTDEV"
		if [ "$MDEV" == "$BOOTDEV" ]; then
			log "!" "exit, boot device is blacklisted"
			exit 0
		fi

		# blacklist partitions on the same device as the boot device
		DEVBASE=${MDEV:0:7}
		log ">" "DEVBASE = $DEVBASE"
		if [[ $BOOTDEV == $DEVBASE* ]]; then
			log "!" "exit, boot device partition blacklisted"
			exit 0
		fi

		# get the device base (f.e. sd[a-z] or mmcblk[0-9])
		if [ ! -d /sys/block/${DEVBASE} ]; then
			DEVBASE=${MDEV:0:3}
			log ">" "DEVBASE = $DEVBASE"
		else
			# if MDEV is a partion on the boot device, skip it
			if [ "$DEVBASE" == "${BOOTDEV:0:7}" ]; then
				log "!" "exit, $MDEV is a partiton on $BOOTDEV"
				exit 0
			fi
		fi

		# check for "please don't mount it" file
		if [ -f "/dev/nomount.${DEVBASE}" ]; then
			# blocked
			log "!" "exit, due to a no-mount flag for $DEVBASE"
			exit 0
		fi

		# check for full-disk partition
		if [ "${DEVBASE}" == "${MDEV}" ]; then
			if [ -d /sys/block/${DEVBASE}/${DEVBASE}*1 ]; then
				# Partition detected, just tell and quit
				notify
				log "!" "exit, device has partitions"
				exit 0
			fi
			if [ ! -f /sys/block/${DEVBASE}/size ]; then
				# No size at all
				log "!" "exit, device has no size"
				exit 0
			fi
			if [ `cat /sys/block/${DEVBASE}/size` == 0 ]; then
				# empty device, bail out
				log "!" "exit, empty device"
				exit 0
			fi
		fi

		# get device information of the current MDEV
		get_blkid

		if [ -z "$TYPE" ]; then
			notify
			log "!" "exit, device type missing"
			exit 0

		# if this is a swap device, activate swap space
		elif [ "$TYPE" == "swap" ]; then
			if ! grep -q "^/dev/${MDEV} " /proc/swaps ; then
				swapon /dev/${MDEV}
			fi
			log "!" "exit, swap device"
			exit 0
		fi

		# get the device model
		if [ -f /sys/block/$DEVBASE/device/model ]; then
			MODEL=`cat /sys/block/$DEVBASE/device/model`
		else
			MODEL="unknown device"
		fi
		log ">" "MODEL = $MODEL"

		# first allow fstab to determine the mountpoint
		if ! mount /dev/$MDEV > /dev/null 2>&1; then
			log "!" "device could not be mounted"

			# did if fault because it was mounted through fstab?
			MOUNTPOINT=$(fstab_mountpoint)
			if [ ! -z "$MOUNTPOINT" ]; then
				# Already mounted
				samba_share "$MOUNTPOINT" "$MODEL"
				log "!" "exit, device was already mounted outside mdev"
				exit 0
			fi

			# create a mountpoint and mount the device
			MOUNTPOINT=$(create_mountpoint)
			log ">" "MOUNTPOINT = $MOUNTPOINT"

			# Use mkdir as 'atomic' action, failure means someone beat us to the punch
			if ! mkdir "${MOUNTPOINT}" ; then
				MOUNTPOINT="/media/$MDEV"
				log ">" "alternative MOUNTPOINT = $MOUNTPOINT"
				mkdir -p "${MOUNTPOINT}"
			fi

			# mount the device on the mountpoint
			MOUNTOPTIONS="noatime"
			# work around an ext4 issue on 4.1 kernels
			if [ ${TYPE} == "ext4" ]; then
				case $(uname -r) in *4.1*)
					MOUNTOPTIONS="${MOUNTOPTIONS},nodelalloc"
					;;
				esac
			fi
			if ! mount -t auto -o "${MOUNTOPTIONS}" /dev/$MDEV "$MOUNTPOINT" ; then
				rmdir "$MOUNTPOINT"
				log "!" "mount failed, mountpoint removed again"
			elif [ -d "$MOUNTPOINT/bin" -a -d "$MOUNTPOINT/etc" -a -d "$MOUNTPOINT/lib" -a -d "$MOUNTPOINT/usr" ]; then
				umount "$MOUNTPOINT"
				rmdir "$MOUNTPOINT"
				log "!" "don't mount Linux rootfs"
			else
				samba_share "$MOUNTPOINT" "$MODEL"
			fi
		else
			# mounted via fstab definitions
			MOUNTPOINT=$(create_mountpoint)
			samba_share "$MOUNTPOINT" "$MODEL"
		fi
		;;
	remove)
		# find the device's current mountpoint
		MOUNTPOINT=$(current_mountpoint)

		# bail out if none found
		if [ -z "$MOUNTPOINT" ]; then
			log "!" "no mountpoint found for $MDEV ?"
		fi

		# get device information of the current MDEV
		get_blkid

		# only remove the samba shares for dynamically mounted devices
		FSTAB=$(fstab_mountpoint)
		if [ -z "${FSTAB}" ]; then
			samba_share "$MOUNTPOINT" ""
		fi

		# unmount the device and remove the mountpoint
		log ">" "MOUNTPOINT = $MOUNTPOINT"
		umount -l "$MOUNTPOINT" || umount -l "/dev/${MDEV}"
		rmdir "$MOUNTPOINT"
		;;
	*)
		# Unexpected keyword
		exit 1
		;;
esac

notify

log ""
