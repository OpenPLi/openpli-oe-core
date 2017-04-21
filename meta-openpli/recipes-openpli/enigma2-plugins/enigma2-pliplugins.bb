LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
# There's only one working plugin in this repository
PACKAGES = "enigma2-plugin-extensions-openuitzendinggemist"
RDEPENDS_enigma2-plugin-extensions-openuitzendinggemist = "python-argparse"

inherit allarch pythonnative gitpkgv autotools-brokensep

PV = "2+git${SRCPV}"
PKGV = "2+git${GITPKGV}"

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/${BPN}.git"

S = "${WORKDIR}/git"

EXTRA_OECONF = "LIBDIR=${libdir}"

FILES_enigma2-plugin-extensions-openuitzendinggemist = "${libdir}/enigma2/python/Plugins/Extensions/OpenUitzendingGemist"

do_install_append() {
	rm -rf ${D}${libdir}/enigma2/python/Plugins/Extensions/UShare
	find ${D}${libdir}/enigma2/python/ -name '*.pyc' -exec rm {} \;
}

# Nothing of this recipe should end up in sysroot, so blank it away.
sysroot_stage_all() {
    :
}
