DESCRIPTION = "meta file for enigma2 softcam packages"

require conf/license/openpli-gplv2.inc

PROVIDES = "softcams"

# mipsel only binary softcams (mips32el and mips32el-nf arch)
DEPENDS_append_mipsel = "\
	enigma2-plugin-softcams-cccam \
	enigma2-plugin-softcams-cccam209 \
	enigma2-plugin-softcams-cccam221 \
	enigma2-plugin-softcams-cccam230 \
	enigma2-plugin-softcams-rqcamd \
	"

# armv7a only binary softcams (armv7ahf arch)
DEPENDS_append_armv7a = " \
	enigma2-plugin-softcams-cccam \
	"
# armv7ve only binary softcams (cortexa15hf arch)
DEPENDS_append_armv7ve = " \
	enigma2-plugin-softcams-cccam \
	"

# softcams with source available
DEPENDS += " \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-softcams-oscam-emu \
	"
