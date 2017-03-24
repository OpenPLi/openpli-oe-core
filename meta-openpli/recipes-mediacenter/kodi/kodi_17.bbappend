PROVIDES += "virtual/kodi"
RPROVIDES_${PN} += "virtual/kodi"
PROVIDES += "kodi"
RPROVIDES_${PN} += "kodi"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "\
	file://v3d-platform.patch \
	file://brcmstb-settings.patch \
	file://e2player.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://EGLNativeTypeV3D-nxpl.patch', '', d)} \
	"

SRC_URI_append_osmega += "file://EGLNativeTypeV3D-platform.patch"

DEPENDS += " \
	libbluray \
	nfs-utils \
	libupnp \
	"

EXTRA_OECONF_hd51 += " \
	--with-platform=v3d-cortexa15 \
	--with-ffmpeg=v3d \
	"

EXTRA_OECONF_vs1500 += " \
	--with-platform=v3d-cortexa15 \
	--with-ffmpeg=v3d \
	"

EXTRA_OECONF_hd2400 += " \
	--with-platform=v3d-mipsel \
	--with-ffmpeg=v3d \
	"

EXTRA_OECONF_osmega += " \
	--with-platform=v3d-xcore-mipsel \
	--with-ffmpeg=v3d \
	"