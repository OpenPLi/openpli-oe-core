SUMMARY = "Minisatip SAT>IP server"
MAINTAINER = "catalinii"
require conf/license/openpli-gplv2.inc

HOMEPAGE = "https://minisatip.org/"
DEPENDS = "libdvbcsa openssl"
RDEPENDS:${PN} = "libdvbcsa openssl"

SRC_URI = " \
    git://github.com/catalinii/minisatip.git;protocol=http;branch=master;protocol=https \
    file://minisatip.sh \
    "

UPSTREAMVERSION = "1.0d"
PV = "${UPSTREAMVERSION}+git${SRCPV}"

S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/git"

inherit autotools-brokensep update-rc.d

INITSCRIPT_NAME = "minisatip"

INITSCRIPT_PARAMS = "defaults 80"

EXTRA_OECONF = "--disable-netcv"

do_configure:prepend () {
}

do_install () {
    install -d -m 0755 ${D}/${bindir}
    install -d -m 0755 ${D}/${datadir}/${PN}
    install -d -m 0755 ${D}/etc/init.d
    install -m 0755 ${S}/minisatip ${D}/${bindir}/
    install -m 0755 ${WORKDIR}/minisatip.sh ${D}/etc/init.d/minisatip
    cp -r --preserve=timestamps ${S}/html ${D}/${datadir}/${PN}
}
