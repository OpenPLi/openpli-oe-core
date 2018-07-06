DESCRIPTION = "meta file for enigma2 softcam packages"

require conf/license/openpli-gplv2.inc

PROVIDES = "softcams"

# mipsel only softcams
DEPENDS_append_mipsel = "\
	enigma2-plugin-softcams-cccam209 \
	enigma2-plugin-softcams-cccam221 \
	enigma2-plugin-softcams-cccam230 \
	enigma2-plugin-softcams-rqcamd \
	"

# other softcams
DEPENDS += " \
	${@bb.utils.contains("TARGET_ARCH", "aarch64", "", "enigma2-plugin-softcams-cccam", d)} \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-softcams-oscam-emu \
	"
