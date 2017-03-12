require recipes-mediacenter/kodi/kodi_${PV}.bb

PR = "r7"

COMPATIBLE_MACHINE = "^(osmega)$"

FILESEXTRAPATHS_prepend := "${THISDIR}/kodi:"

RDEPENDS_${PN} += "v3d-libgles-osmega"

SRC_URI_append = " \
	file://0001-Krypton-V3D-support.patch \
	"

EXTRA_OECONF += " \
	--with-platform=v3d \
	"

