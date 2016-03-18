RSUGGESTS_${PN} = ""

PACKAGECONFIG ="gpl mp3lame x264 vpx"

EXTRA_OECONF_append = " --disable-mipsdspr1 --disable-mipsdspr2 "
