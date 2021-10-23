# NOTE: this should be in the conf files in the respective BSP layers

# meta-edision
SRC_URI_append_osmega = " file://egl/kodi-old-gl-headers.patch"

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

# for some devices: for libmali.so libEGL.so and libGLESv2.so no providers found
# in RDEPENDS_kodi? [file-rdeps]
#
INSANE_SKIP_${PN}_gbtrio4k += "file-rdeps"
#
INSANE_SKIP_${PN}_sf8008 += "file-rdeps"
INSANE_SKIP_${PN}_sf8008m += "file-rdeps"
#
INSANE_SKIP_${PN}_dual += "file-rdeps"
#
INSANE_SKIP_${PN}_ustym4kpro += "file-rdeps"
#
INSANE_SKIP_${PN}_h7 += "file-rdeps"
INSANE_SKIP_${PN}_h9 += "file-rdeps"
INSANE_SKIP_${PN}_h9combo += "file-rdeps"
INSANE_SKIP_${PN}_h9combose += "file-rdeps"
INSANE_SKIP_${PN}_h9se += "file-rdeps"
INSANE_SKIP_${PN}_h10 += "file-rdeps"
INSANE_SKIP_${PN}_h11 += "file-rdeps"
INSANE_SKIP_${PN}_i55plus += "file-rdeps"
INSANE_SKIP_${PN}_i55se += "file-rdeps"
