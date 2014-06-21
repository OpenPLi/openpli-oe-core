SRC_URI += "file://sanity-check-provides.patch \
	file://fix_uname_cache.patch \
	file://0001-reuse-the-installed_files-list-when-possible.patch \
	file://ifmod.patch \
	file://modprobe \
	file://opkg-wget \
	"

PR = "r5"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PACKAGES =+ "opkg-wget"
FILES_opkg-wget = "${bindir}/opkg-wget"
RDEPENDS_libopkg += "opkg-wget"

do_install_prepend() {
	install -d ${D}${datadir}/opkg/intercept
	install -m 755 ${WORKDIR}/modprobe ${D}${datadir}/opkg/intercept/
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/opkg-wget ${D}${bindir}/
}
