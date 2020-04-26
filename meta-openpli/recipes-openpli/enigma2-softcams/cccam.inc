DESCRIPTION = "CCcam ${PV} softcam"

SRC_URI = " \
	http://downloads.openpli.org/softcams/CCcam-${PV}.zip;name=softcam \
	http://downloads.openpli.org/softcams/CCcam.xml;name=ppanel \
	"

SRC_URI[ppanel.md5sum] = "6d9c1964e0f65278b8f3024772b69d13"
SRC_URI[ppanel.sha256sum] = "bd4e0b2b088a4a977d431ae1bb85fae142020f7b6f88baba06f9757f1b1cd9e2"

CAMNAME = "CCcam${CCCAMVER}"

S = "${WORKDIR}"

require softcam.inc

RDEPENDS_${PN} += "libxcrypt-compat"

INHIBIT_PACKAGE_STRIP = "1"

CONFFILES = "${sysconfdir}/CCcam.cfg"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/CCcam.${TARGET_ARCH} ${D}${bindir}/${CAMNAME}
	install -d ${D}${sysconfdir}
	install -m 0644 ${S}/CCcam.cfg ${D}${sysconfdir}/CCcam.cfg
	install -d ${D}${sysconfdir}/ppanels
	install -m 0644 ${WORKDIR}/CCcam.xml ${D}${sysconfdir}/ppanels/CCcam.xml
}
