#!/bin/sh

[ ! -e "/dev/block/by-name" ] && mkdir -p /dev/block/by-name
ln -sf ${DEVNAME} /dev/block/by-name/${PARTNAME}

if [ -e "/dev/block/by-name/flag" ]; then
    if [ ! -e /dev/block/by-name/bootoptions ]; then
      ln -sf /boot/ /dev/block/by-name/bootoptions
    fi
fi
