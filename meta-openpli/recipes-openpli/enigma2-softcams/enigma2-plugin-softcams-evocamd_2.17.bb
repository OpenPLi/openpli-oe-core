CAMNAME = "evocamd"
DESCRIPTION = "evocamd ${PV} softcam"

SRC_URI = "http://downloads.openpli.org/softcams/evocamd-${PV}.zip"

S = "${WORKDIR}/evocamd-${PV}"

INHIBIT_PACKAGE_STRIP = "1"

require softcam.inc

CONFFILES = "${prefix}/keys/feynman.cfg ${prefix}/keys/ignore.list ${prefix}/keys/priority.list ${prefix}/keys/replace.list"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/evocamd.mips ${D}${bindir}/${CAMNAME}
	install -d ${D}${prefix}/keys
	install -m 0644 ${S}/camd_cfg ${D}${prefix}/keys/camd_cfg
	install -m 0644 ${S}/feynman.cfg ${D}${prefix}/keys/feynman.cfg
	install -m 0644 ${S}/ignore.list ${D}${prefix}/keys/ignore.list
	install -m 0644 ${S}/priority.list ${D}${prefix}/keys/priority.list
	install -m 0644 ${S}/replace.list ${D}${prefix}/keys/replace.list
}

FILES_${PN} += "${prefix}/keys"

SRC_URI[md5sum] = "74972fae77137f91b014b9cf4b8da137"
SRC_URI[sha256sum] = "cc4b190afc8ecbb4664cf297c6298638e28c55e92b381e631dc3729c45bdada6"
