SUMMARY = "Kodi platform support library"
DESCRIPTION = "Platform support library used by libCEC and binary add-ons for Kodi"
HOMEPAGE = "https://github.com/xbmc/kodi-platform"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/util/XMLUtils.cpp;beginline=2;endline=18;md5=dae8e846500e70dd8ecee55f3f018c30"

DEPENDS = "libtinyxml kodi"

SRCREV = "${AUTOREV}"
SRC_URI = " \
   git://github.com/xbmc/kodi-platform.git;protocol=https;branch=master \
   file://kodi-platform-01_crosscompile-badness.patch \
   file://kodi-platform-02_no-multi-lib.patch \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

OECMAKE_GENERATOR="Unix Makefiles"
EXTRA_OECMAKE = " \
   -DCMAKE_INSTALL_LIBDIR=${libdir} \
   -DCMAKE_INSTALL_LIBDIR_NOARCH=${libdir} \
   -DCMAKE_INSTALL_PREFIX_TOOLCHAIN=${STAGING_DIR_HOST}${prefix} \
   -DCMAKE_MODULE_PATH='${STAGING_DIR_HOST}${libdir}/kodi;${STAGING_DIR_HOST}${datadir}/kodi/cmake' \
   -DCMAKE_PREFIX_PATH=${STAGING_DIR_HOST}${prefix} \
   -DKODI_INCLUDE_DIR=${STAGING_INCDIR}/kodi \
"

do_compile:prepend() {
	sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' \
	       -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g' \
	          ${B}/CMakeFiles/kodiplatform.dir/flags.make
	sed -i -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g'\
	          ${B}/CMakeFiles/kodiplatform.dir/link.txt
}

do_install:append() {
	sed -i -e '/CXX/d' \
               -e '/CC/d' \
               -e 's:${STAGING_LIBDIR}:${libdir}:g' \
               -e 's:${STAGING_DIR_HOST}:/:g' \
               -e 's:${STAGING_DIR_NATIVE}:/:g' \
               -e 's:${prefix}/${libdir}:${libdir}:g' \
               -e 's:${WORKDIR}=::g' \ 
            ${D}${libdir}/pkgconfig/*.pc
}

RPROVIDES:${PN} += "libkodiplatform"
PACKAGES =+ "libkodiplatform"

FILES:libkodiplatform = "${libdir}/lib*.so.*"

FILES:${PN}-dev += "${libdir}/*platform"
