PROVIDES += " virtual/kodi"
RPROVIDES_${PN} += " virtual/kodi"
INSANE_SKIP_${PN} += " file-rdeps"

PACKAGE_ARCH = "${MACHINE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/kodi-18:"

SRC_URI_append += "\
            file://kodi-stb-support.patch \
            file://egl/kodi-EGL.patch \
            file://kodi18-add-libinput-rckey-events.patch \
            \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://egl/EGLNativeTypeV3D-nxpl.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://egl/EGLNativeTypeMali.patch file://kodiplayers/HiPlayer.patch file://kodiplayers/HiPlayer-Subs.patch file://defaultplayer-HiPlayer.patch', 'file://defaultplayer-E2Player.patch file://kodiplayers/E2Player.patch', d)} \
"

WINDOWSYSTEM = "stb"

PACKAGECONFIG = "${ACCEL} ${WINDOWSYSTEM} lcms"
PACKAGECONFIG[stb] = "-DCORE_PLATFORM_NAME=stb -DENABLE_GLES=ON,,"

#Edision
SRC_URI_append_osmini4k    += "file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4k     += "file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4kplus += "file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmega      += "file://egl/EGLNativeTypeV3D-platform.patch file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_osmini4k    += " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4k     += " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4kplus += " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmega      += " -DWITH_V3D=v3dplatform"

#Gigablue
#SRC_URI_append_gbquad4k     += " file://egl/EGLNativeTypeV3D-gb4k.patch"
#SRC_URI_append_gbue4k       += " file://egl/EGLNativeTypeV3D-gb4k.patch"
#SRC_URI_append_gbtrio4k     += " file://egl/EGLNativeTypeMali.patch"
#EXTRA_OECMAKE_append_gbquad4k     += " -DWITH_V3D=nxinit"
#EXTRA_OECMAKE_append_gbue4k       += " -DWITH_V3D=nxinit"

#Vuplus
SRC_URI_append_vusolo2     += " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vuduo2      += " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vusolose    += " file://egl/EGLNativeTypeV3D-vuplus.patch file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_vusolo4k    += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuultimo4k  += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4k     += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuuno4kse   += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuzero4k    += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuduo4k     += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
SRC_URI_append_vuduo4kse   += " file://egl/EGLNativeTypeV3D-vuplus4k.patch"
EXTRA_OECMAKE_append_vusolo2     += " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vuduo2      += " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vusolose    += " -DWITH_V3D=vumips"
EXTRA_OECMAKE_append_vusolo4k    += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuultimo4k  += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuuno4k     += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuuno4kse   += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuzero4k    += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuduo4k     += " -DWITH_V3D=vuarm"
EXTRA_OECMAKE_append_vuduo4kse   += " -DWITH_V3D=vuarm"

EXTRA_KODI ?= "empty"
EXTRA_KODI_vusolo2 = "vuplus"
EXTRA_KODI_vuduo2 = "vuplus"
EXTRA_KODI_vusolose = "vuplus"

require kodi-${EXTRA_KODI}.inc

do_configure_prepend_vusolo2() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

do_configure_prepend_vuduo2() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

do_configure_prepend_vusolose() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

RDEPENDS_${pn}_append_vusolo2     += " kodi-vuplus-support"
RDEPENDS_${pn}_append_vuduo2      += " kodi-vuplus-support"
RDEPENDS_${pn}_append_vusolose    += " kodi-vuplus-support"
RDEPENDS_${pn}_append_vusolo4k    += " kodiegl-vusolo4k"
RDEPENDS_${pn}_append_vuultimo4k  += " kodiegl-vuultimo4k"
RDEPENDS_${pn}_append_vuuno4k     += " kodiegl-vuuno4k"
RDEPENDS_${pn}_append_vuuno4kse   += " kodiegl-vuuno4kse"
RDEPENDS_${pn}_append_vuzero4k    += " kodiegl-vuzero4k"
RDEPENDS_${pn}_append_vuduo4k     += " kodiegl-vuduo4k"
RDEPENDS_${pn}_append_vuduo4kse   += " kodiegl-vuduo4kse"

# Qviart
SRC_URI_append_lunix4k    += "file://egl/EGLNativeTypeV3D-lunix4k.patch"
EXTRA_OECMAKE_append_lunix4k    += " -DWITH_V3D=nxcl"
