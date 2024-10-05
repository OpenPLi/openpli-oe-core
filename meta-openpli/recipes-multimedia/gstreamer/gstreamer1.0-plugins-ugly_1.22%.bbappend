PACKAGECONFIG = "${GSTREAMER_ORC} a52dec mpeg2dec cdio" 

PV = "1.24.6"

SRC_URI[sha256sum] = "5dfdbb2978ab282e637255cd875cf5b7b93fc83db62f8a0b1af02c85c84a75c4"

PACKAGECONFIG[amrnb]    = ""
PACKAGECONFIG[amrwb]    = ""
