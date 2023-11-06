DESCRIPTION = "Imports XMLTV and epg.dat files into the EPG cache of enigma2"
MAINTAINER = "OpenPLi team"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../etc/epgimport/readme.txt;startline=1;endline=4;md5=c162054328d930d453543efef81be1d8"

inherit gitpkgv python3-compileall

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_ORIGIN ?= "git://github.com/OpenPLi/${BPN}.git;protocol=https;branch=master"
SRC_URI := "${SRC_ORIGIN} "

S = "${WORKDIR}/git/src"

inherit distutils-openplugins

DEPENDS = "${PYTHON_PN}"
RDEPENDS:${PN} = "${PYTHON_PN}-compression ${PYTHON_PN}-shell ${PYTHON_PN}-pkgutil"
RRECOMMENDS:${PN} = "${PN}-rytec"
PACKAGES = "${PN}-dbg ${PN} ${PN}-src"

RREPLACES:${PN} = "enigma2-plugin-extensions-xmltvimport"
RCONFLICTS:${PN} = "enigma2-plugin-extensions-xmltvimport"

PLUGIN = "EPGImport"

FILES:${PN} = "${libdir}/enigma2/python"
FILES:${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/${PLUGIN}/*.py"
FILES:${PN}-dbg = "${libdir}/enigma2/python/Plugins/Extensions/${PLUGIN}/.debug ${prefix}/src/debug"

pkg_postinst:${PN}() {

	if [ ! -f $D${sysconfdir}/image-version ]
	then
		# when slipstreaming, don't patch enigma
		exit 0
	fi
	if grep -q PLi $D${sysconfdir}/image-version
	then
		# PLi needs no patch...
		true
	else
		[ -f $D${bindir}/enigma2.sh.xmltvbak ] || {
			cp $D${bindir}/enigma2.sh $D${bindir}/enigma2.sh.xmltvbak
			sed '3ipython $D${libdir}/enigma2/python/Plugins/Extensions/EPGImport/boot.py' $D${bindir}/enigma2.sh.xmltvbak > $D${bindir}/enigma2.sh
		}
	fi
}

pkg_prerm:${PN}() {
	if [ -f ${bindir}/enigma2.sh.xmltvbak ] ; then
		mv -f ${bindir}/enigma2.sh.xmltvbak ${bindir}/enigma2.sh
	fi
}
