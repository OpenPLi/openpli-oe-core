#!/bin/sh

[ ! -e "/dev/block/by-name" ] && mkdir -p /dev/block/by-name
ln -sf ${DEVNAME} /dev/block/by-name/${PARTNAME}

