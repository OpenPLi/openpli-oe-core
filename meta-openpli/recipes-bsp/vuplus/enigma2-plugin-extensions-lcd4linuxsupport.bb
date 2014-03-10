DESCRIPTION = "Vu+ LCD4LinuxSupport plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_DATE = "20130723_p0"

PR = "${SRC_DATE}_r0"

SRC_URI = " \
	http://archive.vuplus.com/openpli-support/vuplus-lcd4linuxsupport-plugin_${SRC_DATE}.tar.gz \
	"

RDEPENDS_${PN} = "enigma2 png-util"

S = "${WORKDIR}/vuplus-lcd4linuxsupport-plugin"

do_install() {
        install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linuxSupport
        install -m 0600 ${S}/*.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linuxSupport

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_${PN} = "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[md5sum] = "17fb241d1dbc0b44620f199a750fb55a"
SRC_URI[sha256sum] = "3bc485eddc5bb1d952320dff18cf2f819e058aaf6bf3f02319b732122a97f5bc"

