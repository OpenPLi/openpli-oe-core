SUMMARY = "Compatibility for packages that link to older libudev0"

require conf/license/license-gplv2.inc

RDEPENDS_${PN} = "libudev"

do_install_append() {
	install -d ${D}${base_libdir}
	ln -sf libudev.so.1 ${D}${base_libdir}/libudev.so.0
}

FILES_${PN} = "${base_libdir}"
