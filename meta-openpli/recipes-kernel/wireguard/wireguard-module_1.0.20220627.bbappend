FILESEXTRAPATHS:prepend := "${THISDIR}/wireguard-module:"

SRC_URI:append = " \
    file://0001-fix-fallback-macro-missing.patch \
    "
