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
	enigma2-plugin-softcams-mgcamd145c \
	"

# armv7a only binary softcams (armv7ahf arch)
DEPENDS_append_armv7a = " \
	enigma2-plugin-softcams-cccam \
	enigma2-plugin-softcams-mgcamd135a \
	"
# armv7ve only binary softcams (cortexa15hf arch)
DEPENDS_append_armv7ve = " \
	enigma2-plugin-softcams-cccam \
	enigma2-plugin-softcams-mgcamd135a \
	"

# aarch64 only binary softcams (aarch64 arch)
#DEPENDS_append_aarch64 = " \
#	enigma2-plugin-softcams-mgcamd135a \
#	"

# softcams with source available
DEPENDS += " \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-softcams-oscam-emu \
	"
