FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PACKAGECONFIG[cdparanoia]     = "--enable-cdparanoia,--disable-cdparanoia,cdparanoia"
PACKAGECONFIG += "cdparanoia"
EXTRA_OECONF := "${@bb.data.getVar('EXTRA_OECONF',d,1).replace('--disable-cdparanoia', '--enable-cdparanoia')}"
SRC_URI += "file://0001-riff-media-added-fourcc-to-all-mpeg4-video-caps.patch"

# Do not strip binary
#INHIBIT_PACKAGE_STRIP = "1"
#do_strip[noexec] = "1"
#do_strip="no"
#do_strip(){
#        pass
#}

