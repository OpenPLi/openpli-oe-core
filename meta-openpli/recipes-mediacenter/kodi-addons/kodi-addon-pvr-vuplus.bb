SUMMARY = "Kodi Media Center PVR plugins"

PV = "3.28.9+git${SRCPV}"
PKGV = "3.28.9+git${GITPKGV}"

KODIADDONPLUGIN = "vuplus"

DEPENDS_append = "nlohmann-json"

require kodi-addon-pvr.inc
