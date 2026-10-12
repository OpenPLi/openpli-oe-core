DESCRIPTION = "Userspace tools for MMC/SD devices"
HOMEPAGE = "http://git.kernel.org/cgit/linux/kernel/git/cjb/mmc-utils.git/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://mmc.c;beginline=1;endline=20;md5=fae32792e20f4d27ade1c5a762d16b7d"

BRANCH ?= "master"

PV = "0.1"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/cjb/mmc-utils.git;branch=${BRANCH};protocol=https"
UPSTREAM_CHECK_COMMITS = "1"

# original VU+ (Aug 2014)
#SRCREV = "f4eb241519f8d500ce6068a70d2389be39ac5189"
#SRC_URI += " file://0001-mmc.h-don-t-include-asm-generic-int-ll64.h.patch"
#PR = "r0"

# oatv (March 2018)
SRCREV = "b4fe0c8c0e57a74c01755fa9362703b60d7ee49d"
SRC_URI += " file://0001-lsmmc-replace-strncpy-with-memmove-on-overlapping-me.patch"
PR = "r1"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 mmc ${D}${bindir}/mmc-vu
}
