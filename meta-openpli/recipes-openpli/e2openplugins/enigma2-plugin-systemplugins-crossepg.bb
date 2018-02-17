DESCRIPTION = "Handle your EPG on enigma2 from various sources (opentv, mhw, xmltv, custom sources)"
HOMEPAGE = "https://github.com/oe-alliance/e2openplugin-${MODULE}"
MODULE = "CrossEPG"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "libxml2 zlib python"

inherit gitpkgv

inherit python-dir

require openplugins.inc

SRC_URI = "git://github.com/oe-alliance/e2openplugin-${MODULE}.git"
PV = "0.8.1+git${SRCPV}"
PKGV = "0.8.1+git${GITPKGV}"

FILES_${PN} = "/usr/*"
FILES_${PN}-dbg += "/usr/crossepg/scripts/mhw2epgdownloader/.debug"

PARALLEL_MAKE = ""
CFLAGS_append = " -I${STAGING_INCDIR}/libxml2/ -I${STAGING_INCDIR}/${PYTHON_DIR}/"

do_compile() {
	echo ${PKGV} > ${S}/VERSION
	oe_runmake SWIG="swig"
}

do_install() {
	oe_runmake 'D=${D}' install
}
