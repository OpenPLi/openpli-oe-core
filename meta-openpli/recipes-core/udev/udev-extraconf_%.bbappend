FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://mount.blacklist.append"

do_install_append() {
	cat "${WORKDIR}/mount.blacklist.append" >> "${D}${sysconfdir}/udev/mount.blacklist"
}
