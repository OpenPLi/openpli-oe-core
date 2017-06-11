RSUGGESTS_${PN} = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PROVIDES =+ " libavcodec53 libavformat53 "
RDEPENDS_${PN} =+ " libbluray rtmpdump libxml2 openssl "
DEPENDS =+ " libxml2 "

PACKAGECONFIG[librtmp] = "--enable-librtmp,--disable-librtmp,rtmpdump"
PACKAGECONFIG[libbluray] = "--enable-libbluray --enable-protocol=bluray,--disable-libbluray,libbluray"

PACKAGECONFIG = "avdevice avfilter avcodec avformat swresample swscale postproc \
		bzlib gpl theora x264 openssl avresample libvorbis vpx librtmp libbluray"

PACKAGES =+ " libavcodec53 libavformat53 libav "

MIPSFPU = "${@bb.utils.contains('TARGET_FPU', 'soft', '--disable-mipsfpu', '--enable-mipsfpu', d)}"

SRC_URI_append = " \
    file://ffmpeg-fix-hls.patch \
    file://ffmpeg-buffer-size.patch \
    file://ffmpeg-aac.patch \
    file://ffmpeg-fix-mpegts.patch \
    file://000003_allow_to_choose_rtmp_impl_at_runtime.patch \
    file://ffmpeg-fix-edit-list-parsing.patch \
    file://000001_add_dash_demux.patch \
"

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
    --disable-armv5te \
    --disable-armv6 \
    --disable-armv6t2 \
    --disable-vfp \
    --disable-neon \
    --disable-inline-asm \
    --disable-yasm \
    --disable-fast-unaligned \
    --disable-muxers \
    --enable-muxer=mpeg1video \
    --enable-encoders \
    --enable-encoder=mpeg1video \
    --enable-encoder=png \
    --disable-decoders \
    --enable-decoder=alac \
    --enable-decoder=ape \
    --enable-decoder=atrac1 \
    --enable-decoder=atrac3 \
    --enable-decoder=atrac3p \
    --enable-decoder=cook \
    --enable-decoder=dca \
    --enable-decoder=dsd_lsbf \
    --enable-decoder=dsd_lsbf_planar \
    --enable-decoder=dsd_msbf \
    --enable-decoder=dsd_msbf_planar \
    --enable-decoder=eac3 \
    --enable-decoder=evrc \
    --enable-decoder=h264 \
    --enable-decoder=iac \
    --enable-decoder=imc \
    --enable-decoder=mace3 \
    --enable-decoder=mace6 \
    --enable-decoder=metasound \
    --enable-decoder=mjpeg \
    --enable-decoder=mlp \
    --enable-decoder=mp1 \
    --enable-decoder=mp3 \
    --enable-decoder=mp3adu \
    --enable-decoder=mp3on4 \
    --enable-decoder=mpeg1video \
    --enable-decoder=nellymoser \
    --enable-decoder=pcm_alaw \
    --enable-decoder=pcm_bluray \
    --enable-decoder=pcm_dvd \
    --enable-decoder=pcm_f32be \
    --enable-decoder=pcm_f32le \
    --enable-decoder=pcm_f64be \
    --enable-decoder=pcm_f64le \
    --enable-decoder=pcm_lxf \
    --enable-decoder=pcm_mulaw \
    --enable-decoder=pcm_s16be \
    --enable-decoder=pcm_s16be_planar \
    --enable-decoder=pcm_s16le \
    --enable-decoder=pcm_s16le_planar \
    --enable-decoder=pcm_s24be \
    --enable-decoder=pcm_s24daud \
    --enable-decoder=pcm_s24le \
    --enable-decoder=pcm_s24le_planar \
    --enable-decoder=pcm_s32be \
    --enable-decoder=pcm_s32le \
    --enable-decoder=pcm_s32le_planar \
    --enable-decoder=pcm_s8 \
    --enable-decoder=pcm_s8_planar \
    --enable-decoder=pcm_u16be \
    --enable-decoder=pcm_u16le \
    --enable-decoder=pcm_u24be \
    --enable-decoder=pcm_u24le \
    --enable-decoder=pcm_u32be \
    --enable-decoder=pcm_u32le \
    --enable-decoder=pcm_u8 \
    --enable-decoder=pcm_zork \
    --enable-decoder=png \
    --enable-decoder=ra_144 \
    --enable-decoder=ra_288 \
    --enable-decoder=ralf \
    --enable-decoder=s302m \
    --enable-decoder=shorten \
    --enable-decoder=sipr \
    --enable-decoder=sonic \
    --enable-decoder=tak \
    --enable-decoder=truehd \
    --enable-decoder=truespeech \
    --enable-decoder=tta \
    --enable-decoder=wmalossless \
    --enable-decoder=wmapro \
    --enable-decoder=wmav1 \
    --enable-decoder=wmav2 \
    --enable-decoder=wmavoice \
    --enable-decoder=aac \
    --enable-decoder=aac_latm \
    --enable-decoder=libfdk_aac \
    --enable-decoder=adpcm_ct \
    --enable-decoder=adpcm_g722 \
    --enable-decoder=adpcm_g726 \
    --enable-decoder=adpcm_g726le \
    --enable-decoder=adpcm_ima_amv \
    --enable-decoder=adpcm_ima_oki \
    --enable-decoder=adpcm_ima_qt \
    --enable-decoder=adpcm_ima_rad \
    --enable-decoder=adpcm_ima_wav \
    --enable-decoder=adpcm_ms \
    --enable-decoder=adpcm_sbpro_2 \
    --enable-decoder=adpcm_sbpro_3 \
    --enable-decoder=adpcm_sbpro_4 \
    --enable-decoder=adpcm_swf \
    --enable-decoder=adpcm_yamaha \
    --enable-decoder=flac \
    --enable-decoder=g723_1 \
    --enable-decoder=g729 \
    --enable-decoder=opus \
    --enable-decoder=qcelp \
    --enable-decoder=qdm2 \
    --enable-decoder=vorbis \
    --enable-decoder=wavpack \
    --disable-demuxer=adp \
    --disable-demuxer=adx \
    --disable-demuxer=afc \
    --disable-demuxer=anm \
    --disable-demuxer=apc \
    --disable-demuxer=ast \
    --disable-demuxer=avs \
    --disable-demuxer=bethsoftvid \
    --disable-demuxer=bfi \
    --disable-demuxer=bink \
    --disable-demuxer=bmv \
    --disable-demuxer=brstm \
    --disable-demuxer=c93 \
    --disable-demuxer=cdg \
    --disable-demuxer=dnxhd \
    --disable-demuxer=dsicin \
    --disable-demuxer=dfa \
    --disable-demuxer=dxa \
    --disable-demuxer=ea \
    --disable-demuxer=ea_cdata \
    --disable-demuxer=frm \
    --disable-demuxer=gif \
    --disable-demuxer=gsm \
    --disable-demuxer=gxf \
    --disable-demuxer=hnm \
    --disable-demuxer=ico \
    --disable-demuxer=ilbc \
    --disable-demuxer=iss \
    --disable-demuxer=jv \
    --disable-demuxer=mm \
    --disable-demuxer=paf \
    --disable-demuxer=pva \
    --disable-demuxer=qcp \
    --disable-demuxer=redspark \
    --disable-demuxer=rl2 \
    --disable-demuxer=roq \
    --disable-demuxer=rsd \
    --disable-demuxer=rso \
    --disable-demuxer=siff \
    --disable-demuxer=smjpeg \
    --disable-demuxer=smush \
    --disable-demuxer=sol \
    --disable-demuxer=thp \
    --disable-demuxer=tiertexseq \
    --disable-demuxer=tmv \
    --disable-demuxer=tty \
    --disable-demuxer=txd \
    --disable-demuxer=vqf \
    --disable-demuxer=wsaud \
    --disable-demuxer=wsvqa \
    --disable-demuxer=xa \
    --disable-demuxer=xbin \
    --disable-demuxer=yop \
    --disable-demuxer=ingenient \
    --disable-demuxer=image_dds_pipe \
    --disable-demuxer=image_dpx_pipe \
    --disable-demuxer=image_exr_pipe \
    --disable-demuxer=image_j2k_pipe \
    --disable-demuxer=image_pictor_pipe \
    --disable-demuxer=image_qdraw_pipe \
    --disable-demuxer=image_sgi_pipe \
    --disable-demuxer=image_sunrast_pipe \
    --enable-demuxer=image2 \
    --disable-filters \
    --enable-filter=scale \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "", "${MIPSFPU}", d)} \
    --disable-debug \
    --pkg-config="pkg-config" \
    --enable-zlib \
    --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} -ffunction-sections -fdata-sections -fno-aggressive-loop-optimizations" \
    --extra-ldflags="${TARGET_LDFLAGS},--gc-sections -Wl,--print-gc-sections,-lrt" \
    --prefix=${prefix} \
"
