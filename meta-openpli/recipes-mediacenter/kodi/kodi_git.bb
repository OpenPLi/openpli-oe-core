SUMMARY = "Kodi Media Center"
DESCRIPTION = "Kodi is an award-winning free and open source home theater/media \ 
center software and entertainment hub for digital media. With its beautiful \
interface and powerful skinning engine, it's available for Android, BSD, Linux, \
macOS, iOS and Windows."

HOMEPAGE = "https://kodi.tv/"
BUGTRACKER = "https://github.com/xbmc/xbmc/issues"

PACKAGE_ARCH = "${MACHINE_ARCH}"

require kodi.inc
require kodi-extra.inc

inherit cmake pkgconfig gettext python3-dir python3native

DEPENDS += " \
autoconf-native \
automake-native \
curl-native \
flatbuffers-native \
git-native \
googletest-native \
gperf-native \
kodi-tools-jsonschemabuilder-native \
nasm-native \
swig-native \
kodi-tools-texturepacker-native \
unzip-native \
yasm-native \
zip-native \
\
alsa-lib \
avahi \
bzip2 \
crossguid \
curl \
dcadec \
faad2 \
ffmpeg \
flatbuffers \
fmt \
fstrcmp \
fontconfig \
fribidi \
glib-2.0 \
giflib \
harfbuzz \
libass \
libbluray \
libcdio \
libcec \
libdvdnav \
libdvdcss \
libdvdread \
libudfread \
libinput \
libmicrohttpd \
libnfs \
libpcre \
libplist \
libssh \
libsquish \
libtinyxml \
libxkbcommon \
libxml2 \
libxslt \
lzo \
mpeg2dec \
python3 \
p8platform \
rapidjson \
samba \
spdlog \
sqlite3 \
taglib \
udev \
virtual/egl \
wavpack \
zlib \
 "

# 'patch' doesn't support binary diffs
PATCHTOOL = "git"


# stb, egl, players
SRC_URI_append = " \
           file://0001-flatbuffers.patch \
           file://0002-readd-Touchscreen-settings.patch \
           file://0003-shader-nopow.patch \
           file://0004-stb-support.patch \
           file://0005-use-mute-button-as-ok-button.patch \
            \
           "

# stb, egl, players
SRC_URI_append = " \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-cortexa15', 'file://egl/0001-EGLNativeTypeV3DNXPL.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-mipsel', 'file://egl/0001-EGLNativeTypeV3DNXPL.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'edision-cortexa15', 'file://egl/0001-EGLNativeTypeEdision.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'xcore-mipsel', 'file://egl/0001-EGLNativeTypeV3D.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'nextv-cortexa15', 'file://egl/0001-EGLNativeTypeDags.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'GB-cortexa15', 'file://egl/0001-EGLNativeTypeV3D-gb4k.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-mipsel', 'file://egl/0001-EGLNativeTypeVuplus.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-cortexa15', 'file://egl/0001-EGLNativeTypeVuplus4k.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://egl/0001-EGLNativeTypeMali.patch', '', d)} \
            \
            ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://HiPlayer.patch file://HiPlayer-Subs.patch file://defaultplayer-HiPlayer.patch', \
                                                              'file://E2Player.patch', d)} \
           "


# breaks compilation
CCACHE_DISABLE = "1"
ASNEEDED = ""

ACCEL ?= ""
ACCEL:x86 = "vaapi vdpau"
ACCEL:x86-64 = "vaapi vdpau"

WINDOWSYSTEM ?= "stb"

APPRENDERSYSTEM ?= "gles"
PACKAGECONFIG ?= "${ACCEL} ${WINDOWSYSTEM} pulseaudio lcms lto \
                            ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                            ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)}"

# Core windowing system choices

