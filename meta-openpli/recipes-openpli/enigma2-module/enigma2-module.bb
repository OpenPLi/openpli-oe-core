SECTION = "kernel/modules"
LICENSE = "CLOSED"
PRIORITY = "required"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://enigma2module.c file://Makefile"

S = "${WORKDIR}"

inherit module machine_kernel_pr

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C "${STAGING_KERNEL_BUILDDIR}" M="${S}" modules
}

do_configure[nostamp] = "1"

do_configure_prepend(){
    sed -i "s/@MACHINE@/${MACHINE}/" "${S}/enigma2module.c"
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/enigma2module
    install -m 0644 ${S}/enigma2module.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/enigma2module
    install -d ${D}/${sysconfdir}/modules-load.d
    echo enigma2module >> ${D}/${sysconfdir}/modules-load.d/zzenigma2module.conf
}

FILES_${PN} += "${sysconfdir}/modules-load.d/zzenigma2module.conf"
