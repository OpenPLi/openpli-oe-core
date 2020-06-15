SECTION = "base"
LICENSE = "CLOSED"

DEPENDS = "virtual/libgles2 virtual/egl"

require kodi-vuplus.inc

# The driver is a set of binary libraries to install
# there's nothing to configure or compile
do_configure[noexec] = "1"

do_install(){
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/xbmc-support/xbmc.helper ${D}${bindir}
    install -m 0755 ${WORKDIR}/xbmc-support/libxbmc_base.so ${D}${libdir}
}

FILES_SOLIBSDEV = ""
FILES_${PN} = " ${bindir}/xbmc.helper ${libdir}/libxbmc_base.so"

INSANE_SKIP_${PN}_append += "already-stripped dev-so"
