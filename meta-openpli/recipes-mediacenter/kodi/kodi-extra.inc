# NOTE: this should be in the conf files in the respective BSP layers

# meta-edision
#SRC_URI:append:osmega = " file://egl/kodi-old-gl-headers.patch"
SRC_URI:append:osmio4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI:append:osmio4kplus = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"
SRC_URI:append:osmini4k = " file://0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch"

# meta-gigablue
#SRC_URI:append:gbquad4k = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI:append:gbue4k = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI:append:gbtrio4k = " file://egl/kodi-old-gl-headers.patch"

# meta-octagon
#SRC_URI:append:sf8008 = " file://egl/kodi-old-gl-headers.patch"
#SRC_URI:append:sf8008m = " file://egl/kodi-old-gl-headers.patch"

# meta-uclan
SRC_URI:append:ustym4kpro = " file://egl/kodi-old-gl-headers.patch"

# meta-vuplus
SRC_URI:append:vuduo4k = " file://vuplus-fix-exception-duo4k.patch"
SRC_URI:append:vuduo4kse = " file://vuplus-fix-exception-duo4k.patch"
