DESCRIPTION = "Driver for Realtek USB wireless devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module machine_kernel_pr

SRC_URI = "http://downloads.pli-images.org/misc/rtl8188eu_1.0.0_20130816.tar.gz \
	"

S = "${WORKDIR}/rtl8188eu"

MACHINE_KERNEL_PR_append = ".0"

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/8188eu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

SRC_URI[md5sum] = "590c5f89d89ed8aa51f85a5644676e4f"
SRC_URI[sha256sum] = "de2f9c05ec5ae3ea3cc48e7fd2ac2a6052e2d7037e42a2277faab259ae090499"
