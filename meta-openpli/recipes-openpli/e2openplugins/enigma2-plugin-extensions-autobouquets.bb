DESCRIPTION = "28.2E stream bouquet downloader"
SUMMARY = "scan dvb data for automatic bouquets creation on Enigma2 STB"
MAINTAINER = "LraiZer"
HOMEPAGE = "https://github.com/LraiZer/AutoBouquets"
SECTION = "extra"
PRIORITY = "optional"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb \
    file://COPYING;md5=036b9f2d884ff3a35bed6ab09bafff32 \
"

inherit gitpkgv python3-compileall

AUTOBOUQUETS_BRANCH ?= "release"
PV = "2.2+git${SRCPV}"
PKGV = "2.2+git${GITPKGV}"
PR = "r0"

INSANE_SKIP:${PN} += "already-stripped ldflags"

SRC_URI="git://github.com/LraiZer/AutoBouquets.git;branch=${AUTOBOUQUETS_BRANCH};protocol=https"

S = "${WORKDIR}/git"

FILES:${PN} = "${libdir}/enigma2/python/Plugins/Extensions/AutoBouquets"
D_FILES:PN = "${D}${FILES:${PN}}"

EXTRA_OECONF = ""

do_install() {
    install -d ${D_FILES:PN}
    install -d ${D_FILES:PN}/locale
    install -m 755 ${S}/autobouquetsreader ${D_FILES:PN}
    install -m 755 ${S}/*.sh ${D_FILES:PN}
    install -m 644 ${S}/*.py *.txt *.png ${D_FILES:PN}
    install -m 644 ${S}/locale/*.* ${D_FILES:PN}/locale
    install -m 644 ${S}/COPYING ${D_FILES:PN}
    install -m 644 ${S}/LICENSE ${D_FILES:PN}

    install -d ${D_FILES:PN}/locale/en_GB/LC_MESSAGES
    install -d ${D_FILES:PN}/locale/ru/LC_MESSAGES
    install -m 644 ${S}/locale/en_GB/LC_MESSAGES/*.* ${D_FILES:PN}/locale/en_GB/LC_MESSAGES
    install -m 644 ${S}/locale/ru/LC_MESSAGES/*.* ${D_FILES:PN}/locale/ru/LC_MESSAGES
}

pkg_postrm:${PN() {
    #!/bin/sh

    echo "Removing ${PN}"
    rm -rf ${FILES:${PN}} > /dev/null 2>&1

    exit 0
}
