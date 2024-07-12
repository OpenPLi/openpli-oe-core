DESCRIPTION = "Skins for Enigma2"
MAINTAINER = "schwerkraft"
PACKAGES = "${PN}-meta ${PN}"
PACKAGES_DYNAMIC = "enigma2-plugin-skins-*"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README;startline=1;endline=6;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit gitpkgv python3native

PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
BRANCH = "3.2"

SRC_URI = "git://github.com/opendreambox/enigma2-skins.git;protocol=https;branch=${BRANCH}"

#include examples of openpli widgets
SRC_URI:append = " \
	file://dtvhd.diff \
	file://brushedaluhd.diff \
	file://blackbox.diff \
	file://set-encoding-to-unicode.patch \
	"

# note that enigma2-skins is just an empty package to satisfy silly dependencies.
ALLOW_EMPTY:${PN} = "1"
FILES:${PN} = "${datadir}/enigma2 ${datadir}/fonts"
FILES:${PN}-meta = "${datadir}/meta"
RDEPENDS:${PN}-meta = ""

inherit autotools-brokensep

S = "${WORKDIR}/git"

python populate_packages:prepend () {
    if bb.data.expand('${REL_MINOR}', d) != "4":
        enigma2_skindir = bb.data.expand('${datadir}/enigma2', d)
        do_split_packages(d, enigma2_skindir, '(.*?)/.*', 'enigma2-plugin-skins-%s', 'Enigma2 Skin: %s', recursive=True, match_path=True, prepend=True, extra_depends='')
}
