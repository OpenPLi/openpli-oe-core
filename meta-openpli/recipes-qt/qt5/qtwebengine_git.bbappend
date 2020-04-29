# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

DEPENDS += " \
    libnss-mdns \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtwebengine-git:"

PACKAGECONFIG_append = " ffmpeg opus libvpx proprietary-codecs pepper-plugins webrtc"

INSANE_SKIP_${PN} += "file-rdeps"
