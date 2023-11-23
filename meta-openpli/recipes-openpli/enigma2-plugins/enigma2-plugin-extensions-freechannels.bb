SUMMARY = "Enigma2 plugin to scan bouquets to find free channels"
DESCRIPTION = "Scan bouquets to find free channels and add to the bouquet if it contains the specified language."
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-freechannels"
MAINTAINER = "Taapat"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
SRC_URI = "git://github.com/Taapat/enigma2-plugin-freechannels.git;protocol=https;branch=main"
S = "${WORKDIR}/git"

inherit gitpkgv python3-compileall

PV = "py3-git${SRCPV}"
PKGV = "py3-git${GITPKGV}"

inherit setuptools3-openplugins
