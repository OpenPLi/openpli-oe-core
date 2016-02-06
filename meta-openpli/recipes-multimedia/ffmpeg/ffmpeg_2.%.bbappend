RSUGGESTS_${PN} = ""

DEPENDS = "alsa-lib zlib libogg yasm-native"

PACKAGECONFIG ="gpl mp3lame x264 vpx"

EXTRA_OECONF_append = " --disable-mipsdspr1 --disable-mipsdspr2 "