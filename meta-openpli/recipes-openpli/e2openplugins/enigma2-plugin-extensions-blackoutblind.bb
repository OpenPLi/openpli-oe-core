MODULE = "BlackoutBlind"
DESCRIPTION = "Blackout blind by mrvica, puts a black bar on top of the screen to hide VBI lines"

inherit gitpkgv
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-${MODULE}.git;protocol=https;branch=master"
