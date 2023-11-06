SUMMARY = "Kodi FluxFM Plugin."
HOMEPAGE = "https://www.facebook.com/groups/hskde/"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/MarkusVolk/fluxfm.git;branch=master;protocol=https"
SRCREV = "53d8d7aa6d5f39e71c69ee0a7da96da75e1c5ea7"
S = "${WORKDIR}/git/${KODIADDONNAME}"
PV = "1.0.12"

RDEPENDS:${PN} = "python3"

KODIADDONNAME = "plugin.audio.fluxfm"
KODIADDONDIR = "${datadir}/kodi/addons"

do_compile() {
	:
}

do_install() {
	install -d ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m644 ${S}/addon.xml ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m644 ${S}/icon.png ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m644 ${S}/fanart.jpg ${D}${KODIADDONDIR}/${KODIADDONNAME}
	install -m755 ${S}/addon.py ${D}${KODIADDONDIR}/${KODIADDONNAME}
	cp -rf ${S}/resources ${D}${KODIADDONDIR}/${KODIADDONNAME}
}

FILES:${PN} = "${KODIADDONDIR}"
INSANE_SKIP:${PN} += "file-rdeps"
