# NOTE: this should be put in kodi_18.bbappend in the respective BSP layers

# meta-abcom
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_pulse4k           = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_pulse4kmini       = " file://egl/EGLNativeTypeMali.patch"
EXTRA_OECMAKE_append_pulse4k     = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_pulse4kmini = " -DWITH_V3D=mali-cortexa15"

# meta-axasuhd
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_e4hd       = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_e4hd = " -DWITH_V3D=nxpl"

# meta-edision
SRC_URI_append_osmini4k    = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4k     = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4kplus = " file://egl/EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmega      = " file://egl/EGLNativeTypeV3D-platform.patch file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_osmini4k    = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4k     = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmio4kplus = " -DWITH_V3D=v3dplatform"
EXTRA_OECMAKE_append_osmega      = " -DWITH_V3D=v3dplatform"

# meta-gfutures
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_bre2ze4k     = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_bre2ze4k = " -DWITH_V3D=nxpl"
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_hd2400     = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_hd2400 = " -DWITH_V3D=nxpl"
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_hd51     = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_hd51 = " -DWITH_V3D=nxpl"
#
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_vs1500     = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_vs1500 = " -DWITH_V3D=nxpl"
#
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_hd60         = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_hd61         = " file://egl/EGLNativeTypeMali.patch"
EXTRA_OECMAKE_append_hd60   = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_hd61   = " -DWITH_V3D=mali-cortexa15"

# meta-gi
# MACHINE_FEATURES contains "v3d-nxclient" so try the nxpl version
SRC_URI_append_et1x000     = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxclient"
# translated into nxcl
EXTRA_OECMAKE_append_et1x000 = " -DWITH_V3D=nxcl"

# meta-gigablue
SRC_URI_append_gbquad4k     = " file://egl/EGLNativeTypeV3D-gb4k.patch"
SRC_URI_append_gbue4k       = " file://egl/EGLNativeTypeV3D-gb4k.patch"
EXTRA_OECMAKE_append_gbquad4k     = " -DWITH_V3D=nxinit"
EXTRA_OECMAKE_append_gbue4k       = " -DWITH_V3D=nxinit"
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_gbtrio4k       = " file://egl/EGLNativeTypeMali.patch"
SRC_URI_append_gbtrio4k        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_gbtrio4k  = " -DWITH_V3D=mali-cortexa15"

# meta-maxytec
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_multibox        = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_multiboxse      = " file://egl/EGLNativeTypeMali.patch"
EXTRA_OECMAKE_append_multibox   = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_multiboxse = " -DWITH_V3D=mali-cortexa15"

# meta-octagon
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_sf8008        = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_sf8008m       = " file://egl/EGLNativeTypeMali.patch"
SRC_URI_append_sf8008         = " file://egl/kodi-old-gl-headers.patch"
SRC_URI_append_sf8008m        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_sf8008   = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_sf8008m  = " -DWITH_V3D=mali-cortexa15"

# meta-qviart
SRC_URI_append_lunix4k        = " file://egl/EGLNativeTypeV3D-lunix4k.patch"
EXTRA_OECMAKE_append_lunix4k  = " -DWITH_V3D=nxcl"

# meta-uclan
#
# in OE-A seems needing a support package as it was for Vu+:
# hiadp.a and hiplayer.a are installed by hand
#
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_ustym4kpro        = " file://egl/EGLNativeTypeMali.patch"
SRC_URI_append_ustym4kpro        = " file://egl/kodi-old-gl-headers.patch"
EXTRA_OECMAKE_append_ustym4kpro   = " -DWITH_V3D=mali-cortexa15"

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

# meta-xtrend
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_et10000       = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_et10000 = " -DWITH_V3D=nxpl"

# meta-zgemma
# MACHINE_FEATURES contains "v3d-nxpl" so this is already added
#SRC_URI_append_h7       = " file://egl/EGLNativeTypeV3D-nxpl.patch"
# already in the BSP: EXTRA_OECMAKE_append_pn-kodi = " -DWITH_V3D=nxpl"
#EXTRA_OECMAKE_append_h7 = " -DWITH_V3D=nxpl"
#
# MACHINE_FEATURES contains "hisil" so this and HiPlayer are added
#SRC_URI_append_h9         = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_h9combo    = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_h9combose  = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_h9se       = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_h10        = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_h11        = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_i55        = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_i55plus    = " file://egl/EGLNativeTypeMali.patch"
#SRC_URI_append_i55se      = " file://egl/EGLNativeTypeMali.patch"
EXTRA_OECMAKE_append_h9        = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_h9combo   = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_h9combose = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_h9se      = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_h10       = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_h11       = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_i55       = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_i55plus   = " -DWITH_V3D=mali-cortexa15"
EXTRA_OECMAKE_append_i55se     = " -DWITH_V3D=mali-cortexa15"

# Zgemma specific: for libmali.so libEGL.so and libGLESv2.so no providers found
  in RDEPENDS_kodi? [file-rdeps]
INSANE_SKIP_${PN}_h7 += "file-rdeps"
INSANE_SKIP_${PN}_h9 += "file-rdeps"
INSANE_SKIP_${PN}_h9combo += "file-rdeps"
INSANE_SKIP_${PN}_h9combose += "file-rdeps"
INSANE_SKIP_${PN}_h9se += "file-rdeps"
INSANE_SKIP_${PN}_h10 += "file-rdeps"
INSANE_SKIP_${PN}_h11 += "file-rdeps"
INSANE_SKIP_${PN}_i55 += "file-rdeps"
INSANE_SKIP_${PN}_i55plus += "file-rdeps"
INSANE_SKIP_${PN}_i55se += "file-rdeps"
