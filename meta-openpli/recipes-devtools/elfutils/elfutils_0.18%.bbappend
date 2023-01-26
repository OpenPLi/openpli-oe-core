FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " file://0001-debuginfod-debuginfod-client.c-correct-string-format.patch"