PACKAGECONFIG[x11] = "-DCORE_PLATFORM_NAME=x11,,libxinerama libxmu libxrandr libxtst glew"
PACKAGECONFIG[gbm] = "-DCORE_PLATFORM_NAME=gbm -DGBM_RENDER_SYSTEM=gles,,"
PACKAGECONFIG[stb] = "-DCORE_PLATFORM_NAME=stb,,"
PACKAGECONFIG[amlogic] = "-DCORE_PLATFORM_NAME=aml,,"
PACKAGECONFIG[wayland] = "-DCORE_PLATFORM_NAME=wayland -DWAYLAND_RENDER_SYSTEM=gles,,wayland waylandpp"

# Features

PACKAGECONFIG[opengl] = "-DENABLE_OPENGL=ON,,"
PACKAGECONFIG[openglesv2] = "-DENABLE_GLES=ON,,virtual/egl"
PACKAGECONFIG[bluetooth] = ",,bluez5"
PACKAGECONFIG[samba] = ",,samba"
PACKAGECONFIG[lcms] = ",,lcms"
PACKAGECONFIG[vaapi] = "-DENABLE_VAAPI=ON,-DENABLE_VAAPI=OFF,libva"
PACKAGECONFIG[vdpau] = "-DENABLE_VDPAU=ON,-DENABLE_VDPAU=OFF,libvdpau"
PACKAGECONFIG[mysql] = "-DENABLE_MYSQLCLIENT=ON,-DENABLE_MYSQLCLIENT=OFF,mysql5"
PACKAGECONFIG[pulseaudio] = "-DENABLE_PULSEAUDIO=ON,-DENABLE_PULSEAUDIO=OFF,pulseaudio"
PACKAGECONFIG[lcms] = ",,lcms"

# Compilation tunes

PACKAGECONFIG[gold] = "-DENABLE_LDGOLD=ON,-DENABLE_LDGOLD=OFF"
PACKAGECONFIG[lto] = "-DUSE_LTO=${@oe.utils.cpu_count()},-DUSE_LTO=OFF"
PACKAGECONFIG[testing] = "-DENABLE_TESTING=ON,-DENABLE_TESTING=0FF,googletest"

LDFLAGS += "${TOOLCHAIN_OPTIONS}"
LDFLAGS_append_mipsarch = " -latomic -lpthread"
LDFLAGS_append_arm = " -lpthread"
EXTRA_OECMAKE_append_mipsarch = " -DWITH_ARCH=${TARGET_ARCH}"

# Allow downloads during internals build
do_compile[network] = "1"



EXTRA_OECMAKE = " \
    ${KODI_ARCH} \
    -DUSE_INTERNAL_LIBS=OFF \
    -DAPP_RENDER_SYSTEM=${APPRENDERSYSTEM} \
    \
    -DNATIVEPREFIX=${STAGING_DIR_NATIVE}${prefix} \
    -DJava_JAVA_EXECUTABLE=/usr/bin/java \
    -DWITH_TEXTUREPACKER=${STAGING_BINDIR_NATIVE}/TexturePacker \
    -DWITH_JSONSCHEMABUILDER=${STAGING_BINDIR_NATIVE}/JsonSchemaBuilder \
    \
    -DENABLE_STATIC_LIBS=FALSE \
    -DCMAKE_NM='${NM}' \
    \
    -DFFMPEG_PATH=${STAGING_DIR_TARGET} \
    -DENABLE_INTERNAL_CROSSGUID=OFF \
    -DENABLE_INTERNAL_RapidJSON=OFF \
    -DENABLE_INTERNAL_FLATBUFFERS=OFF \
    -DENABLE_INTERNAL_FMT=OFF \
    -DENABLE_INTERNAL_FSTRCMP=0 \
    -DENABLE_INTERNAL_FFMPEG=OFF \
    -DLIBDVD_INCLUDE_DIRS=${STAGING_INCDIR} \
    -DNFS_INCLUDE_DIR=${STAGING_INCDIR} \
    -DSHAIRPLAY_INCLUDE_DIR=${STAGING_INCDIR} \
    \
    -DENABLE_AIRTUNES=ON \
    -DENABLE_OPTICAL=OFF \
    -DENABLE_DVDCSS=OFF \
    -DENABLE_DEBUGFISSION=OFF \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    \
    ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-cortexa15', '-DWITH_PLATFORM="v3d-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-mipsel', '-DWITH_PLATFORM="v3d-mipsel"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'edision-cortexa15', '-DWITH_PLATFORM="edision-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'dags-cortexa15', '-DWITH_PLATFORM="dags-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'xcore-mipsel', '-DWITH_PLATFORM="xcore-mipsel"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'nextv-cortexa15', '-DWITH_PLATFORM="nextv-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'GB-cortexa15', '-DWITH_PLATFORM="GB-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-mipsel', '-DWITH_PLATFORM="vuplus-mipsel"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-cortexa15', '-DWITH_PLATFORM="vuplus-cortexa15"', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali', '-DWITH_PLATFORM="mali-cortexa15"', '', d)} \
"


