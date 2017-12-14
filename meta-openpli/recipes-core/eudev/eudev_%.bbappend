FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " file://98-dbox-devices.rules"

do_install_append() {
	install -d "${D}/lib/udev/rules.d"
	install -m 0644 "${WORKDIR}/98-dbox-devices.rules" "${D}/lib/udev/rules.d"
}

FILES_${PN} += " ${sysconfdir}/udev/rules.d/98-dbox-devices.rules "
