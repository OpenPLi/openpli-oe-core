FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PACKAGECONFIG[amrnb]     = "--enable-amrnb,--disable-amrnb,opencore-amr"
PACKAGECONFIG[amrwb]     = "--enable-amrwb,--disable-amrwb,opencore-amr"
PACKAGECONFIG += "cdio dvdread amrnb amrwb mad"
EXTRA_OECONF := "${@bb.data.getVar('EXTRA_OECONF',d,1).replace('--disable-amrnb', '--enable-amrnb').replace('--disable-amrwb', '--enable-amrwb')}"
DEPENDS += "libmad"

# Do not strip binary
#INHIBIT_PACKAGE_STRIP = "1"
#do_strip[noexec] = "1"
#do_strip="no"
#do_strip(){
#        pass
#}

