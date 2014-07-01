SUMMARY = "Transcoding setup"
DESCRIPTION = "Set default bitrate and picture size for streamproxy (VU+ Solo2 and VU+ Duo2)"
AUTHOR = "PLi team"
HOMEPAGE = "http://www.openpli.org"
MAINTAINER = "PLi team"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9c67ebc2b01da2b23b565bc2efdec43e"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r0"
SRC_URI = "git://github.com/eriksl/enigma2-plugin-systemplugins-transcoding-setup.git;protocol=git"
SRCREV = "fe2ff9266098b042da04134472bb6529c0f9f497"
S = "${WORKDIR}/git"

inherit gitpkgv

do_install() {
	install -d "${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/TranscodingSetup"
	install -m 644 "${S}/__init__.py" "${S}/plugin.py" "${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/TranscodingSetup"
}

FILES_${PN} = "/usr/lib/enigma2/python/Plugins/SystemPlugins/TranscodingSetup"
PACKAGE_ARCH = "${MACHINE_ARCH}"
