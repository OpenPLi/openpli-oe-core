DESCRIPTION = "Utils for blindscan"
SECTION = "base"
PRIORITY = "optional"
RDEPENDS:${PN} = "ncurses"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "vuplus-blindscan-utils-${MACHINE}-dbg \
            vuplus-blindscan-dvbs-utils-${MACHINE} \
            vuplus-blindscan-dvbc-utils-${MACHINE} \
            "

PROVIDES += "virtual/blindscan-dvbs virtual/blindscan-dvbc"
RPROVIDES:vuplus-blindscan-dvbs-utils-${MACHINE} += "virtual-blindscan-dvbs"
RPROVIDES:vuplus-blindscan-dvbc-utils-${MACHINE} += "virtual-blindscan-dvbc"

PV = "5.1"
PR = "r0"

SRC_URI = "http://downloads.openpli.org/archive/vuplus/vuplus-blindscan-utils-${PV}_arm.tar.bz2"

SRC_URI[md5sum] = "1744184485bdc6889f6556504e574c6a"
SRC_URI[sha256sum] = "518775bf3ff43ade300741232ce4d303d4aeaea09f1a7d7256e40a4fe95156ca"

S = "${WORKDIR}/blindscan-utils"

PLUGABLE_ARM_BLINDSCAN = " \
	vuplus_blindscan \
	vuplus_6211_blindscan \
	vuplus_6222_blindscan \
	ssh108 \
	ssh108_t2_scan \
	tt3l10 \
	tt3l10_t2_scan \
	tt2l08 \
	tt2l08_t2_scan \
	bcm3148 \
	bcm3466 \
"

do_install() {
	install -d "${D}/${bindir}"
	install -m 0755 "${S}/tda1002x" "${D}/${bindir}"
}
do_install:vusolo4k() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuuno4k() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuultimo4k() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuuno4kse() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuzero4k() {
	install -d "${D}/${bindir}"
	install -m 0755 "${S}/tt2l08b" "${D}/${bindir}/tt2l08"
	install -m 0755 "${S}/tt2l08b_t2_scan" "${D}/${bindir}/tt2l08_t2_scan"
	install -m 0755 "${S}/vuplus_si2166_blindscan" "${D}/${bindir}/vuplus_si2166_blindscan"
}

do_install:vuduo4k() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuduo4kse() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

do_install:vuduo4klite() {
	install -d "${D}/${bindir}"
	for f in ${PLUGABLE_ARM_BLINDSCAN}; do
		install -m 0755 "${S}/$f" "${D}/${bindir}"
	done
}

FILES:vuplus-blindscan-dvbs-utils-${MACHINE} = "\
        ${bindir}/vuplus_blindscan \
        ${bindir}/vuplus_6211_blindscan \
        ${bindir}/vuplus_6222_blindscan \
        ${bindir}/vuplus_si2166_blindscan \
        "

FILES:vuplus-blindscan-dvbc-utils-${MACHINE} = "\
        ${bindir}/ssh108 \
        ${bindir}/tt3l10 \
        ${bindir}/tt2l08 \
        ${bindir}/bcm3148 \
        ${bindir}/ssh108_t2_scan \
        ${bindir}/tt3l10_t2_scan \
        ${bindir}/tt2l08_t2_scan \
        ${bindir}/bcm3466 \
        "

INSANE_SKIP = "32bit-time"
