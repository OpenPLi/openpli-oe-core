HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/hostapd-0.7.3/COPYING;md5=c54ce9345727175ff66d17b67ff51f58"
DEPENDS = "libnl openssl"

inherit update-rc.d
INITSCRIPT_NAME="hostapd"

do_configure() {
	install -m 0644 ${WORKDIR}/defconfig ${S}/.config
}

do_compile() {
	make
}

do_install() {
	install -d ${D}${sbindir} ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/hostapd ${D}${sbindir}
	install -m 0755 ${S}/hostapd_cli ${D}${sbindir}
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hostapd
}

