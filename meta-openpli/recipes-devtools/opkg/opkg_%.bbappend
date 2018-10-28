FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append := " \
	file://modprobe \
	file://0002-opkg-symlink-to-directory-workarounds.patch \
"

do_install_prepend() {
	install -d ${D}${datadir}/opkg/intercept
	install -m 755 ${WORKDIR}/modprobe ${D}${datadir}/opkg/intercept/
	install -d ${D}${bindir}
}
