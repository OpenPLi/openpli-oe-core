FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append += " file://0001-debuginfod-debuginfod-client.c-correct-string-format.patch"
