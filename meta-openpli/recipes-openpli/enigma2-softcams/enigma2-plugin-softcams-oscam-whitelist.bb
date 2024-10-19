DESCRIPTION = "OsCam iCam whitelist"
MAINTAINER = "AbuBaniaz"
LICENSE = "CLOSED"

PV = "1.0+${DATE}"

SRC_URI = "https://raw.githubusercontent.com/biko-73/OsCam_EMU/refs/heads/main/whitelist_streamrelay"

BB_STRICT_CHECKSUM = "0"

DEPENDS = "enigma2-plugin-softcams-oscam"

inherit allarch


do_install () {
    install -d ${D}${sysconfdir}/enigma2/
	        if [ -e ${S}/whitelist_streamrelay ]; then
		rm -f ${S}/whitelist_streamrelay
	fi
   cp -r ${WORKDIR}/whitelist_streamrelay ${D}${sysconfdir}/enigma2
}
