FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"
