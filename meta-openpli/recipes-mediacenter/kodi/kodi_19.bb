SUMMARY = "Kodi Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

PROVIDES += "kodi"
RPROVIDES_${PN} += "kodi"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit cmake gettext ${PYTHON_PN}-dir ${PYTHON_PN}native systemd

DEPENDS += " \
            fmt \
            flatbuffers flatbuffers-native \
            fstrcmp \
            rapidjson \
            crossguid \
            texturepacker-native \
            libdvdnav libdvdcss libdvdread \
            git-native \
            curl-native \
            googletest-native \
            gperf-native \
            jsonschemabuilder-native \
            nasm-native \
            swig-native \
            unzip-native \
            yasm-native \
            zip-native \
            \
            avahi \
            boost \
            bzip2 \
            curl \
            dcadec \
            enca \
            expat \
            faad2 \
            ffmpeg \
            fontconfig \
            fribidi \
            glib-2.0 \ 
            giflib \
            libass \
            libcdio \
            libcec \
            libinput \
            libbluray \
            libmad \
            libmicrohttpd \
            libmms \
            libmodplug \
            libnfs \
            libpcre \
            libplist \
            libsamplerate0 \
            libssh \
            spdlog \
            libtinyxml \
            libusb1 \
            libxkbcommon \
            libxslt \
            lzo \
            mpeg2dec \
            ${PYTHON_PN} \
            samba \
            sqlite3 \
            taglib \
            udev \
            virtual/egl \
            wavpack \
            yajl \
            zlib \
           "

# 'patch' doesn't support binary diffs
PATCHTOOL = "git"

PV = "19.3+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Matrix \
           file://0001-flatbuffers-19.patch \
           file://0002-readd-Touchscreen-settings.patch \
           file://0003-crossguid-0.2.patch \
           file://0004-shader-nopow-19.patch \
           file://0005-stb-support-19.patch \
           file://0007-add-winsystemfactory-windowing-init.patch \
           file://0008-adapt-window-system-registration.patch \
            \
           "

# stb, egl, players
SRC_URI_append += " \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-cortexa15', 'file://egl/0001-EGLNativeTypeV3DNXPL.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-mipsel', 'file://egl/0001-EGLNativeTypeV3DNXPL.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'edision-cortexa15', 'file://egl/0001-EGLNativeTypeEdision.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'xcore-mipsel', 'file://egl/0001-EGLNativeTypeV3D.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'nextv-cortexa15', 'file://egl/0001-EGLNativeTypeNexTV.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'GB-cortexa15', 'file://egl/0001-EGLNativeTypeV3D-gb4k.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-mipsel', 'file://egl/0001-EGLNativeTypeVuplus.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'vuplus-cortexa15', 'file://egl/0001-EGLNativeTypeVuplus4k.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://egl/0001-EGLNativeTypeMali.patch', '', d)} \
            \
            ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://HiPlayer.patch file://HiPlayer-Subs.patch file://defaultplayer-HiPlayer.patch', \
                                                              'file://E2Player.patch', d)} \
           "

S = "${WORKDIR}/git"

# breaks compilation
CCACHE_DISABLE = "1"
ASNEEDED = ""

ACCEL ?= ""
ACCEL_x86 = "vaapi vdpau"
ACCEL_x86-64 = "vaapi vdpau"

WINDOWSYSTEM ?= "stb"

APPRENDERSYSTEM ?= "gles"

PACKAGECONFIG ??= "${ACCEL} ${WINDOWSYSTEM} pulseaudio lcms lto \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)}"

# Core windowing system choices

PACKAGECONFIG[gbm] = "-DCORE_PLATFORM_NAME=gbm -DGBM_RENDER_SYSTEM=gles,,"
PACKAGECONFIG[stb] = "-DCORE_PLATFORM_NAME=stb,,"
PACKAGECONFIG[raspberrypi] = "-DCORE_PLATFORM_NAME=rbpi,,userland"
PACKAGECONFIG[amlogic] = "-DCORE_PLATFORM_NAME=aml,,"
PACKAGECONFIG[wayland] = "-DCORE_PLATFORM_NAME=wayland -DWAYLAND_RENDER_SYSTEM=gles,,wayland waylandpp"
PACKAGECONFIG[x11] = "-DCORE_PLATFORM_NAME=x11,,libxinerama libxmu libxrandr libxtst glew"

# Features

PACKAGECONFIG[opengl] = "-DENABLE_OPENGL=ON,,"
PACKAGECONFIG[openglesv2] = "-DENABLE_GLES=ON,,virtual/egl"
PACKAGECONFIG[dvdcss] = "-DENABLE_DVDCSS=ON,-DENABLE_DVDCSS=OFF"
PACKAGECONFIG[vaapi] = "-DENABLE_VAAPI=ON,-DENABLE_VAAPI=OFF,${KODIVAAPIDEPENDS},${KODIVAAPIDEPENDS}"
PACKAGECONFIG[vdpau] = "-DENABLE_VDPAU=ON,-DENABLE_VDPAU=OFF,libvdpau,mesa-vdpau-drivers"
PACKAGECONFIG[lirc] = ",,lirc"
PACKAGECONFIG[mysql] = "-DENABLE_MYSQLCLIENT=ON,-DENABLE_MYSQLCLIENT=OFF,mysql5"
PACKAGECONFIG[pulseaudio] = "-DENABLE_PULSEAUDIO=ON,-DENABLE_PULSEAUDIO=OFF,pulseaudio"
PACKAGECONFIG[lcms] = ",,lcms"

# Compilation tunes

PACKAGECONFIG[gold] = "-DENABLE_LDGOLD=ON,-DENABLE_LDGOLD=OFF"
PACKAGECONFIG[lto] = "-DUSE_LTO=${@oe.utils.cpu_count()},-DUSE_LTO=OFF"
PACKAGECONFIG[testing] = "-DENABLE_TESTING=ON,-DENABLE_TESTING=0FF,googletest"

