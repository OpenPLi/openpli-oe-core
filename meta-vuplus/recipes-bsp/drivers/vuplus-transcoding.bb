DESCRIPTION = "VU+ transcoding support"
SUMMARY = "Support for transcoding live streams and files on VU+ receivers"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504"

PV = "2.0"
PKGV = "${PV}"
PR = "1"

PACKAGE_ARCH := "${MACHINE_ARCH}"

RDEPENDS_${PN} = "vuplus-filestreamproxy vuplus-livestreamproxy"
RREPLACES_${PN} = "enigma2-transtreamproxy-util enigma2-plugin-systemplugins-transcodingsetup"
RCONFLICTS_${PN} = "enigma2-transtreamproxy-util enigma2-plugin-systemplugins-transcodingsetup"

file = "transcoding-setup.sh"
destdir = "/etc/init.d"

SRC_URI = "file://${file} \
		   file://LICENSE.GPLv3"

FILES_${PN} = "${destdir}/${file}"
CONFFILES_${PN} = "${destdir}/${file}"

S = "${WORKDIR}"

# NOTE: this should probably be done by inheriting update-rc.d.bbclass

do_install() {
	install -d "${D}/${destdir}"
	install -m 0644 "${S}/${file}" "${D}/${destdir}/"
}

pkg_postinst_${PN} () {
	rm -rf /etc/rc?.d/[SK]*transcoding-setup.sh

	for i in 2 3 4 5
	do
		ln -s /etc/init.d/transcoding-setup.sh /etc/rc${i}.d/S90transcoding-setup.sh
	done
}

pkg_postrm_${PN} () {
	rm -rf /etc/rc?.d/[SK]*transcoding-setup.sh
}
