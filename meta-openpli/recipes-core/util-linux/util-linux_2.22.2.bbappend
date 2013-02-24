PR = "r1"

PACKAGES =+ "\
            util-linux-libmount util-linux-libmount-dev \
            "

FILES_util-linux-libmount = "${base_libdir}/libmount.so.*"
FILES_util-linux-libmount-dev = "${base_libdir}/libmount.so ${base_libdir}/libmount.la ${includedir}/libmount ${libdir}/pkgconfig/mount.pc"
