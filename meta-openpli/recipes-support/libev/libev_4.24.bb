DESCRIPTION = "Event Loop Library"
HOMEPAGE = "http://software.schmorp.de/pkg/libev.html"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6ad416afd040c90698edcdf1cbee347"
SRC_URI = "http://dist.schmorp.de/libev/libev-${PV}.tar.gz"

inherit autotools

SRC_URI[md5sum] = "94459a5a22db041dec6f98424d6efe54"
SRC_URI[sha256sum] = "973593d3479abdf657674a55afe5f78624b0e440614e2b8cb3a07f16d4d7f821"

do_compile_prepend() {
	sed -i 's#include_HEADERS = ev.h ev++.h event.h#include_HEADERS = ev.h ev++.h#' ${S}/Makefile.*
	sed -i 's#libev_la_SOURCES = ev.c event.c#libev_la_SOURCES = ev.c#' ${S}/Makefile.*
}

