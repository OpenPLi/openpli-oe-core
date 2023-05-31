# NOTE: this should be in the conf files in the respective BSP layers

# meta-edision
SRC_URI_append_osmega = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_osmio4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI_append_osmio4kplus = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI_append_osmini4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"

# meta-gigablue
SRC_URI_append_gbquad4k = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_gbue4k = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_gbtrio4k = " file://egl/kodi-old-gl-headers.patch"

# meta-octagon
SRC_URI_append_sf8008 = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_sf8008m = " file://egl/kodi-old-gl-headers.patch"

# meta-uclan
SRC_URI_append_ustym4kpro = " file://egl/kodi-old-gl-headers.patch"

# meta-vuplus
SRC_URI_append_vuduo4k = " file://vuplus-fix-exception-duo4k.patch"
SRC_URI_append_vuduo4kse = " file://vuplus-fix-exception-duo4k.patch"
