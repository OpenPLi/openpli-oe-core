DESCRIPTION = "Event Loop Library"
HOMEPAGE = "http://software.schmorp.de/pkg/libev.html"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6ad416afd040c90698edcdf1cbee347"
SRC_URI = "http://dist.schmorp.de/libev/libev-${PV}.tar.gz"

inherit autotools

SRC_URI[md5sum] = "f1dbf786ead8307e0d4f5f68f2f8526c"
SRC_URI[sha256sum] = "c7fe743e0c3b50dd34bf222ebdba4e8acac031d41ce174f17890f8f84eeddd7a"


do_compile_prepend() {
	sed -i 's#include_HEADERS = ev.h ev++.h event.h#include_HEADERS = ev.h ev++.h#' ${S}/Makefile.*
	sed -i 's#libev_la_SOURCES = ev.c event.c#libev_la_SOURCES = ev.c#' ${S}/Makefile.*
}

