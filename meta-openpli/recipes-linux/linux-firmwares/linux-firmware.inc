inherit allarch

LICENSE = "CLOSED"
PACKAGES = "${PN}"
PACKAGE_ARCH = "all"

SRC_URI = "git://github.com/BjornLee/linux-firmware.git;protocol=git"
S = "${WORKDIR}/git"
FILES_${PN} += "${base_libdir}/firmware"

PR = "r2"
