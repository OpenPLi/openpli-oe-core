SUMMARY  = "Interface to the sendfile syscall"
HOMEPAGE = "https://github.com/giampaolo/pysendfile"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42fd5943ce8e80da4f2e57cb3a73489d"

inherit setuptools3 gittag

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/giampaolo/pysendfile.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

include python3-package-split.inc
