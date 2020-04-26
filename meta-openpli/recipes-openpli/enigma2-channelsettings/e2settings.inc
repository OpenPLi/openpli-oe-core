INHIBIT_PACKAGE_STRIP = "1"

python do_remove_tarball() {
	import os
	try:
		os.remove(bb.data.expand("${DL_DIR}/${SETTINGS_FILENAME}.tar.gz.md5", d))
		os.remove(bb.data.expand("${DL_DIR}/${SETTINGS_FILENAME}.tar.gz", d))
	except:
		pass
}

addtask remove_tarball before do_fetch

do_install() {
	install -d ${D}${sysconfdir}/enigma2
	install -m 0644 ${S}${sysconfdir}/enigma2/bouquets.* ${D}${sysconfdir}/enigma2/
	install -m 0644 ${S}${sysconfdir}/enigma2/userbouquet.* ${D}${sysconfdir}/enigma2/
	install -m 0644 ${S}${sysconfdir}/enigma2/lamedb ${D}${sysconfdir}/enigma2/
}

FILES_${PN} = "/"

inherit allarch
