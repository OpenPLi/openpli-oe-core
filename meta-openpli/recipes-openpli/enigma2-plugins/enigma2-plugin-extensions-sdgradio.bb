SUMMARY = "Enigma2 Software Defined Radio"
DESCRIPTION = "SDR for Enigma2 using rtl_fm and dab-cmdline command line tools"
SECTION = "multimedia"
MAINTAINER = "SatDreamGR"
HOMEPAGE = "http://satdreamgr.com"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://setup.py;md5=fc9a06ee9f5aa09c43503adaac0ad2fd"
SRC_URI = "git://github.com/satdreamgr/SDGRadio.git;protocol=http"

S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
PR = "r1"

inherit distutils-openplugins

RDEPENDS_${PN} = "python-core rtl-sdr redsea dab-cmdline-sdgradio dab-cmdline-sdgradio-pcm dab-cmdline-sdgradio-wav"

PACKAGES =+ "${PN}-src"
RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/SDGRadio/*.py"

