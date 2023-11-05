FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://init \
	"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hddtemp
}
