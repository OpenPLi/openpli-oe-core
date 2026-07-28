SUMMARY = "Foreca One"
MAINTAINER = "Lululla"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPL-2.0-only"

require conf/license/license-gplv2.inc

RDEPENDS:${PN} = "python3-pillow"

inherit allarch gittag python3-compileall

PV = "git"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/Belfagor2005/ForecaOne.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

FILES:${PN} = "/usr/*"
FILES:${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/Foreca1/*.py"

do_install() {
    cp -af --no-preserve=ownership ${S}/usr* ${D}/
}
