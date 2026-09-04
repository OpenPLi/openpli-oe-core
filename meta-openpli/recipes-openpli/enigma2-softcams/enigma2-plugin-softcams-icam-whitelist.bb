DESCRIPTION = "NDS CSA whitelist, list of service references using NDS CSA scrambling"
MAINTAINER = "AbuBaniaz"
LICENSE = "CLOSED"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_ORIGIN ?= "git://github.com/E2OpenPlugins/oscam-whitelist.git;protocol=https;branch=main"
SRC_URI := "${SRC_ORIGIN} "

S = "${WORKDIR}/git"

RCONFLICTS:${PN} = "enigma2-plugins-softcams-oscam-whitelist enigma2-plugins-softcams-csa-whitelist"
RREPLACES:${PN} = "enigma2-plugins-softcams-oscam-whitelist enigma2-plugins-softcams-csa-whitelist"

inherit allarch gitpkgv

do_install () {
	install -d ${D}${sysconfdir}/enigma2/
	if [ -f ${D}${sysconfdir}/enigma2/whitelist_streamrelay ]; then
		rm -f ${D}${sysconfdir}/enigma2/whitelist_streamrelay
	fi
	cp -r ${S}/whitelist_streamrelay ${D}${sysconfdir}/enigma2
}
