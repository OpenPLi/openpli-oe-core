# NOTE: this should be in the conf files in the respective BSP layers

# meta-abcom
EXTRA_OECMAKE_append_pulse4k      = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_pulse4kmini  = " -DWITH_PLATFORM=mali-cortexa15"

# meta-axasuhd
EXTRA_OECMAKE_append_e4hd = " -DWITH_PLATFORM=nxpl"

# meta-edision
SRC_URI_append_osmega      = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_osmini4k    = " -DWITH_PLATFORM=v3dplatform-arm"
EXTRA_OECMAKE_append_osmio4k     = " -DWITH_PLATFORM=v3dplatform-arm"
EXTRA_OECMAKE_append_osmio4kplus = " -DWITH_PLATFORM=v3dplatform-arm"
EXTRA_OECMAKE_append_osmega      = " -DWITH_PLATFORM=v3dplatform-mipsel"

# meta-gfutures
EXTRA_OECMAKE_append_bre2ze4k = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_hd2400   = " -DWITH_PLATFORM=nxpl"
EXTRA_OECMAKE_append_hd51     = " -DWITH_PLATFORM=nxpl"
#
EXTRA_OECMAKE_append_vs1500   = " -DWITH_PLATFORM=nxpl"
#
EXTRA_OECMAKE_append_hd60     = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_hd61     = " -DWITH_PLATFORM=mali-cortexa15"

# meta-gi
EXTRA_OECMAKE_append_et1x000 = " -DWITH_PLATFORM=nxcl"

# meta-gigablue
SRC_URI_append_gbquad4k        = " file://egl/EGLNativeTypeV3D-gb4k.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_gbue4k          = " file://egl/EGLNativeTypeV3D-gb4k.patch file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_gbquad4k  = " -DWITH_PLATFORM=nxinit"
EXTRA_OECMAKE_append_gbue4k    = " -DWITH_PLATFORM=nxinit"
DEPENDS_append_gbquad4k        = " gigablue-kodi-gbquad4k"
DEPENDS_append_gbue4k          = " gigablue-kodi-gbue4k"
SRC_URI_append_gbtrio4k        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_gbtrio4k  = " -DWITH_PLATFORM=mali-cortexa15"

# meta-maxytec
EXTRA_OECMAKE_append_multibox   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_multiboxse = " -DWITH_PLATFORM=mali-cortexa15"

# meta-octagon
SRC_URI_append_sf8008         = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_sf8008m        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_sf8008   = " -DWITH_PLATFORM=mali-cortexa15"
EXTRA_OECMAKE_append_sf8008m  = " -DWITH_PLATFORM=mali-cortexa15"

# meta-qviart
EXTRA_OECMAKE_append_lunix4k  = " -DWITH_PLATFORM=nxcl"

# meta-uclan
SRC_URI_append_ustym4kpro         = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_ustym4kpro   = " -DWITH_PLATFORM=mali-cortexa15"

# meta-vuplus
SRC_URI_append_vusolo2     = " file://egl/EGLNativeTypeV3D-vuplus.patch"
SRC_URI_append_vuduo2      = " file://egl/EGLNativeTypeV3D-vuplus.patch"
SRC_URI_append_vusolose    = " file://egl/EGLNativeTypeV3D-vuplus.patch"
SRC_URI_append_vusolo4k    = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuultimo4k  = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4k     = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4kse   = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuzero4k    = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuduo4k     = " file://egl/EGLNativeTypeV3D-vuplus4k.patch file://vuplus-fix-exception-duo4k.patch"
SRC_URI_append_vuduo4kse   = " file://egl/EGLNativeTypeV3D-vuplus4k.patch file://vuplus-fix-exception-duo4k.patch"
EXTRA_OECMAKE_append_vusolo2     = " -DWITH_PLATFORM=vumips"
EXTRA_OECMAKE_append_vuduo2      = " -DWITH_PLATFORM=vumips"
EXTRA_OECMAKE_append_vusolose    = " -DWITH_PLATFORM=vumips"
EXTRA_OECMAKE_append_vusolo4k    = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuultimo4k  = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuuno4k     = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuuno4kse   = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuzero4k    = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuduo4k     = " -DWITH_PLATFORM=vuarm"
EXTRA_OECMAKE_append_vuduo4kse   = " -DWITH_PLATFORM=vuarm"

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
EXTRA_OECMAKE_append_et10000 = " -DWITH_PLATFORM=nxpl"

# meta-zgemma
EXTRA_OECMAKE_append_h7 = " -DWITH_PLATFORM=nxpl"
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
