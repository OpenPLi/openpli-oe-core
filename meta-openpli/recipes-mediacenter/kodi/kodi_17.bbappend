PROVIDES += "virtual/kodi"
RPROVIDES_${PN} += "virtual/kodi"
PACKAGE_ARCH = "${MACHINE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/kodi-17:"

SRC_URI_append += "\
	file://kodi-platform-support.patch \
	file://brcmstb-settings.patch \
	file://input-devices.patch \
	file://e2player.patch \
	file://quit.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://EGLNativeTypeV3D-nxpl.patch', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://EGLNativeTypeMali.patch', '', d)} \
	"

SRC_URI_append_osmega += "file://EGLNativeTypeV3D-platform.patch"

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

require kodi-${EXTRA_KODI}.inc
