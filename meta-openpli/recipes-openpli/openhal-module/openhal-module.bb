SUMMARY = "OpenHAL information module for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

# Use something here as we use VISIONVERSION and VISIONREVISION
# Maybe date would be good?
#PV = "${DATE}"
#PR = "${DATE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/OpenEnigma2/openhal-module.git;protocol=git"

S = "${WORKDIR}/git/source/openhal"

inherit module gitpkgv

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_BUILDDIR}"

do_configure_prepend(){
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@MACHINE@|${MACHINE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@BOX_BRAND@|${BOX_BRAND}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@STB_PLATFORM@|${STB_PLATFORM}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@DISTRO_FEED_URI@|${DISTRO_FEED_URI}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@DISTRO_NAME@|${DISTRO_NAME}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@BUILD_VERSION@|${BUILD_VERSION}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@KERNELVERSION@|${KERNELVERSION}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@PREFERRED_VERSION_python@|${PREFERRED_VERSION_python}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@PREFERRED_PROVIDER_virtual/enigma2-mediaservice@|${PREFERRED_PROVIDER_virtual/enigma2-mediaservice}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_MULTILIB@|${HAVE_MULTILIB}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@DEFAULTTUNE@|${DEFAULTTUNE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@SOC_FAMILY@|${SOC_FAMILY}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@BLINDSCAN_BINARY@|${BLINDSCAN_BINARY}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@RCTYPE@|${RCTYPE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@RCNAME@|${RCNAME}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@RCIDNUM@|${RCIDNUM}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@IMAGEDIR@|${IMAGEDIR}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@IMAGE_FSTYPES@|${IMAGE_FSTYPES}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@MTD_BOOTFS@|${MTD_BOOTFS}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@MTD_ROOTFS@|${MTD_ROOTFS}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@MTD_KERNEL@|${MTD_KERNEL}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@ROOTFS_FILE@|${ROOTFS_FILE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@KERNEL_FILE@|${KERNEL_FILE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@MKUBIFS_ARGS@|${MKUBIFS_ARGS}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@UBINIZE_ARGS@|${UBINIZE_ARGS}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@DATE@|${DATE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@TARGET_FPU@|${TARGET_FPU}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@DISPLAY_TYPE@|${DISPLAY_TYPE}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_TRANSCODING@|${HAVE_TRANSCODING}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_MULTITRANSCODING@|${HAVE_MULTITRANSCODING}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_HDMI@|${HAVE_HDMI}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_YUV@|${HAVE_YUV}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_RCA@|${HAVE_RCA}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_AV_JACK@|${HAVE_AV_JACK}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_SCART@|${HAVE_SCART}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_DVI@|${HAVE_DVI}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_SVIDEO@|${HAVE_SVIDEO}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_HDMI_IN_HD@|${HAVE_HDMI_IN_HD}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_HDMI_IN_FHD@|${HAVE_HDMI_IN_FHD}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_WOL@|${HAVE_WOL}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_CI@|${HAVE_CI}|g"
	find ${S}/ -type f -name "*.c" | xargs -r -L1 sed -i "s|@HAVE_FHDSKIN@|${HAVE_FHDSKIN}|g"
}

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C "${STAGING_KERNEL_BUILDDIR}" M="${S}" modules
}

do_configure[nostamp] = "1"
do_install[vardepsexclude] += "DATE"

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/openhal
	install -m 0644 ${S}/openhal.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/openhal/
	install -d ${D}${sysconfdir}/modules-load.d
	echo "openhal" > ${D}${sysconfdir}/modules-load.d/zzzzopenhal.conf
}

FILES_${PN} += "${sysconfdir}"
