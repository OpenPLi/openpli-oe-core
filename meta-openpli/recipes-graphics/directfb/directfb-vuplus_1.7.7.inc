GFXPR ?= ""

GFXPR_vusolo4k = "20160310.r0"

SRC_URI[gfxdriver.vusolo4k.md5sum] = "8b11fb5d296c79f881c13f443d738cbf"
SRC_URI[gfxdriver.vusolo4k.sha256sum] = "42832063c22b0e669d25747139eaf858cbba2196b2d83c6d0378fa5a0bb62a0d"

GFXPR_vuultimo4k = "20160524.r0"

SRC_URI[gfxdriver.vuultimo4k.md5sum] = "872bd359ab6b585e75b3dcb5f818640a"
SRC_URI[gfxdriver.vuultimo4k.sha256sum] = "dda575c986010a1411399431b82039090f641a56aa9ee7700ca05e60c41f6d22"

GFXPR_vuuno4k = "20160525.r0"

SRC_URI[gfxdriver.vuuno4k.md5sum] = "8d47ad05f4af32e9bfc8cd36543c4941"
SRC_URI[gfxdriver.vuuno4k.sha256sum] = "e164f190f6396e492cce19dc107a38711e69486ac1ddf6ee784be80d28d8770a"

GFXPR_vuuno4kse = "20170831.r4"

SRC_URI[gfxdriver.vuuno4kse.md5sum] = "a2ed9ee48a6cc959d4223a24a35d2287"
SRC_URI[gfxdriver.vuuno4kse.sha256sum] = "6dd4c7c15afbda75d1ce0f6807b1e607388eb13dd78425dfb18720573100819e"

GFX_FILE = "directfb_1.7-7_gfxdriver_${MACHINE}_${GFXPR}.tar.gz"

DEPENDS_append_class-target += "libgles-${MACHINE}"

SRC_URI_append_class-target += " \
	file://vuplus-conf.patch \
	file://vuplus-prevent-VT.patch \
	file://disable_linux_input_patch.diff \
	http://archive.vuplus.com/download/build_support/vuplus/${GFX_FILE};name=gfxdriver.${MACHINE} \
"

EXTRA_OECONF_append_class-target += "\
	--with-inputdrivers=linuxinput \
	--disable-devmem \
	--disable-video4linux \
	--without-tools \
"

PRIVATE_LIBS_${PN}_append_class-target = "\
	${@bb.utils.contains("MACHINE_FEATURES", "dvbproxy", "libinit.so", "", d)} \
"

do_install_append_class-target() {
	install -d ${D}/etc
	install -d ${D}/usr/lib/directfb-${RV}/systems
	install -d ${D}/usr/lib/directfb-${RV}/gfxdrivers

	install -m 0644 ${WORKDIR}/directfb-vuplus/etc/directfbrc ${D}/etc/directfbrc
	cp -r --preserve=mode,links ${WORKDIR}/directfb-vuplus/usr/lib/directfb-${RV}/systems/* ${D}/usr/lib/directfb-${RV}/systems/
	rm -f ${D}/usr/lib/directfb-${RV}/systems/libdirectfb_dummy.so
	rm -f ${D}/usr/lib/directfb-${RV}/systems/libdirectfb_fbdev.so
	install -m 0755 ${WORKDIR}/directfb-vuplus/usr/lib/directfb-${RV}/gfxdrivers/* ${D}/usr/lib/directfb-${RV}/gfxdrivers/

	cp -r --preserve=mode,links ${WORKDIR}/directfb-vuplus/usr/lib/libinit* ${D}/usr/lib/
}

INSANE_SKIP_${PN}_append_class-target += "dev-so"

FILES_${PN}_append_class-target += "\
	/etc/directfbrc \
	/usr/lib/libinit* \
	/usr/lib/directfb-${RV}/systems/libdirectfb_dvb_proxy_sys* \
	/usr/lib/directfb-${RV}/gfxdrivers/* \
"

FILES_${PN}-dbg_append_class-target += "\
	/usr/lib/.debug/libinit* \
	/usr/lib/directfb-${RV}/systems/.debug/* \
	/usr/lib/directfb-${RV}/gfxdrivers/.debug/* \
"

BBCLASSEXTEND = "native"
PACKAGE_ARCH := "${MACHINE_ARCH}"
