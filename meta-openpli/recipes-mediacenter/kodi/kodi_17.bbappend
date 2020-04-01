PROVIDES += "virtual/kodi"
RPROVIDES_${PN} += "virtual/kodi"
PACKAGE_ARCH = "${MACHINE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/kodi-17:"

SRC_URI_append += "\
	file://kodi-platform-support.patch \
	file://brcmstb-settings.patch \
	file://input-devices.patch \
	file://HiPlayer.patch \
	file://HiPlayer-Subs.patch \
	file://quit.patch \
	file://Apply-workaround-for-scratchy-sound-with-FFmpeg-3.4.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://HiPlayer-defaultplayer.patch', 'file://e2player.patch', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://EGLNativeTypeV3D-nxpl.patch', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://EGLNativeTypeMali.patch', '', d)} \
	"

SRC_URI_append_osmega += "file://EGLNativeTypeV3D-platform.patch"
SRC_URI_append_osmio4k += "file://EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmio4kplus += "file://EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_osmini4k += "file://EGLNativeTypeV3D-platform-arm.patch"
SRC_URI_append_lunix4k += "file://EGLNativeTypeV3D-lunix4k.patch"

DEPENDS += " \
	bluez5 \
	libbluray \
	nfs-utils \
	libupnp \
	"

EXTRA_OECONF_hd51 += "--with-gpu=v3d"
EXTRA_OECONF_vs1500 += "--with-gpu=v3d"
EXTRA_OECONF_hd2400 += "--with-gpu=v3d"
EXTRA_OECONF_h7 += "--with-gpu=v3d"
EXTRA_OECONF_osmega += "--with-gpu=v3dplatform"
EXTRA_OECONF_osmio4k += "--with-gpu=v3dplatform"
EXTRA_OECONF_osmio4kplus += "--with-gpu=v3dplatform"
EXTRA_OECONF_osmini4k += "--with-gpu=v3dplatform"
EXTRA_OECONF_wetekplay += "--with-gpu=mali"

EXTRA_KODI ?= "empty"
EXTRA_KODI_vusolo2 = "vuplus"
EXTRA_KODI_vuduo2 = "vuplus"
EXTRA_KODI_vusolose = "vuplus"
EXTRA_KODI_vusolo4k = "vuplus4k"
EXTRA_KODI_vuultimo4k = "vuplus4k"
EXTRA_KODI_vuuno4k = "vuplus4k"
EXTRA_KODI_vuuno4kse = "vuplus4k"
EXTRA_KODI_vuzero4k = "vuplus4k"
EXTRA_KODI_vuduo4k = "vuplus4k"

require kodi-${EXTRA_KODI}.inc
