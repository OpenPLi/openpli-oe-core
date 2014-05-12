DESCRIPTION = "Vu+ LCD4Linux plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_DATE = "20130723_p0"

PR = "${SRC_DATE}_r0"

SRC_URI = " \
	http://archive.vuplus.com/openpli-support/vuplus-lcd4linux-plugin_${SRC_DATE}.tar.gz;name=plugin \
	http://archive.vuplus.com/openpli-support/vuplus-lcd4linux-wetter_${SRC_DATE}.tar.gz;name=wetter \
	file://enigma2-plugin-extensions-lcd4linux_20130723.patch;striplevel=1;apply=yes \
	"

RDEPENDS_${PN} = "enigma2 lcd4linux python-codecs python-datetime python-imaging python-textutils python-shell python-ctypes python-pyusb"

S = "${WORKDIR}/LCD4linux"

do_install() {
        install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux
        install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/wetter
        install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/locale/de/LC_MESSAGES
        install -m 0600 ${S}/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux
        install -m 0600 ${S}/refreshrate ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux
        install -m 0600 ${S}/locale/de/LC_MESSAGES/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/locale/de/LC_MESSAGES
        install -m 0600 ${S}/wetter/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/LCD4linux/wetter

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_${PN} = "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[plugin.md5sum] = "9511f6ed80c5e26e9a2592a14aaecf13"
SRC_URI[plugin.sha256sum] = "2a36e7b4fecf221e6c37ea102072ae9a5c706ff3225dce93409cf10c04605799"

SRC_URI[wetter.md5sum] = "76869edf4f95aaa8a67395112e7b82f5"
SRC_URI[wetter.sha256sum] = "8da674774c85e9b9fc9db1bf63d159f9f06b2292882a279d581c6440937ba4a4"

