PROVIDES += "virtual/kodi"
RPROVIDES_${PN} += "virtual/kodi"
PACKAGE_ARCH = "${MACHINE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "\
	file://kodi-platform-support.patch \
	file://brcmstb-settings.patch \
	file://input-devices.patch \
	file://e2player.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://EGLNativeTypeV3D-nxpl.patch', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://EGLNativeTypeMali.patch', '', d)} \
	"

SRC_URI_append_osmega += "file://EGLNativeTypeV3D-platform.patch"
RDEPENDS_${PN}_osmega += "v3d-libgles-osmega"

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