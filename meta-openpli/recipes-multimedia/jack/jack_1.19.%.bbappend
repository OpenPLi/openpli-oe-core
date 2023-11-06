FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://0001-Remove-usage-of-U-mode-bit-for-opening-files-in-pyth.patch"
