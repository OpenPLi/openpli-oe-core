FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append := " \
	file://modprobe \
	file://0002-symlinks-can-be-valid-directories-too.patch \
"

do_install_prepend() {
	install -d ${D}${datadir}/opkg/intercept
	install -m 755 ${WORKDIR}/modprobe ${D}${datadir}/opkg/intercept/
	install -d ${D}${bindir}
}
