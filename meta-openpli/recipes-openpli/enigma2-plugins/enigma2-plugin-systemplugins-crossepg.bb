DESCRIPTION = "Handle your EPG on enigma2 from various sources (opentv, xmltv, custom sources)"
HOMEPAGE = "https://github.com/oe-alliance/e2openplugin-CrossEPG"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "libxml2 zlib swig-native curl ${PYTHON_PN}"
RDEPENDS_${PN} += "enigma2 libcurl ${PYTHON_PN}-compression xz ${PYTHON_PN}-core"

inherit gitpkgv python3-compileall

SRC_URI = " git://github.com/oe-alliance/e2openplugin-CrossEPG.git;protocol=https;branch=dev \
			file://add-dummy-boxbranding.patch \
			file://make-huffman-root-structure-variable-extern.patch \
			file://fix-boxbranding-import.patch \
			"

PV = "0.9.0+gitr${SRCPV}"
PKGV = "0.9.0+gitr${GITPKGV}"
PR = "r0"

inherit ${PYTHON_PN}-dir ${PYTHON_PN}native

ALLOW_EMPTY_${PN} = "1"

CFLAGS_append = " -I${STAGING_INCDIR}/libxml2/ -I${STAGING_INCDIR}/${PYTHON_DIR}/"

# prevent lots of QA warnings
INSANE_SKIP_${PN} += "already-stripped"

S = "${WORKDIR}/git"

do_compile() {
    echo ${PV} > ${S}/VERSION
    oe_runmake SWIG="swig"
}

do_install() {
    oe_runmake 'D=${D}' install 'PYTHON_BASEVERSION=${PYTHON_BASEVERSION}'
    mv ${D}/usr/crossepg/libcrossepg.so ${D}${libdir}/
}

pkg_postrm_${PN}() {
    rm -fr ${libdir}/enigma2/python/Plugins/SystemPlugins/CrossEPG > /dev/null 2>&1
}

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}_append = " /usr/crossepg ${libdir}/libcrossepg.so ${libdir}/${PYTHON_DIR}"
FILES_${PN}-dbg_append = " /usr/crossepg/scripts/mhw2epgdownloader/.debug /usr/crossepg/scripts/mhw2epgdownloader/.debug"
FILES_SOLIBSDEV = ""
