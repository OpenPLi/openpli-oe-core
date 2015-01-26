FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
DEPENDS += "libdca"
EXTRA_OECONF := "${@bb.data.getVar('EXTRA_OECONF',d,1).replace('--disable-dts', '--enable-dts --enable-mpegdemux')}"
PACKAGECONFIG += "faac faad libmms hls dash smoothstreaming webp rtmp"
SRC_URI += "file://0001-rtmp-fix-seeking-and-potential-segfault.patch"

# Do not strip binary
#INHIBIT_PACKAGE_STRIP = "1"
#do_strip[noexec] = "1"
#do_strip="no"
#do_strip(){
#        pass
#}

