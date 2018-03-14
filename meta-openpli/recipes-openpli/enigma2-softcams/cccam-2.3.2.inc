DESCRIPTION = "CCcam ${PV} softcam"

SRC_URI = "http://downloads.pli-images.org/softcams/CCcam-${PV}.zip"

CAMNAME = "CCcam_2.3.2"

S = "${WORKDIR}"

require softcam.inc

INHIBIT_PACKAGE_STRIP = "1"

CONFFILES = "/etc/CCcam.cfg"

do_install() {
        install -d ${D}/usr/bin
        install -m 0755 ${S}/CCcam.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
        install -d ${D}/etc
        install -m 0644 ${S}/CCcam.cfg ${D}/etc/CCcam.cfg
	install -d ${D}/etc/ppanels
	install -m 0644 ${WORKDIR}/CCcam.xml ${D}/etc/ppanels/CCcam.xml
}