EXTRA_OECMAKE_append_mipsarch = " -DWITH_ARCH=${TARGET_ARCH}"
EXTRA_OECMAKE_append_mipselarch = " -DWITH_ARCH=${TARGET_ARCH}"

LDFLAGS += "${TOOLCHAIN_OPTIONS}"
LDFLAGS_append_mips = " -latomic -lpthread"
LDFLAGS_append_mipsel = " -latomic -lpthread"
LDFLAGS_append_mips64 = " -latomic -lpthread"
LDFLAGS_append_mips64el = " -latomic -lpthread"
LDFLAGS_append_arm = " -lpthread"

KODI_ARCH = ""
KODI_ARCH_mips = "-DWITH_ARCH=${TARGET_ARCH}"
KODI_ARCH_mipsel = "-DWITH_ARCH=${TARGET_ARCH}"
KODI_ARCH_mips64 = "-DWITH_ARCH=${TARGET_ARCH}"
KODI_ARCH_mips64el = "-DWITH_ARCH=${TARGET_ARCH}"

KODI_DISABLE_INTERNAL_LIBRARIES = " \
  -DENABLE_INTERNAL_CROSSGUID=OFF \
  -DENABLE_INTERNAL_FLATBUFFERS=OFF \
  -DENABLE_INTERNAL_FMT=OFF \
  -DENABLE_INTERNAL_FSTRCMP=0 \
  -DENABLE_INTERNAL_RapidJSON=OFF \
  -DENABLE_INTERNAL_FFMPEG=OFF \
"

EXTRA_OECMAKE = " \
    ${KODI_ARCH} \
    ${KODI_DISABLE_INTERNAL_LIBRARIES} \
    -DAPP_RENDER_SYSTEM=${APPRENDERSYSTEM} \
    \
    -DNATIVEPREFIX=${STAGING_DIR_NATIVE}${prefix} \
    -DJava_JAVA_EXECUTABLE=/usr/bin/java \
    -DWITH_TEXTUREPACKER=${STAGING_BINDIR_NATIVE}/TexturePacker \
    -DWITH_JSONSCHEMABUILDER=${STAGING_BINDIR_NATIVE}/JsonSchemaBuilder \
    \
    -DCMAKE_NM='${NM}' \
    \
    -DFFMPEG_PATH=${STAGING_DIR_TARGET} \
    -DLIBDVD_INCLUDE_DIRS=${STAGING_INCDIR} \
    -DNFS_INCLUDE_DIR=${STAGING_INCDIR} \
    -DSHAIRPLAY_INCLUDE_DIR=${STAGING_INCDIR} \
    \
    -DENABLE_AIRTUNES=ON \
    -DENABLE_OPTICAL=OFF \
    -DENABLE_DVDCSS=OFF \
    -DENABLE_DEBUGFISSION=OFF \
    -DENABLE_NEW_CROSSGUID=ON \
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

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
FULL_OPTIMIZATION_armv7ve = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export ${PYTHON_DIR}

export TARGET_PREFIX

do_configure_prepend() {
        # Ensure 'nm' can find the lto plugins 
        liblto=$(find ${STAGING_DIR_NATIVE} -name "liblto_plugin.so.0.0.0")
        mkdir -p ${STAGING_LIBDIR_NATIVE}/bfd-plugins
        ln -sf $liblto ${STAGING_LIBDIR_NATIVE}/bfd-plugins/liblto_plugin.so

        sed -i -e 's:CMAKE_NM}:}${TARGET_PREFIX}gcc-nm:' ${S}/xbmc/cores/DllLoader/exports/CMakeLists.txt
}

INSANE_SKIP_${PN} = "rpaths already-stripped file-rdeps"

FILES_${PN} = "${libdir}/kodi ${libdir}/xbmc"
FILES_${PN} += "${bindir}/kodi ${bindir}/xbmc"
FILES:${PN} += "${datadir}/metainfo ${datadir}/xsessions ${datadir}/icons ${libdir}/xbmc ${datadir}/xbmc ${libdir}/firewalld"
FILES_${PN} += "${bindir}/kodi-standalone ${bindir}/xbmc-standalone ${datadir}/xsessions"
FILES_${PN} += "${libdir}/firewalld"
FILES_${PN}-dev = "${includedir}"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS_${PN}_append = " libcec \
                             libcurl \
                             libnfs \
                             nss \
                             os-release \
                             ${@bb.utils.contains('PACKAGECONFIG', 'x11', 'xdyinfo xrandr xinit mesa-demos', '', d)} \
                             ${PYTHON_PN} \
                             ${PYTHON_PN}-ctypes \
                             ${PYTHON_PN}-netclient \
                             ${PYTHON_PN}-html \
                             ${PYTHON_PN}-difflib \
                             ${PYTHON_PN}-pycryptodome \
                             ${PYTHON_PN}-pycryptodomex \
                             ${PYTHON_PN}-json \
                             ${PYTHON_PN}-shell \
                             ${PYTHON_PN}-sqlite3 \
                             ${PYTHON_PN}-compression \
                             ${PYTHON_PN}-xmlrpc \
                             ${PYTHON_PN}-mechanize \
                             ${PYTHON_PN}-profile \
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
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 \
                                        glibc-gconv-ibm850 \
                                        glibc-charmap-ibm437 \
                                        glibc-gconv-ibm437 \
                                        glibc-gconv-unicode \
                                        glibc-gconv-utf-32 \
                                        glibc-charmap-utf-8 \
                                        glibc-localedata-en-us \
                                      "
# customizations should be in the BSP layers
require kodi_19.inc
