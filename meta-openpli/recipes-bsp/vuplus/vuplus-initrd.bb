SECTION = "base"
LICENSE = "CLOSED"
PRIORITY = "required"

PR = "r6"

SRC_URI = "http://archive.vuplus.com/download/kernel/vmlinuz-initrd_${MACHINE}_20130212.tar.gz"

do_install() {
	install -d ${D}/boot
        install -m 0755 ${WORKDIR}/vmlinuz-initrd-7346b0 ${D}/boot/initrd_cfe_auto.bin
}

FILES_${PN} = "/boot"

INHIBIT_PACKAGE_STRIP = "1"
PACKAGE_ARCH := "${MACHINE_ARCH}"

SRC_URI[md5sum] = "3b45489e7902cbf98e9abdddea14567a"
SRC_URI[sha256sum] = "e7a7e747dcd7240c5d36c2235d11b2d0e703ed55be1120d6109220478d23fb09"

