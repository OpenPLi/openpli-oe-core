LICENSE = "CLOSED"

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

SRCREV = "8ca606176939fdb7e6fa562ef6c2230750e0647f"

SRC_URI = "git://github.com/icesat/Enigma2-settings-13.0E-19.2E-23.5E-28.2E.git"
S = "${WORKDIR}/git"

inherit gitpkgv allarch

FILES_${PN} = "/etc/enigma2/"

do_install () {
	install -d ${D}/etc/enigma2
	cp -r ${S}/${SETTINGS_FILENAME}/* ${D}/etc/enigma2
}
