inherit allarch

LICENSE = "CLOSED"
PACKAGES = "${PN}"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
FILES:${PN} += "${base_libdir}/firmware"
