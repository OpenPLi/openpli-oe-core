#! /bin/sh
#
# umountfs	Turn off swap and unmount all local filesystems.
#

PATH=/sbin:/bin:/usr/sbin:/usr/bin

echo "[umountfs] Deactivating swap..."
swapoff -a

# only runlevel 0 (stop) check spin down hdd (removable 0).
REMOVABLE="1"
RUNLEVEL="$(runlevel | awk {'print $2'})"
if [ "$RUNLEVEL" = "0" ] ; then
	for D in /sys/block/sd*; do [ `cat $D/removable` == "0" ] && REMOVABLE="0" ; done
fi

# We leave /proc mounted.
echo "[umountfs] Unmounting local filesystems..."
grep -q /mnt/ram /proc/mounts && mount -o remount,ro /mnt/ram
mount -o remount,ro /

# sync to flush pending writes for loop-mounted file system.
sync

umount -f -a -r > /dev/null 2>&1

# Add an extra delay of 3 sec to make sure even a problematic HDD has enough time to go to park-position.
if [ "$REMOVABLE" = "0" ]; then
	sdparm --command=stop /dev/sd?
	echo "[umountfs] spin down HDD before power off..."
	sleep 3
fi

echo "[umountfs] Good Bye..."

exit 0
