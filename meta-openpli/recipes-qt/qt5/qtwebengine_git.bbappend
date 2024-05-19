# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

inherit python3native

BBFILE_COLLECTIONS:prepend = " meta-python2"

SRCREV_qtwebengine = "63d4e58009c7f069ace14b64f1528ba2664272e9"
SRCREV_chromium = "0d0d7dfbae4f2adfe1109d963160f14896e65244"

SRC_URI:remove = " \
	file://chromium/0013-Fix-build-with-gcc-13.patch;patchdir=src/3rdparty \
	file://chromium/0014-avcodec-x86-mathops-clip-constants-used-with-shift-i.patch;patchdir=src/3rdparty \
	file://0002-qmake.conf-lower-MODULE_VERSION-to-5.15.X.patch \
"

SRC_URI += " \
    file://chromium/0002-ffmpeg-5.patch \
    file://chromium/0002-Replace-hbbtv-responses-with-application-xhtml-xml.patch;patchdir=src/3rdparty \
    file://chromium/qt5-webengine-pipewire-0.3.patch;patchdir=src/3rdparty \
    file://chromium/replace-html5ib-with-html-parser.patch;patchdir=src/3rdparty \
"

SRC_URI:append:osmio4k = " \
    file://chromium/0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch;patchdir=src/3rdparty \
    "

SRC_URI:append:osmio4kplus = " \
    file://chromium/0001-Add-initial-support-for-V4L2-mem2mem-decoder.patch;patchdir=src/3rdparty \
"

DEPENDS += " \
    libnss-mdns \
    libxkbcommon \
    libwebp-native \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/qtwebengine-git:"

PACKAGECONFIG[alsa] = "-feature-webengine-alsa,-no-feature-webengine-alsa,alsa-lib"
PACKAGECONFIG[extensions] = "-feature-webengine-extensions,-no-feature-webengine-extensions"


PACKAGECONFIG:append = " libwebp ffmpeg opus libvpx alsa \
                        proprietary-codecs pepper-plugins \
                        webrtc \
                       "

INSANE_SKIP:${PN} += "file-rdeps"
