# Some forked meta-kodi repositories using custom kodi urls, personally I think they should use variable instead of
# creating different recipes and diverse from upstream

KODIVER ?= "20.1"

KODIURI ?= "git://github.com/xbmc/xbmc.git;protocol=https;branch=Nexus"
KODIWORKDIR ?= "${WORKDIR}/git"

KODIPATCHES ?= ""
KODIADDONS ?= ""

SRC_URI = "${KODIURI} ${KODIPATCHES} ${KODIADDONS}"
SRCREV = "${PV}"
S = "${KODIWORKDIR}"

PV = "${KODIVER}-Nexus"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM ?= "file:///${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

# for some devices: for libmali.so libEGL.so and libGLESv2.so no providers found
# in RDEPENDS:kodi? [file-rdeps]
#
INSANE_SKIP:${PN}_gbtrio4k += "file-rdeps"
#
INSANE_SKIP:${PN}_sf8008 += "file-rdeps"
INSANE_SKIP:${PN}_sf8008m += "file-rdeps"
INSANE_SKIP:${PN}_sx88v2 += "file-rdeps"
#
INSANE_SKIP:${PN}_dual += "file-rdeps"
#
INSANE_SKIP:${PN}_ustym4kpro += "file-rdeps"
INSANE_SKIP:${PN}_ustym4ks2ottx += "file-rdeps"
#
INSANE_SKIP:${PN}_h7 += "file-rdeps"
INSANE_SKIP:${PN}_h9 += "file-rdeps"
INSANE_SKIP:${PN}_h9combo += "file-rdeps"
INSANE_SKIP:${PN}_h9combose += "file-rdeps"
INSANE_SKIP:${PN}_h9se += "file-rdeps"
INSANE_SKIP:${PN}_h10 += "file-rdeps"
INSANE_SKIP:${PN}_h11 += "file-rdeps"
INSANE_SKIP:${PN}_i55plus += "file-rdeps"
INSANE_SKIP:${PN}_i55se += "file-rdeps"
