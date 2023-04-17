SUMMARY = "Kodi Media Center PVR plugins"

PV = "20.3.9+git${SRCPV}"
PKGV = "20.3.0+git${GITPKGV}"

RDEPENDS_${PN} = "libtinyxml2"

KODIADDONPLUGIN = "dvblink"

require kodi-addon-pvr.inc
