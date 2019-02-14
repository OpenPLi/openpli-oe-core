DESCRIPTION = "mgcamd ${PV} softcam"

SRC_URI = " \
	http://downloads.openpli.org/softcams/mgcamd-${PV}.tar.gz;name=softcam \
	"

CAMNAME = "mgcamd${MGCAMVER}"

S = "${WORKDIR}"

require softcam.inc

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} += "/usr/keys"

CONFFILES = "/usr/keys/mg_cfg /usr/keys/ignore.list /usr/keys/newcamd.list /usr/keys/priority.list /usr/keys/replace.list"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/mgcamd.${TARGET_ARCH} ${D}/usr/bin/${CAMNAME}
	install -d ${D}/usr/keys
	install -m 0644 ${S}/mg_cfg ${D}/usr/keys/mg_cfg
	install -m 0644 ${S}/ignore.list.example ${D}/usr/keys/ignore.list.example
	install -m 0644 ${S}/newcamd.list.example ${D}/usr/keys/newcamd.list.example
	install -m 0644 ${S}/priority.list.example ${D}/usr/keys/priority.list.example
	install -m 0644 ${S}/replace.list.example ${D}/usr/keys/replace.list.example
	touch ${D}/usr/keys/ignore.list
	touch ${D}/usr/keys/newcamd.list
	touch ${D}/usr/keys/priority.list
	touch ${D}/usr/keys/replace.list
}
