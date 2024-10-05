FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "1.24.6"

SRC_URI[sha256sum] = "64342060d7c6f9e36a35e3be38a4f5ac3b41ed93b0853619be45141ef3cc1b9d"

SRC_URI:append = " file://001-revert-use-new-gst-adapter-get-buffer.patch"
