SUMMARY = "Kodi Media Center PVR plugins"

PV = "20.3.0+git${SRCPV}"
PKGV = "20.3.0+git${GITPKGV}"

KODIADDONPLUGIN = "mediaportal.tvserver"

require kodi-addon-pvr.inc

SRC_URI:append = " file://0001-drop-xlocale.h.patch"
