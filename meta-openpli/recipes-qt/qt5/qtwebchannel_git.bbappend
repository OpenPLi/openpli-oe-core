# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

inherit pkgconfig

INSANE_SKIP_${PN}-qmlplugins += "file-rdeps"
