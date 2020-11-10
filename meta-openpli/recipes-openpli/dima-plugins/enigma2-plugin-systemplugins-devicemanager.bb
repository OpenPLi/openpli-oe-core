DESCRIPTION = "Device manager for storage devices (format/change partitions and type/fast & fixed mount and umount)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=fa931694f4579578b39587f7ca837042"

PLUGINNAME = "enigma2-plugin-systemplugins-devicemanager"

require dima-plugins.inc

RRECOMMENDS_${PN} = "e2fsprogs-e2fsck e2fsprogs-mke2fs ntfsprogs hddtemp dosfstools e2fsprogs util-linux-sfdisk fuse-exfat exfat-utils"
