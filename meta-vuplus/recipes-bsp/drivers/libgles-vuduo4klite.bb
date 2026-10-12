SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

PROVIDES = "libgles virtual/libgles2 virtual/egl"
DEPENDS = "libgles-headers patchelf-native"

RPROVIDES:${PN} = "libgles virtual-libgles2 virtual-egl libnexus.so libnxclient.so libnxpl.so libv3ddriver.so libGLESv2.so libEGL.so"


SRCDATE = "20250326.r0"

PV = "${SRCDATE}"
PR = "r2"

SRC_URI = "https://code.vuplus.de/download/release/libgles/libgles-${MACHINE}-${SRCDATE}.zip"

SRC_URI[md5sum] = "5a1caaadaf1dd6842200d86de406dab0"
SRC_URI[sha256sum] = "1b2ce9d5457292f2c704020bad1427dba15774c879e83365b9af3cdcb461ba15"

S = "${WORKDIR}"

do_configure() {
}

do_compile() {
}

do_install:append() {
	install -d ${D}${libdir}
	install -m 0755 ${S}/v3ddriver/libnxpl.so ${D}${libdir}/
	install -m 0755 ${S}/v3ddriver/libv3ddriver.so ${D}${libdir}/
	install -m 0755 ${S}/v3ddriver/libnexus.so ${D}${libdir}/
	install -m 0755 ${S}/v3ddriver/libnxclient.so ${D}${libdir}/
	patchelf --set-soname libv3ddriver.so ${D}${libdir}/libv3ddriver.so
	ln -s libv3ddriver.so ${D}${libdir}/libEGL.so
	ln -s libv3ddriver.so ${D}${libdir}/libGLESv2.so
	# vu webkit compatibility
	#ln -s libnexus.so ${D}${libdir}/libdvb_base.so
	#ln -s libnxclient.so ${D}${libdir}/libdvb_client.so
}

FILES:${PN} = "/usr/lib/*"
FILES:${PN}-dev = "/usr/include/*"

INSANE_SKIP = "32bit-time"
INSANE_SKIP:${PN} += "already-stripped dev-so ldflags"
