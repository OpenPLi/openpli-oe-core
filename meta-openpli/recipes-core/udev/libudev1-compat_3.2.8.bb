SUMMARY = "Compatibility for packages that link to older libudev0"

require conf/license/license-gplv2.inc

RDEPENDS:${PN} = "libudev"

do_install:append() {
	install -d ${D}${base_libdir}
	ln -sf libudev.so.1 ${D}${base_libdir}/libudev.so.0
}

FILES:${PN} = "${base_libdir}"
