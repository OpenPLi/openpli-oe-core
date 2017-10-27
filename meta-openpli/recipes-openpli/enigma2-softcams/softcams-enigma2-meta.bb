DESCRIPTION = "meta file for enigma2 softcam packages"

require conf/license/openpli-gplv2.inc

PROVIDES = "softcams"

DEPENDS_append_mipsel = "\
	enigma2-plugin-softcams-cccam \
	enigma2-plugin-softcams-cccam209 \
	enigma2-plugin-softcams-cccam221 \
	enigma2-plugin-softcams-rqcamd \
	"
DEPENDS += "enigma2-plugin-softcams-oscam enigma2-plugin-softcams-oscam-emu"
