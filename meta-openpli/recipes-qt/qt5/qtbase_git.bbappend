# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase-git:"

SET_QT_QPA_DEFAULT_PLATFORM ?= "linuxfb"
SET_QT_QPA_EGLFS_INTEGRATION ?= "eglfs_emu"


SRC_URI += " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'file://0001-eglfs-mali-platform.patch', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'file://0002-eglfs-brcm-nexus-platform.patch', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'eglfs-brcmstb', 'file://0001-Add-eglfs-brcmstb-support-for-INTEGRITY-5.13.2.patch', '', d)} \
    file://0003-Revert-configure-actually-resolve-libraries.patch \
"

PACKAGECONFIG_GL = " "
PACKAGECONFIG_OPENSSL = "openssl"
PACKAGECONFIG_remove = "tests"

PACKAGECONFIG_append += " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'no-opengl', '', 'gles2 eglfs', d)} \
    linuxfb \
"

do_configure_prepend() {
cat >> ${S}/mkspecs/oe-device-extra.pri <<EOF
QT_QPA_DEFAULT_PLATFORM = ${SET_QT_QPA_DEFAULT_PLATFORM}
EGLFS_DEVICE_INTEGRATION = ${SET_QT_QPA_EGLFS_INTEGRATION}
EOF
}

INSANE_SKIP_${PN} += "file-rdeps"
INSANE_SKIP_${PN}-plugins += "file-rdeps"
