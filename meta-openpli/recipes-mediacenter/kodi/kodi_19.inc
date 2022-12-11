FILESEXTRAPATHS_prepend := "${THISDIR}/kodi-19:"

# NOTE: this should be in the conf files in the respective BSP layers

# meta-edision
#SRC_URI_append_osmega = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_osmio4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI_append_osmio4kplus = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI_append_osmini4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"

# meta-gigablue
#SRC_URI_append_gbquad4k = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI_append_gbue4k = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI_append_gbtrio4k = " file://egl/kodi-old-gl-headers.patch"

# meta-octagon
#SRC_URI_append_sf8008 = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI_append_sf8008m = " file://egl/kodi-old-gl-headers.patch"

# meta-uclan

# meta-vuplus
SRC_URI_append_vuduo4k = " file://vuplus-fix-exception-duo4k.patch"
SRC_URI_append_vuduo4kse = " file://vuplus-fix-exception-duo4k.patch"


PV = "19.4+gitr${SRCPV}"
KODIURI ?= "git://github.com/xbmc/xbmc.git;branch=Matrix;protocol=https \
"

KODIWORKDIR ?= "${WORKDIR}/git"

KODIPATCHES ?= ""
KODIADDONS ?= ""
SRC_URI = "${KODIURI} ${KODIPATCHES} ${KODIADDONS}"
SRCREV = "813a194c4e8d3f9bcfd4c18b04be0fd596036f84"

S = "${KODIWORKDIR}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM ?= "file:///${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"
