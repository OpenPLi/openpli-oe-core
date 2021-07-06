SUMMARY = "Kodi Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit cmake gettext python-dir pythonnative systemd

DEPENDS = " \
            fmt \
            flatbuffers flatbuffers-native \
            fstrcmp \
            rapidjson \
            crossguid \
            texturepacker-native \
            libdvdnav libdvdcss libdvdread \
            git-native \
            curl-native \
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
            libsquish \
            libssh \
            libtinyxml \
            libusb1 \
            libxkbcommon \
            libxslt \
            lzo \
            mpeg2dec \
            python \
            samba \
            sqlite3 \
            taglib \
            virtual/egl \
            wavpack \
            yajl \
            zlib \
           "

SRCREV = "0655c2c71821567e4c21c1c5a508a39ab72f0ef1"

# 'patch' doesn't support binary diffs
PATCHTOOL = "git"

# Correct 18+git vs 18-git screwup
PE = "1"

PV = "18.9+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;protocol=https;branch=Leia"

# patches for 18.x upstreamed in 19.x
SRC_URI_append = " \
            file://0001-Add-support-for-musl-triplets.patch \
            file://0002-Fix-file_Emu-on-musl.patch \
            file://0003-Remove-FILEWRAP.patch \
            file://0004-Replace-u_int64_t-with-uint64_t-from-stdint.h.patch \
            \
            file://0005-estuary-move-recently-added-entries-to-the-top-in-ho.patch \
            file://0006-kodi.sh-set-mesa-debug.patch \
            file://0007-peripheral-settings-export-CEC-device_name-in-GUI.patch \
            file://0010-flatbuffers.patch \
            \
            file://PR15286-shader-nopow.patch \
            file://15941.patch \
           "

# stb, egl, players
SRC_URI_append = " \
            file://stb-1-platform.patch \
            file://stb-2-ext-install.patch \
            file://stb-3-rckey-events.patch \
            file://stb-4-crosstools.patch \
            \
            file://egl-1-v3d-mali.patch \
            file://egl-2-windowing.patch \
            \
            ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://egl/EGLNativeTypeV3D-nxpl.patch', '', d)} \
            ${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'file://egl/EGLNativeTypeMali.patch file://HiPlayer.patch file://HiPlayer-Subs.patch file://defaultplayer-HiPlayer.patch', 'file://defaultplayer-E2Player.patch file://E2Player.patch', d)} \
           "

S = "${WORKDIR}/git"

# breaks compilation
CCACHE = ""
ASNEEDED = ""

ACCEL ?= ""
ACCEL_x86 = "vaapi vdpau"
ACCEL_x86-64 = "vaapi vdpau"

WINDOWSYSTEM ?= "stb"

PACKAGECONFIG ?= "${ACCEL} ${WINDOWSYSTEM} lcms lto \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)} \
                  "

# Core windowing system choices

PACKAGECONFIG[x11] = "-DCORE_PLATFORM_NAME=x11,,libxinerama libxmu libxrandr libxtst glew"
PACKAGECONFIG[gbm] = "-DCORE_PLATFORM_NAME=gbm -DGBM_RENDER_SYSTEM=gles,,"
PACKAGECONFIG[stb] = "-DCORE_PLATFORM_NAME=stb,,"
PACKAGECONFIG[raspberrypi] = "-DCORE_PLATFORM_NAME=rbpi,,userland"
PACKAGECONFIG[amlogic] = "-DCORE_PLATFORM_NAME=aml,,"
PACKAGECONFIG[wayland] = "-DCORE_PLATFORM_NAME=wayland -DWAYLAND_RENDER_SYSTEM=gles,,wayland waylandpp"

# Features

PACKAGECONFIG[opengl] = "-DENABLE_OPENGL=ON,,"
PACKAGECONFIG[openglesv2] = "-DENABLE_GLES=ON,,virtual/egl"

PACKAGECONFIG[vaapi] = "-DENABLE_VAAPI=ON,-DENABLE_VAAPI=OFF,libva"
PACKAGECONFIG[vdpau] = "-DENABLE_VDPAU=ON,-DENABLE_VDPAU=OFF,libvdpau"
PACKAGECONFIG[mysql] = "-DENABLE_MYSQLCLIENT=ON,-DENABLE_MYSQLCLIENT=OFF,mysql5"
PACKAGECONFIG[pulseaudio] = "-DENABLE_PULSEAUDIO=ON,-DENABLE_PULSEAUDIO=OFF,pulseaudio"
PACKAGECONFIG[lcms] = ",,lcms"

# Compilation tunes

PACKAGECONFIG[gold] = "-DENABLE_LDGOLD=ON,-DENABLE_LDGOLD=OFF"
PACKAGECONFIG[lto] = "-DUSE_LTO=${@oe.utils.cpu_count()},-DUSE_LTO=OFF"

EXTRA_OECMAKE = " \
    -DENABLE_INTERNAL_CROSSGUID=OFF \
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
    -DENABLE_OPTICAL=OFF \
    -DENABLE_DVDCSS=OFF \
    -DENABLE_DEBUGFISSION=OFF \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
"

EXTRA_OECMAKE_append_mipsarch = " -DWITH_ARCH=${TARGET_ARCH}"

LDFLAGS += "${TOOLCHAIN_OPTIONS}"
LDFLAGS_append_mipsarch = " -latomic -lpthread"
LDFLAGS_append_arm = " -lpthread"

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

export TARGET_PREFIX

do_configure_prepend() {
        # Ensure 'nm' can find the lto plugins 
        liblto=$(find ${STAGING_DIR_NATIVE} -name "liblto_plugin.so.0.0.0")
        mkdir -p ${STAGING_LIBDIR_NATIVE}/bfd-plugins
        ln -sf $liblto ${STAGING_LIBDIR_NATIVE}/bfd-plugins/liblto_plugin.so

        sed -i -e 's:CMAKE_NM}:}${TARGET_PREFIX}gcc-nm:' ${S}/xbmc/cores/DllLoader/exports/CMakeLists.txt
}

FILES_${PN} += "${datadir}/xsessions ${datadir}/icons ${libdir}/xbmc ${datadir}/xbmc ${libdir}/firewalld"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS_${PN}_append = " libcec \
                             libcurl \
                             libnfs \
                             nspr \
                             nss \
                             ${@bb.utils.contains('PACKAGECONFIG', 'x11', 'xdyinfo xrandr xinit mesa-demos', '', d)} \
                             os-release \
                             python \
                             python-ctypes \
                             python-lang \
                             python-re \
                             python-netclient \
                             python-html \
                             python-difflib \
                             python-pycryptodome \
                             python-pycryptodomex \
                             python-json \
                             python-zlib \
                             python-shell \
                             python-sqlite3 \
                             python-compression \
                             python-xmlrpc \
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
require kodi_18.inc
