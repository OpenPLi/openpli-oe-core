# NOTE: this should be in the conf files in the respective BSP layers

# meta-abcom

# meta-axasuhd
MACHINE_FEATURES_append_e4hd = " v3d-cortexa15"

# meta-edision
MACHINE_FEATURES_append_osmini4k    = " edison-cortexa15"
MACHINE_FEATURES_append_osmio4k     = " edison-cortexa15"
MACHINE_FEATURES_append_osmio4kplus = " edison-cortexa15"
MACHINE_FEATURES_append_osmega      = " xcore-mipsel"
SRC_URI_append_osmega            = " file://egl/kodi-old-gl-headers.patch"

# meta-gfutures
MACHINE_FEATURES_append_bre2ze4k = " v3d-cortexa15"
MACHINE_FEATURES_append_hd2400   = " v3d-mipsel"
MACHINE_FEATURES_append_hd51     = " v3d-cortexa15"
MACHINE_FEATURES_append_vs1500   = " v3d-cortexa15"

# meta-gi
MACHINE_FEATURES_append_et1x000 = " nextv-cortexa15"

# meta-gigablue
MACHINE_FEATURES_append_gbquad4k = " GB-cortexa15"
MACHINE_FEATURES_append_gbue4k   = " GB-cortexa15"
SRC_URI_append_gbquad4k          = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_gbue4k            = " file://egl/kodi-old-gl-headers.patch"
DEPENDS_append_gbquad4k          = " gigablue-kodi-gbquad4k"
DEPENDS_append_gbue4k            = " gigablue-kodi-gbue4k"
SRC_URI_append_gbtrio4k          = " file://egl/kodi-old-gl-headers.patch"

# meta-maxytec

# meta-octagon
SRC_URI_append_sf8008         = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_sf8008m        = " file://egl/kodi-old-gl-headers.patch"

# meta-qviart
MACHINE_FEATURES_append_lunix4k = " nextv-cortexa15"

# meta-uclan
SRC_URI_append_ustym4kpro         = " file://egl/kodi-old-gl-headers.patch"

# meta-vuplus
MACHINE_FEATURES_append_vusolo2    = " vuplus-mipsel"
MACHINE_FEATURES_append_vuduo2     = " vuplus-mipsel"
MACHINE_FEATURES_append_vusolose   = " vuplus-mipsel"
MACHINE_FEATURES_append_vusolo4k   = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuultimo4k = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuuno4k    = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuuno4kse  = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuzero4k   = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuduo4k    = " vuplus-cortexa15"
MACHINE_FEATURES_append_vuduo4kse  = " vuplus-cortexa15"

SRC_URI_append_vuduo4k     = " file://vuplus-fix-exception-duo4k.patch"
SRC_URI_append_vuduo4kse   = " file://vuplus-fix-exception-duo4k.patch"

DEPENDS_append_vusolo2     = " vuplus-kodi-vusolo2"
DEPENDS_append_vuduo2      = " vuplus-kodi-vuduo2"
DEPENDS_append_vusolose    = " vuplus-kodi-vusolose"
DEPENDS_append_vusolo4k    = " vuplus-kodi-vusolo4k"
DEPENDS_append_vuultimo4k  = " vuplus-kodi-vuultimo4k"
DEPENDS_append_vuuno4k     = " vuplus-kodi-vuuno4k"
DEPENDS_append_vuuno4kse   = " vuplus-kodi-vuuno4kse"
DEPENDS_append_vuzero4k    = " vuplus-kodi-vuzero4k"
DEPENDS_append_vuduo4k     = " vuplus-kodi-vuduo4k"
DEPENDS_append_vuduo4kse   = " vuplus-kodi-vuduo4kse"

RDEPENDS_${PN}_append_vusolo2     = " vuplus-kodi-vusolo2"
RDEPENDS_${PN}_append_vuduo2      = " vuplus-kodi-vuduo2"
RDEPENDS_${PN}_append_vusolose    = " vuplus-kodi-vusolose"
RDEPENDS_${PN}_append_vusolo4k    = " vuplus-kodi-vusolo4k"
RDEPENDS_${PN}_append_vuultimo4k  = " vuplus-kodi-vuultimo4k"
RDEPENDS_${PN}_append_vuuno4k     = " vuplus-kodi-vuuno4k"
RDEPENDS_${PN}_append_vuuno4kse   = " vuplus-kodi-vuuno4kse"
RDEPENDS_${PN}_append_vuzero4k    = " vuplus-kodi-vuzero4k"
RDEPENDS_${PN}_append_vuduo4k     = " vuplus-kodi-vuduo4k"
RDEPENDS_${PN}_append_vuduo4kse   = " vuplus-kodi-vuduo4kse"

# meta-xtrend
MACHINE_FEATURES_append_et10000   = " v3d-mipsel"

# meta-zgemma
MACHINE_FEATURES_append_h7     = " v3d-cortexa15"
#

# for some devices: for libmali.so libEGL.so and libGLESv2.so no providers found
# in RDEPENDS_kodi? [file-rdeps]
#
INSANE_SKIP_${PN}_gbtrio4k += "file-rdeps"
#
INSANE_SKIP_${PN}_sf8008 += "file-rdeps"
INSANE_SKIP_${PN}_sf8008m += "file-rdeps"
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
