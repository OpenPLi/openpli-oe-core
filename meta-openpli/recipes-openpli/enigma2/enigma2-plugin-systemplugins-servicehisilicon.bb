DESCRIPTION = "hisilicon service for enigma2"
AUTHOR = "zgemma-star"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "virtual/enigma2-mediaservice"
RPROVIDES:${PN} += "virtual/enigma2-mediaservice"

DEPENDS = "enigma2"
RDEPENDS:${PN} = "enigma2"

SRCREV = "${AUTOREV}"
SERVICEHISILICON_BRANCH ?= "main"
SRC_URI = "git://github.com/OpenPLi/servicehisilicon.git;branch=${SERVICEHISILICON_BRANCH};protocol=https"

S = "${WORKDIR}/git"

inherit autotools gitpkgv python3-compileall

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

FILES:${PN} = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceHisilicon/*.pyc \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceHisilicon/servicehisilicon.so"

FILES:${PN}-dev = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceHisilicon/*.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceHisilicon/ServiceHisilicon.la"
