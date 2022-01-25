SUMMARY = "Kodi Media Center PVR plugins"

PV = "3.9.8+git${SRCPV}"
PKGV = "3.9.8+git${GITPKGV}"

KODIADDONPLUGIN = "iptvsimple"

DEPENDS_append = "pugixml"

require kodi-addon-pvr.inc
