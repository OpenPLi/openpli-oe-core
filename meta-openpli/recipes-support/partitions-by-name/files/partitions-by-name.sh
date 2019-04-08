#!/bin/sh
search=/sys/block/mmcblk0/mmcblk0p*
for i in $search;
do
  if [ "$i" != "$search" ]; then
    partname=`cat /$i/uevent | grep PARTNAME | cut -d '=' -f 2`
    devname=`cat /$i/uevent | grep DEVNAME | cut -d '=' -f 2`
    mkdir -p /dev/block/by-name/
    ln -sf /dev/$devname /dev/block/by-name/$partname
  fi
done