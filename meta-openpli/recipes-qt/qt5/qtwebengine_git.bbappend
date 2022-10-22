# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI += " \
    file://chromium/0002-Replace-hbbtv-responses-with-application-xhtml-xml.patch;patchdir=src/3rdparty \
"

SRC_URI_append_osmio4k = " \
    file://chromium/0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch;patchdir=src/3rdparty \
    "

SRC_URI_append_osmio4kplus = " \
    file://chromium/0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch;patchdir=src/3rdparty \
"

DEPENDS += " \
    libnss-mdns \
    libxkbcommon \
    libwebp-native \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine-git:"

PACKAGECONFIG[alsa] = "-feature-webengine-alsa,-no-feature-webengine-alsa,alsa-lib"
PACKAGECONFIG[extensions] = "-feature-webengine-extensions,-no-feature-webengine-extensions"


PACKAGECONFIG_append = " libwebp ffmpeg opus libvpx alsa \
                        proprietary-codecs pepper-plugins \
                        webrtc \
                       "

INSANE_SKIP_${PN} += "file-rdeps"
