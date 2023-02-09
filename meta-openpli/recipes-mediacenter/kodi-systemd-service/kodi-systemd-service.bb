SUMMARY = "Kodi Media Center systemd service"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://kodi.service"


SYSTEMD_SERVICE:${PN} = "kodi.service"
SYSTEMD_AUTO_ENABLE:${PN} = "${KODISYSTEMDAUTOSTART}"

FILES:${PN} = "${systemd_unitdir}/system"

do_install() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/kodi.service ${D}${systemd_unitdir}/system/
}
RDEPENDS:${PN} += "xinit"
