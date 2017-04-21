DESCRIPTION = "Automatic full backup and manual flashing image"
HOMEPAGE = "https://github.com/Dima73/automatic-full-backup"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=f521231b9317995c51bdc211746b2802"
SRC_URI = "git://github.com/Dima73/automatic-full-backup.git"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit allarch distutils-openplugins

RDEPENDS_${PN} = " \
	mtd-utils-ubifs \
	mtd-utils \
	ofgwrite \
	zip \
	"
