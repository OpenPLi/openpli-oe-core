SUMMARY = "Kodi Media Center PVR plugins"

PV = "20.4.0+git${SRCPV}"
PKGV = "20.4.0+git${GITPKGV}"

KODIADDONPLUGIN = "vuplus"

DEPENDS:append = "nlohmann-json"

require kodi-addon-pvr.inc
