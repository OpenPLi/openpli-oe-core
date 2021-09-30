# NOTE: this should be in the conf files in the respective BSP layers
# 
# EXTRA_OECMAKE_append_pn-kodi = " -DWITH_PLATFORM=x"

# meta-abcom
EXTRA_OECMAKE_append_pulse4k      = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_pulse4kmini  = " -DWITH_PLATFORM=mali-cortexa15"

# meta-axasuhd
MACHINE_FEATURES_append_e4hd = " nxpl"
EXTRA_OECMAKE_append_e4hd    = " -DWITH_PLATFORM=nxpl"

# meta-edision
MACHINE_FEATURES_append_osmini4k    = " edison-cortexa15"
MACHINE_FEATURES_append_osmio4k     = " edison-cortexa15"
MACHINE_FEATURES_append_osmio4kplus = " edison-cortexa15"
MACHINE_FEATURES_append_osmega      = " xcore-mipsel"
SRC_URI_append_osmega            = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_osmini4k    = " -DWITH_PLATFORM=edison-cortexa15"
EXTRA_OECMAKE_append_osmio4k     = " -DWITH_PLATFORM=edison-cortexa15"
EXTRA_OECMAKE_append_osmio4kplus = " -DWITH_PLATFORM=edison-cortexa15"
EXTRA_OECMAKE_append_osmega      = " -DWITH_PLATFORM=xcore-mipsel"

# meta-gfutures
MACHINE_FEATURES_append_bre2ze4k = " nxpl"
MACHINE_FEATURES_append_hd2400   = " nxpl"
MACHINE_FEATURES_append_hd51     = " nxpl"
MACHINE_FEATURES_append_vs1500   = " nxpl"
EXTRA_OECMAKE_append_bre2ze4k = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_hd2400   = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_hd51     = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_vs1500   = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_hd60     = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_hd61     = " -DWITH_PLATFORM=mali-cortexa15"

# meta-gi
MACHINE_FEATURES_append_et1x000 = " nxcl"
EXTRA_OECMAKE_append_et1x000    = " -DWITH_PLATFORM=nxcl"

# meta-gigablue
MACHINE_FEATURES_append_gbquad4k = " GB-cortexa15"
MACHINE_FEATURES_append_gbue4k   = " GB-cortexa15"
SRC_URI_append_gbquad4k          = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_gbue4k            = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_gbquad4k    = " -DWITH_PLATFORM=GB-cortexa15"
EXTRA_OECMAKE_append_gbue4k      = " -DWITH_PLATFORM=GB-cortexa15"
DEPENDS_append_gbquad4k          = " gigablue-kodi-gbquad4k"
DEPENDS_append_gbue4k            = " gigablue-kodi-gbue4k"
SRC_URI_append_gbtrio4k          = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_gbtrio4k    = " -DWITH_PLATFORM=mali-cortexa15"

# meta-maxytec
EXTRA_OECMAKE_append_multibox   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_multiboxse = " -DWITH_PLATFORM=mali-cortexa15"

# meta-octagon
SRC_URI_append_sf8008         = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_sf8008m        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_sf8008   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_sf8008m  = " -DWITH_PLATFORM=mali-cortexa15"

# meta-qviart
MACHINE_FEATURES_append_lunix4k = " nxcl"
EXTRA_OECMAKE_append_lunix4k    = " -DWITH_PLATFORM=nxcl"

# meta-uclan
SRC_URI_append_ustym4kpro         = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_ustym4kpro   = " -DWITH_PLATFORM=mali-cortexa15"

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
EXTRA_OECMAKE_append_vusolo2     = " -DWITH_PLATFORM=vuplus-mipsel"
EXTRA_OECMAKE_append_vuduo2      = " -DWITH_PLATFORM=vuplus-mipsel"
EXTRA_OECMAKE_append_vusolose    = " -DWITH_PLATFORM=vuplus-mipsel"
EXTRA_OECMAKE_append_vusolo4k    = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuultimo4k  = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuuno4k     = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuuno4kse   = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuzero4k    = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuduo4k     = " -DWITH_PLATFORM=vuplus-cortexa15"
EXTRA_OECMAKE_append_vuduo4kse   = " -DWITH_PLATFORM=vuplus-cortexa15"

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
MACHINE_FEATURES_append_et10000   = " nxpl"
EXTRA_OECMAKE_append_et10000      = " -DWITH_PLATFORM=nxpl"

# meta-zgemma
MACHINE_FEATURES_append_h7     = " nxpl"
EXTRA_OECMAKE_append_h7        = " -DWITH_PLATFORM=nxpl"
#
EXTRA_OECMAKE_append_h9        = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_h9combo   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_h9combose = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_h9se      = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_h10       = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_h11       = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_i55plus   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_i55se     = " -DWITH_PLATFORM=mali-cortexa15"

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
