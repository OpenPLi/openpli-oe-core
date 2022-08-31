SUMMARY = "Kodi Media Center"

FILESEXTRAPATHS_prepend := "${THISDIR}/kodi-19:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit cmake gettext python3-dir python3native

DEPENDS += " \
            autoconf-native \
            automake-native \
            curl-native \
            flatbuffers-native \
            git-native \
            googletest-native \
            gperf-native \
            jsonschemabuilder-native \
            nasm-native \
            swig-native \
            texturepacker-native \
            unzip-native \
            yasm-native \
            zip-native \
            \
            avahi \
            boost \
            bzip2 \
            crossguid \
            curl \
            dcadec \
            enca \
            expat \
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
            libmms \
            libmodplug \
            libnfs \
            libpcre \
            libplist \
            libsamplerate0 \
            libssh \
            libtinyxml \
            libusb1 \
            libxkbcommon \
            libxml2 \
            libxslt \
            lzo \
            mpeg2dec \
            python3 \
            rapidjson \
            samba \
            spdlog \
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


# stb, egl, players
SRC_URI_append += " \
           file://0001-flatbuffers-19.patch \
           file://0002-readd-Touchscreen-settings.patch \
           file://0003-crossguid-0.2.patch \
           file://0004-shader-nopow.patch \
           file://0005-stb-support-19.patch \
           file://0007-add-winsystemfactory-windowing-init.patch \
            \
           "

# stb, egl, players
SRC_URI_append += " \
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
CCACHE = ""
ASNEEDED = ""

ACCEL ?= ""
ACCEL_x86 = "vaapi vdpau"
ACCEL_x86-64 = "vaapi vdpau"

WINDOWSYSTEM ?= "stb"

APPRENDERSYSTEM ?= "gles"
PACKAGECONFIG ?= "${ACCEL} ${WINDOWSYSTEM} pulseaudio lcms lto \
                            ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                            ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)}"

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
PACKAGECONFIG[bluetooth] = ",,bluez5"
PACKAGECONFIG[samba] = ",,samba"
PACKAGECONFIG[lcms] = ",,lcms"
PACKAGECONFIG[vaapi] = "-DENABLE_VAAPI=ON,-DENABLE_VAAPI=OFF,libva"
PACKAGECONFIG[vdpau] = "-DENABLE_VDPAU=ON,-DENABLE_VDPAU=OFF,libvdpau"
PACKAGECONFIG[mysql] = "-DENABLE_MYSQLCLIENT=ON,-DENABLE_MYSQLCLIENT=OFF,mysql5"
PACKAGECONFIG[systemd] = ",,,kodi-systemd-service"
PACKAGECONFIG[pulseaudio] = "-DENABLE_PULSEAUDIO=ON,-DENABLE_PULSEAUDIO=OFF,pulseaudio"

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

FILES_${PN} += "${datadir}/metainfo ${datadir}/xsessions ${datadir}/icons ${libdir}/xbmc ${datadir}/xbmc ${libdir}/firewalld"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# for some devices: for libmali.so libEGL.so and libGLESv2.so no providers found
# in RDEPENDS_kodi? [file-rdeps]
#
INSANE_SKIP_${PN} = "file-rdeps"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS_${PN}_append = " \
                             libcec \
                             libcurl \
                             libnfs \
                             nss \
                             os-release \
                             ${@bb.utils.contains('PACKAGECONFIG', 'x11', 'xdyinfo xrandr xinit mesa-demos', '', d)} \
                             ${PYTHON_PN} \
                             ${PYTHON_PN}-compression \
                             ${PYTHON_PN}-ctypes \
                             ${PYTHON_PN}-netclient \
                             ${PYTHON_PN}-difflib \
                             ${PYTHON_PN}-html \
                             ${PYTHON_PN}-json \
                             ${PYTHON_PN}-mechanize \
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

RRECOMMENDS_${PN}_append_libc-glibc = " \
                                        glibc-charmap-ibm850 \
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
