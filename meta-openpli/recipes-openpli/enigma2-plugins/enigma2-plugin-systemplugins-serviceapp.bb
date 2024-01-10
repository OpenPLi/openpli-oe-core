DESCRIPTION = "serviceapp service for enigma2"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "enigma2 uchardet openssl"
RDEPENDS:${PN} = "enigma2 uchardet openssl python3-json"
RRECOMMENDS:${PN} = "exteplayer3 gstplayer"

SRC_URI = "git://github.com/mx3L/serviceapp.git;protocol=https;branch=develop \
			file://update-devel-m4-file.patch \
			file://remove-redundant-c17-check.patch \
"

S = "${WORKDIR}/git"

inherit autotools gitpkgv python3-compileall pkgconfig gettext

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

FILES:${PN} = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/"

FILES:${PN}-dev = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp.la"

