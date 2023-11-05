SUMMARY = "Kodi Media Center PVR plugins"

PV = "20.6.0"
PKGV = "20.6.0+git${GITPKGV}"

KODIADDONPLUGIN = "iptvsimple"

DEPENDS:append = "pugixml"

require kodi-addon-pvr.inc
