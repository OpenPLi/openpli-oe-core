require wireguard.inc

MAKE_TARGETS = "module"
MODULE_NAME = "wireguard"

SRC_URI = " \
    https://git.zx2c4.com/wireguard-linux-compat/snapshot/wireguard-linux-compat-${PV}.tar.xz \
    file://0001-fix-fallback-macro-missing.patch \
    "

SRC_URI[sha256sum] = "362d412693c8fe82de00283435818d5c5def7f15e2433a07a9fe99d0518f63c0"

S = "${WORKDIR}/wireguard-linux-compat-${PV}/src"

inherit module linux-kernel-base

DEPENDS = "bc-native virtual/kernel libmnl"
RRECOMMENDS:${PN} = "kernel-module-xt-hashlimit"

# This module requires Linux 3.10 higher and several networking related
# configuration options. For exact kernel requirements visit:
# https://www.wireguard.io/install/#kernel-requirements

EXTRA_OEMAKE:append = " \
    KERNELDIR=${STAGING_KERNEL_DIR} \
"

KERNEL_VERSION="${@get_kernelversion_headers('${STAGING_KERNEL_DIR}') or oe.utils.read_file('${PKGDATA_DIR}/kernel-depmod/kernel-abiversion')}"
RPROVIDES:${PN} = "${@ 'kernel-module-wireguard' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '5.6') < 0) else '' }"

# Kernel module packages MUST begin with 'kernel-module-', otherwise
# multilib image generation can fail.
#
# The following line is only necessary if the recipe name does not begin
# with kernel-module-.
PKG:${PN} = "kernel-module-${MODULE_NAME}"

module_do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}
    install -m 0644 ${MODULE_NAME}.ko \
    ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}/${MODULE_NAME}.ko
}
