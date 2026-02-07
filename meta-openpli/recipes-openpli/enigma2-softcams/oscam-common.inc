require conf/license/openpli-gplv2.inc
require softcam.inc
inherit gittag cmake

DESCRIPTION = "${CAMTITLE}"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI := "${SRC_ORIGIN} "

DEPENDS = "libusb openssl libdvbcsa openssl-native upx-native pcsc-lite"
RDEPENDS:${PN} += "enigma2-plugin-extensions-oscamstatus libdvbcsa libusb1 pcsc-lite pcsc-tools"

LDFLAGS:prepend = "-ludev -ldvbcsa "

S = "${WORKDIR}/git"
B = "${S}"

CAMSTART = "/usr/bin/${CAMNAME} --wait 60 --config-dir /etc/tuxbox/config/${CAMNAME} --daemon --pidfile /tmp/${CAMNAME}.pid --restart 2"
CAMSTOP = "kill \`cat /tmp/${CAMNAME}.pid\` 2> /dev/null"

SRC_URI += " \
	file://oscam.conf \
	file://oscam.server \
	file://oscam.srvid \
	file://oscam.user \
	file://oscam.dvbapi \
	file://oscam.provid"

CONFFILES = "${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.conf ${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.server ${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.srvid ${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.user ${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.dvbapi ${sysconfdir}/tuxbox/config/${CAMNAME}/oscam.provid"

FILES:${PN} = "${bindir}/${CAMNAME} ${sysconfdir}/tuxbox/config/${CAMNAME}/* ${sysconfdir}/init.d/softcam.${CAMNAME}"

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=0 \
	-DHAVE_LIBUSB=1 \
	-DSTATIC_LIBUSB=0 \
	-DWITH_SSL=1 \
	-DIPV6SUPPORT=1 \
	-DCLOCKFIX=0 \
	-DHAVE_PCSC=1 \
	-DCARDREADER_SMARGO=1 \
	-DCARDREADER_PCSC=1 \
	-DCW_CYCLE_CHECK=1 \
	-DCS_CACHEEX=1 \
	-DMODULE_CONSTCW=1 \
	-DLCDSUPPORT=1 \
	-DMODULE_SCAM=1 \
	-DMODULE_STREAMRELAY=1 \
	-DHAVE_LIBDVBCSA=1 \
	-DWITH_EXTENDED_CW=1 \
	${@bb.utils.contains_any("TUNE_FEATURES", "neon simd", "-DWITH_ARM_NEON=1", "", d)} \
	-DWITH_SIGNING=1 \
	-DUSE_COMPRESS=1 \
	-DCOMP_LEVEL="--best" \
	"

do_configure:prepend () {
	rm -rf ${S}/certs
	${S}/config.sh --create-cert ecdsa prime256v1 ca "OpenPLi OSCam Distribution"
}

do_install() {
	install -d ${D}${sysconfdir}/tuxbox/config/${CAMNAME}
	install -m 0644 ${WORKDIR}/oscam.* ${D}${sysconfdir}/tuxbox/config/${CAMNAME}/
	install -d ${D}${bindir}
	install -m 0755 ${B}/oscam-upx ${D}${bindir}/${CAMNAME}
}
