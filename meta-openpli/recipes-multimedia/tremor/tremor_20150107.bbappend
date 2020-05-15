# svn / git repo server has been unresponsive for a while

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://tremor.tar.gz \
           file://obsolete_automake_macros.patch;striplevel=0 \
           file://tremor-arm-thumb2.patch \
"