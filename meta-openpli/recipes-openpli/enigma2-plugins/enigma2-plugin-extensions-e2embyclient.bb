DESCRIPTION = "An enigma2 client for Emby servers"
MAINTAINER = "DimitarCC"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
HOMEPAGE = "https://github.com/DimitarCC/e2-emby-client"

RDEPENDS:${PN} = "python3-pillow python3-requests"

inherit gittag allarch gettext python3-compileall setuptools3-openplugins

PV = "git"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/DimitarCC/e2-emby-client.git;protocol=https;branch=main"

S = "${WORKDIR}/git"
