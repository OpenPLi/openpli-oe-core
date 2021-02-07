# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

DEPENDS += " \
    libnss-mdns \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine-git:"

PACKAGECONFIG_append = " ffmpeg opus libvpx proprietary-codecs pepper-plugins webrtc"

SRC_URI += " \
    file://chromium/0002-Replace-hbbtv-responses-with-application-xhtml-xml-5.13.2.patch;patchdir=src/3rdparty \
    ${@bb.utils.contains('MACHINE_FEATURES', 'v4l2m2m', 'file://chromium/0001-Add-initial-support-for-V4L2-mem2mem-decoder-5.13.2.patch;patchdir=src/3rdparty', '', d)} \
"

INSANE_SKIP_${PN} += "file-rdeps"
