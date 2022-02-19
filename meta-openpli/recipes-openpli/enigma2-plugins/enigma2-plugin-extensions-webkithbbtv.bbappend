# fix crash, hopefully
do_configure_prepend () {
    sed 's/reader.doDump()/#reader.doDump()/g' -i ${S}/webkit-hbbtv-plugin/WebkitHbbTV/plugin.py
}

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://port-to-python3.patch"
