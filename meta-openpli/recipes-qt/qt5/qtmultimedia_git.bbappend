# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

inherit pkgconfig

INSANE_SKIP_${PN} += "file-rdeps"
INSANE_SKIP_${PN}-plugins += "file-rdeps"
