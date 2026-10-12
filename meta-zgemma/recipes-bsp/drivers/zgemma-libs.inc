DESCRIPTION = "${MACHINE} libraries"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PR = "${SRCDATE}"
VER ?= "${@bb.utils.contains('TARGET_ARCH', 'aarch64', '64' , '', d)}"

SRC_URI = "http://downloads.openpli.org/archive/zgemma/zgemma-libs${VER}-${HICHIPSET}-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

# Disable anything auto depending on zgemma-libs
EXCLUDE_FROM_SHLIBS = "1"

# The vendor bundle is excluded from shared-library dependency generation.
# Declare the system libraries used by its prebuilt ELF files explicitly.
RDEPENDS:${PN} += "glibc libgcc libstdc++ zlib libcurl"

# The driver is a set of binary libraries to install
# there's nothing to configure or compile
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}${libdir}/hisilicon
	install -m 0755 ${S}/hisilicon/* ${D}${libdir}/hisilicon
	install -m 0755 ${S}/ffmpeg/* ${D}${libdir}/hisilicon
	if [ "${GLIBCVERSION}" = "2.25" ]; then
		install -m 0755 ${S}/glibc/* ${D}${libdir}/hisilicon
	else
		ln -sf /lib/ld-linux-armhf.so.3 ${D}${libdir}/hisilicon/ld-linux.so
	fi
}

FILES:${PN} = "${libdir}/hisilicon/*"
FILES:${PN}-dev = ""

INSANE_SKIP:${PN} += "ldflags already-stripped dev-so 32bit-time"
