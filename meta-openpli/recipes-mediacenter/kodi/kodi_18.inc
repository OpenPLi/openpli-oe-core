# NOTE: this should be put in kodi_18.bbappend in the respective BSP layers

#Edision
SRC_URI_append_osmini4k    = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4k     = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4kplus = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmega      = " file://egl/EGLNativeTypeV3D-platform.patch file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_osmini4k    = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4k     = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4kplus = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmega      = " -DWITH_V3D=v3dplatform"

#Gigablue
#SRC_URI_append_gbquad4k     = " file://egl/EGLNativeTypeV3D-gb4k.patch"
#SRC_URI_append_gbue4k       = " file://egl/EGLNativeTypeV3D-gb4k.patch"
#SRC_URI_append_gbtrio4k     = " file://egl/EGLNativeTypeMali.patch"
#EXTRA_OECMAKE_append_gbquad4k     = " -DWITH_V3D=nxinit"
#EXTRA_OECMAKE_append_gbue4k       = " -DWITH_V3D=nxinit"

# Qviart
SRC_URI_append_lunix4k    = " file://egl/EGLNativeTypeV3D-lunix4k.patch"
EXTRA_OECMAKE_append_lunix4k    = " -DWITH_V3D=nxcl"

#Vuplus
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
EXTRA_OECMAKE_append_vusolo2     = " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vuduo2      = " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vusolose    = " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vusolo4k    = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuultimo4k  = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuuno4k     = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuuno4kse   = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuzero4k    = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuduo4k     = " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuduo4kse   = " -DWITH_V3D=vuarm"

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

# Zgemma specific: for libEGL.so and libGLESv2.so no providers found in RDEPENDS_kodi? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
