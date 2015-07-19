DESCIPTION = "helper tool to deliver hotplug events to e2"
MAINTAINER = "PLi team"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://bdpoll.c;startline=9;endline=20;md5=d903287e43d72c0f608fd5b718e88450"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRCREV = "02e6879ffcf2a0aa9c83b355b52108f1e5f4f56c"
GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/${BPN}.git"

S = "${WORKDIR}/git"

inherit autotools
