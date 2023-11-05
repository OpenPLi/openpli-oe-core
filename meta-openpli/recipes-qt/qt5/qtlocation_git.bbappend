# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

INSANE_SKIP_${PN} += "file-rdeps"

FILESEXTRAPATHS:prepend := "${THISDIR}/qtlocation-git:"
