DEPENDS += "libxml2"

PACKAGECONFIG_append += "mp3lame libvorbis vpx"

MIPSFPU = "${@bb.utils.contains('TARGET_FPU', 'soft', '--disable-mipsfpu', '--enable-mipsfpu', d)}"

EXTRA_FFCONF = " \
    --disable-static \
    --disable-runtime-cpudetect \
    --enable-ffprobe \
    --disable-altivec \
    --disable-amd3dnow \
    --disable-amd3dnowext \
    --disable-mmx \
    --disable-mmxext \
    --disable-sse \
    --disable-sse2 \
    --disable-sse3 \
    --disable-ssse3 \
    --disable-sse4 \
    --disable-sse42 \
    --disable-avx \
    --disable-xop \
    --disable-fma3 \
    --disable-fma4 \
    --disable-avx2 \
    --enable-inline-asm \
    --enable-asm \
    --disable-x86asm \
    --disable-fast-unaligned \
    --enable-muxers \
    --enable-encoders \
    --enable-decoders \
    --enable-demuxers \
    --enable-parsers \
    --enable-bsfs \
    --enable-protocols \
    --enable-indevs \
    --enable-outdevs \
    --enable-filters \
    --disable-doc \
    --disable-htmlpages \
    --disable-manpages \
    --disable-podpages \
    --disable-txtpages \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "${MIPSFPU} --disable-vfp --disable-neon --disable-mipsdsp --disable-mipsdspr2", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "--enable-armv6 --enable-armv6t2 --enable-vfp --enable-neon", "", d)} \
    ${@bb.utils.contains("TUNE_FEATURES", "aarch64", "--enable-armv8 --enable-vfp --enable-neon", "", d)} \
    --disable-debug \
    --pkg-config="pkg-config" \
    --enable-zlib \
    --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} -ffunction-sections -fdata-sections -fno-aggressive-loop-optimizations" \
    --extra-ldflags="${TARGET_LDFLAGS},--gc-sections -Wl,--print-gc-sections,-lrt" \
    --prefix=${prefix} \
"
