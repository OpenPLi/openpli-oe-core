FILES_${PN} += "${base_libdir}/modules/${KV}/extra/hi_play.ko"

do_install_append() {
	install -d ${D}${base_libdir}/modules/${KV}/extra
	install -m 0755 ${S}/hi_play.ko ${D}${base_libdir}/modules/${KV}/extra
}
