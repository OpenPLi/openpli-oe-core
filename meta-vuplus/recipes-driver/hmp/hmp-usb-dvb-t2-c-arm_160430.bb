SUMMARY = "media tree drivers for hmp-usb-dvb-t2-c"
HOMEPAGE = "http://linuxtv.org/"
SECTION = "kernel/modules"
LICENSE = "GPL-2.0-only"

COMPATIBLE_MACHINE = "^(vuzero4k|vuuno4kse|vuduo4k|vuduo4klite)$"

SRC_URI[md5sum] = "66990bd8b8aa3759d764552a7ccac013"
SRC_URI[sha256sum] = "b3c612d792834d14c981c400022ec923c154e10161121cf730a09b4ed4e35b04"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

DEPENDS = "virtual/kernel module-init-tools perl"
KERNEL_VERSION = "${@base_read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"

do_fetch[depends] += "virtual/kernel:do_package_write_ipk"

inherit module

PROVIDES += "hmp-usb-dvb-t2-c-arm"
RPROVIDES:${PN} = "kernel-module-dvb-usb-dvbsky-${KERNEL_VERSION} \
                   kernel-module-dvbsky-m88rs6000-${KERNEL_VERSION} \
                   kernel-module-dvbsky-m88ds3103-${KERNEL_VERSION} \
                   kernel-module-sit2fe-${KERNEL_VERSION} \
                   kernel-module-dvb-usb-v2-media-tree-${KERNEL_VERSION} \
                    "
SRCDATE = "160430"
SRCDATE_VER = "${SRCDATE}"
SRCDATE_BIN = "150322"

PV = "V${SRCDATE_VER}"
PR = "r1"

PACKAGES = "${PN} \
            ${PN}-dev \
            ${PN}-dbg \
            "
FILES:${PN} = " \
	/lib/firmware/dvb-fe-ds300x.fw \
	/lib/firmware/dvb-fe-ds3103.fw \
	/lib/firmware/dvb-fe-rs6000.fw \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/dvbsky_m88ds3103.ko \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/dvbsky_m88rs6000.ko \
	/lib/modules/*/kernel/drivers/media/dvb-frontends/sit2fe.ko \
	/lib/modules/*/kernel/drivers/media/usb/dvb-usb-v2/dvb_usb_v2_media_tree.ko \
	/lib/modules/*/kernel/drivers/media/usb/dvb-usb-v2/dvb-usb-dvbsky.ko \
	"

FILES:${PN}-dev = " \
	/lib/modules/*/modules.* \
	/lib/firmware/* \
       "
SRC_URI = "file://media_build-bst-${SRCDATE}.tar.gz \
           file://vu_kernel_4.1.20.patch \
           file://defconfig \
           file://sit2_op.o_${SRCDATE_BIN}_arm \
"

S = "${WORKDIR}/media_build-bst-${SRCDATE}"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} OUTDIR=${STAGING_KERNEL_BUILDDIR}"

do_configure:prepend() {
	CUR=`pwd`
	cp ${WORKDIR}/sit2_op.o_${SRCDATE_BIN}_arm ${S}/v4l/sit2_op.o
	tar -xzf ${S}/dvbsky-firmware.tar.gz
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
	cd $CUR
}

do_configure() {
	install -m 0644 ${WORKDIR}/defconfig ${S}/v4l/.config
}

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake KDIR="${STAGING_KERNEL_DIR}" DIR="${STAGING_KERNEL_DIR}" ${MAKE_TARGETS}
	arm-oe-linux-gnueabi-strip --strip-debug ${S}/v4l/sit2fe.ko
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DIR="${STAGING_KERNEL_BUILDDIR}" DESTDIR="${D}" install
	rm -rf ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/media/pci
	install -m 0644 ${S}/v4l/sit2fe.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/media/dvb-frontends/
	install -d 0644 ${D}${base_libdir}/firmware
	install -m 0644 ${S}/dvbsky-firmware/*.fw ${D}${base_libdir}/firmware/
}

pkg_postinst:${PN} () {
	depmod -a
}

FILESEXTRAPATHS:prepend := "${THISDIR}/hmp-usb-dvb-t2-c-v04arm:"
