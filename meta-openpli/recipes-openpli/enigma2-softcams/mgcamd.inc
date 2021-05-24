DESCRIPTION = "mgcamd ${PV} softcam"

SRC_URI = " \
	http://downloads.openpli.org/softcams/mgcamd-${PV}.tar.gz;name=softcam \
	"

CAMNAME = "mgcamd${MGCAMVER}"

S = "${WORKDIR}"

require mgcamd-softcam.inc

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} += "${prefix}/keys"

CONFFILES = "${prefix}/keys/mg_cfg ${prefix}/keys/ignore.list ${prefix}/keys/newcamd.list ${prefix}/keys/priority.list ${prefix}/keys/replace.list"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/mgcamd.${TARGET_ARCH} ${D}${bindir}/${CAMNAME}
	install -d ${D}${prefix}/keys
	install -m 0644 ${S}/mg_cfg ${D}${prefix}/keys/mg_cfg
	install -m 0644 ${S}/ignore.list.example ${D}${prefix}/keys/ignore.list.example
	install -m 0644 ${S}/newcamd.list.example ${D}${prefix}/keys/newcamd.list.example
	install -m 0644 ${S}/priority.list.example ${D}${prefix}/keys/priority.list.example
	install -m 0644 ${S}/replace.list.example ${D}${prefix}/keys/replace.list.example
	touch ${D}${prefix}/keys/ignore.list
	touch ${D}${prefix}/keys/newcamd.list
	touch ${D}${prefix}/keys/priority.list
	touch ${D}${prefix}/keys/replace.list
}

# If no cam selected yet, install and start this cam (and don't start it on the build host).
pkg_postinst_${PN}_append () {
if [ -f /lib/ld-2.30.so ]; then
	ln -s /lib/ld-2.30.so /lib/ld-linux.so.3
elif [ -f /lib/ld-2.28.so ]; then
	ln -s /lib/ld-2.28.so /lib/ld-linux.so.3
elif [ -f /lib/ld-2.26.so ]; then
	ln -s /lib/ld-2.26.so /lib/ld-linux.so.3
elif [ -f /lib/ld-2.25.so ]; then
	ln -s /lib/ld-2.25.so /lib/ld-linux.so.3
fi
}

# Stop this cam (if running), and move softlink to None if we're the current cam
pkg_prerm_${PN}_prepend () {
if [ -L /lib/ld-linux.so.3 ]; then
	rm -f /lib/ld-linux.so.3
fi
}
