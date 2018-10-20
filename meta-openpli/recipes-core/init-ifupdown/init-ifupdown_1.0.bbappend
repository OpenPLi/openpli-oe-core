FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# override the default interfaces config
SRC_URI_append += "\
    file://interfaces \
"
