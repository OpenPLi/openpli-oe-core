SUMMARY = "Enigma2 Software Defined Radio"
DESCRIPTION = "SDR for Enigma2 using rtl_fm and dab-cmdline command line tools"
SECTION = "multimedia"
MAINTAINER = "SatDreamGR"
HOMEPAGE = "http://satdreamgr.com"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://setup.py;beginline=4;endline=4;md5=a340145d59ca0aca0ff7ec9adccdb556"
SRC_URI = "git://github.com/satdreamgr/SDGRadio.git;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
PR = "r1"

inherit distutils-openplugins

RDEPENDS_${PN} = "${PYTHON_PN}-core rtl-sdr redsea dab-cmdline-sdgradio dab-cmdline-sdgradio-pcm dab-cmdline-sdgradio-wav"
