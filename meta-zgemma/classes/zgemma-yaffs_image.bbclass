inherit image_types zgemma-legacy-deploy

# Migrate the unowned bootoptions file left by earlier image builds.
ZGEMMA_LEGACY_DEPLOY_FILES = "${MACHINE}/bootoptions.yaffs"
do_image_complete[prefuncs] += "zgemma_cleanup_legacy_deploy"
do_image_complete_setscene[prefuncs] += "zgemma_cleanup_legacy_deploy"

ZGEMMA_YAFFS_PAGE_SIZE ?= "4k"

IMAGE_TYPEDEP:ubi += "zgemmayaffs"

do_image_zgemmayaffs[depends] = " \
	e2fsprogs-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	mtools-native:do_populate_sysroot \
	mkyaffs2utils-native:do_populate_sysroot \
"

IMAGE_CMD:zgemmayaffs () {
	mkdir -p ${WORKDIR}/bootoptions
	echo "imageurl https://raw.githubusercontent.com/oe-alliance/bootmenu/master/${MACHINE}/images" > ${WORKDIR}/bootoptions/bootmenu.conf
	echo "# " >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "iface eth0" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "dhcp yes" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "# " >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "# for static config leave out 'dhcp yes' and add the following settings:" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "# " >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "#ip 192.168.1.10" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "#netmask 255.255.255.0" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "#gateway 192.168.1.1" >> ${WORKDIR}/bootoptions/bootmenu.conf
	echo "#dns 192.168.1.1" >> ${WORKDIR}/bootoptions/bootmenu.conf
	mkyaffs ${WORKDIR}/bootoptions/ ${WORKDIR}/bootoptions.yaffs ${ZGEMMA_YAFFS_PAGE_SIZE}
	mkdir -p ${IMGDEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}
	cp ${WORKDIR}/bootoptions.yaffs ${IMGDEPLOYDIR}/zgemma-partitions-${MACHINE}/${MACHINE}/bootoptions.yaffs
}
