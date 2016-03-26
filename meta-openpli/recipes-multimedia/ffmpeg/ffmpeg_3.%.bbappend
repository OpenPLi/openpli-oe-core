RSUGGESTS_${PN} = ""

PACKAGECONFIG ="gpl mp3lame x264 vpx"

EXTRA_OECONF_append = " --disable-mipsdsp --disable-mipsdspr2 "
