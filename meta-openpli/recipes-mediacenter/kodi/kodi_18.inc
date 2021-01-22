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
SRC_URI_append_vusolo2     = " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vuduo2      = " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vusolose    = " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vusolo4k    = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuultimo4k  = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4k     = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4kse   = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuzero4k    = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuduo4k     = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuduo4kse   = " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
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

GLPR ?= ""
GLPR_vuduo2 = "20160331_r0"
GLPR_vusolo2 = "20160331_r0"
GLPR_vusolose = "20160331_r0"

SRC_URI_append_vuduo2 = " http://archive.vuplus.com/download/build_support/kodi/xbmc-support_${MACHINE}_${GLPR}.tar.gz;name=xbmc-${MACHINE}"
SRC_URI_append_vusolo2 = " http://archive.vuplus.com/download/build_support/kodi/xbmc-support_${MACHINE}_${GLPR}.tar.gz;name=xbmc-${MACHINE}"
SRC_URI_append_vusolose = " http://archive.vuplus.com/download/build_support/kodi/xbmc-support_${MACHINE}_${GLPR}.tar.gz;name=xbmc-${MACHINE}"

SRC_URI[xbmc-vuduo2.md5sum] = "f3db678550f3654fcc8dfbb875678943"
SRC_URI[xbmc-vuduo2.sha256sum] = "758e75966c1ca513bbeb7eaef0d0359207232ba0e7f4f5e2574c146f5e09cab3"
SRC_URI[xbmc-vusolo2.md5sum] = "e29a91b185133ec60a59e94a8229d2b4"
SRC_URI[xbmc-vusolo2.sha256sum] = "3c56b7ee890b3e21f378acd79db3752d721de0880b6d763bbd37fa942c2ae2b5"
SRC_URI[xbmc-vusolose.md5sum] = "831014212eed47e36ec084f2e803e2d8"
SRC_URI[xbmc-vusolose.sha256sum] = "97bfc26a316bcba4b897f81f31179e8861cc123a0b4d8589a2290f3cd7268c1d"

do_configure_prepend_vusolo2() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

do_configure_prepend_vuduo2() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

do_configure_prepend_vusolose() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

DEPENDS_append_vusolo2     = " xbmc-support-vusolo2"
DEPENDS_append_vuduo2      = " xbmc-support-vuduo2"
DEPENDS_append_vusolose    = " xbmc-support-vusolose"
DEPENDS_append_vusolo4k    = " kodiegl-vusolo4k"
DEPENDS_append_vuultimo4k  = " kodiegl-vuultimo4k"
DEPENDS_append_vuuno4k     = " kodiegl-vuuno4k"
DEPENDS_append_vuuno4kse   = " kodiegl-vuuno4kse"
DEPENDS_append_vuzero4k    = " kodiegl-vuzero4k"
DEPENDS_append_vuduo4k     = " kodiegl-vuduo4k"
DEPENDS_append_vuduo4kse   = " kodiegl-vuduo4kse"

RDEPENDS_${PN}_append_vusolo2     = " xbmc-support-vusolo2"
RDEPENDS_${PN}_append_vuduo2      = " xbmc-support-vuduo2"
RDEPENDS_${PN}_append_vusolose    = " xbmc-support-vusolose"
RDEPENDS_${PN}_append_vusolo4k    = " kodiegl-vusolo4k"
RDEPENDS_${PN}_append_vuultimo4k  = " kodiegl-vuultimo4k"
RDEPENDS_${PN}_append_vuuno4k     = " kodiegl-vuuno4k"
RDEPENDS_${PN}_append_vuuno4kse   = " kodiegl-vuuno4kse"
RDEPENDS_${PN}_append_vuzero4k    = " kodiegl-vuzero4k"
RDEPENDS_${PN}_append_vuduo4k     = " kodiegl-vuduo4k"
RDEPENDS_${PN}_append_vuduo4kse   = " kodiegl-vuduo4kse"

# Vu+ specific: for libEGL.so and libGLESv2.so no providers found in RDEPENDS_kodi? [file-rdeps]
INSANE_SKIP_${PN} += "file-rdeps"
