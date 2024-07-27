do_install:prepend:libjpeg () {
	rm -rf ${PKGDATA_DIR}/runtime-reverse/libjpeg-dev
	rm -rf ${PKGDATA_DIR}/runtime-reverse/libjpeg-dbg
}
