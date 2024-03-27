# use a patched version that supports ECMs

SRC_URI = "git://github.com/oe-mirrors/libdvbcsa.git;protocol=https;branch=master \
	file://libdvbcsa.pc \
	"

TUNE_32_64   = "${@bb.utils.contains("TUNE_FEATURES", "mips64",        "--enable-uint64",  "--enable-uint32", d)}"
TUNE_DVBCSA  = "${@bb.utils.contains_any("TUNE_FEATURES", "neon simd", "--enable-neon",    "${TUNE_32_64}", d)}"

EXTRA_OECONF += "${TUNE_DVBCSA}"
