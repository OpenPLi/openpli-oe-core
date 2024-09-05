DESCRIPTION = "IPTV m3u list dynamic reader and runner"
MAINTAINER = "DimitarCC"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
HOMEPAGE = "https://github.com/DimitarCC"

inherit gitpkgv allarch gettext python3-compileall

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/DimitarCC/iptv-m3u-reader.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

FILES:${PN} = "${pluginpath}/"

pluginpath = "/usr/lib/enigma2/python/Plugins/SystemPlugins/M3UIPTV"

do_install() {
	install -d ${D}${pluginpath}
	cp -r ${S}/src/* ${D}${pluginpath}/
}
