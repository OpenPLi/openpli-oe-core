FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
			file://mount_single_uuid.patch \
			file://busybox-cron \
"

# we do not really depend on mtd-utils, but as mtd-utils replaces 
# include/mtd/* we cannot build in parallel with mtd-utils
DEPENDS += "mtd-utils"

PACKAGES =+ "${PN}-inetd"
INITSCRIPT_PACKAGES += "${PN}-inetd"
INITSCRIPT_NAME:${PN}-inetd = "inetd.${BPN}" 
CONFFILES:${PN}-inetd = "${sysconfdir}/inetd.conf"
FILES:${PN}-inetd = "${sysconfdir}/init.d/inetd.${BPN} ${sysconfdir}/inetd.conf"
RDEPENDS:${PN}-inetd += "${PN}"

RRECOMMENDS:${PN} += "${PN}-inetd"

PACKAGES =+ "${PN}-cron"
INITSCRIPT_PACKAGES += "${PN}-cron"
INITSCRIPT_NAME:${PN}-cron = "${BPN}-cron" 
FILES:${PN}-cron = "${sysconfdir}/cron ${sysconfdir}/init.d/${BPN}-cron"
RDEPENDS:${PN}-cron += "${PN}"

pkg_postinst:${PN}:append () {
	update-alternatives --install /bin/sh sh /bin/busybox.nosuid 50
}

pkg_prerm:${PN}:append () {
	ln -s ${base_bindir}/busybox $tmpdir/wget
}

do_install:append() {
	if grep -q "CONFIG_CRONTAB=y" ${WORKDIR}/defconfig; then
		install -d ${D}${localstatedir}/spool/cron/crontabs
	fi
	sed -i "/[/][s][h]*$/d" ${D}${sysconfdir}/busybox.links.nosuid
}
