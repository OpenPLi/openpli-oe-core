PRINC = "1"

do_install_append () {
	for i in ext2 ext3 ext4 ext4dev; do
		rm ${D}${base_sbindir}/fsck.${i} || /bin/true;
		rm ${D}${base_sbindir}/mkfs.${i} || /bin/true;
		ln -sf e2fsck ${D}${base_sbindir}/fsck.${i}
		ln -sf mke2fs ${D}${base_sbindir}/mkfs.${i}
	done;
}