FULL_OPTIMIZATION:armv7a = "-fomit-frame-pointer -O3 -ffast-math"
FULL_OPTIMIZATION:armv7ve = "-fomit-frame-pointer -O3 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export ${PYTHON_DIR}

export TARGET_PREFIX

do_configure:prepend() {
	# Ensure 'nm' can find the lto plugins 
	liblto=$(find ${STAGING_DIR_NATIVE} -name "liblto_plugin.so.0.0.0")
	mkdir -p ${STAGING_LIBDIR_NATIVE}/bfd-plugins
	ln -sf $liblto ${STAGING_LIBDIR_NATIVE}/bfd-plugins/liblto_plugin.so

	sed -i -e 's:CMAKE_NM}:}${TARGET_PREFIX}gcc-nm:' ${S}/xbmc/cores/DllLoader/exports/CMakeLists.txt
}

INSANE_SKIP:${PN} = "rpaths"

FILES:${PN} += "${datadir}/metainfo ${datadir}/xsessions ${datadir}/icons ${libdir}/xbmc ${datadir}/xbmc ${libdir}/firewalld"
FILES:${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS:${PN}:append = " \
                             libcec \
                             libcurl \
                             libnfs \
                             nss \
                             os-release \
                             ${@bb.utils.contains('PACKAGECONFIG', 'x11', 'xrandr xinit mesa-demos', '', d)} \
                             ${PYTHON_PN} \
                             ${PYTHON_PN}-compression \
                             ${PYTHON_PN}-ctypes \
                             ${PYTHON_PN}-netclient \
                             ${PYTHON_PN}-difflib \
                             ${PYTHON_PN}-html \
                             ${PYTHON_PN}-json \
                             ${PYTHON_PN}-mechanize \
                             ${PYTHON_PN}-multiprocessing \
                             ${PYTHON_PN}-profile \
                             ${PYTHON_PN}-pycryptodome \
                             ${PYTHON_PN}-pycryptodomex \
                             ${PYTHON_PN}-regex \
                             ${PYTHON_PN}-shell \
                             ${PYTHON_PN}-sqlite3 \
                             ${PYTHON_PN}-xmlrpc \
                             tzdata-africa \
                             tzdata-americas \
                             tzdata-antarctica \
                             tzdata-arctic \
                             tzdata-asia \
                             tzdata-atlantic \
                             tzdata-australia \
                             tzdata-europe \
                             tzdata-pacific \
                             xkeyboard-config \
                             kodi-addon-inputstream-adaptive \
                             kodi-addon-inputstream-rtmp \
                             alsa-plugins \
                           "

RRECOMMENDS:${PN}:append:libc-glibc = " \
  glibc-charmap-ibm850 \
  glibc-gconv-ibm850 \
  glibc-charmap-ibm437 \
  glibc-gconv-ibm437 \
  glibc-gconv-unicode \
  glibc-gconv-utf-32 \
  glibc-charmap-utf-8 \
  glibc-localedata-en-us \
"
