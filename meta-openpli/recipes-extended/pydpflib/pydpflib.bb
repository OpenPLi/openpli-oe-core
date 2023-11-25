DESCRIPTION = "DPF access extension module"
MAINTAINER = "https://sourceforge.net/projects/pydpf/"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=6a256fd20875b5cf06888bbcbe1a21aa"

SRC_URI = "git://github.com/athoik/pydpflib.git;protocol=https"

DEPENDS = "libusb"

S = "${WORKDIR}/git"

inherit gitpkgv setuptools3

PV = "0.14+git${SRCPV}"
PKGV = "0.14+git${GITPKGV}"
PR = "r0"

do_compile_prepend() {
    $MAKE -C ${S}/dpf-ax/dpflib all
}

do_install:prepend() {
	cp ${B}/lib.linux-x86_64-3.9/dpflib.cpython*.so ${B}/lib.linux-x86_64-3.9/dpflib.so
}

do_install:append() {
	rm ${D}${PYTHON_SITEPACKAGES_DIR}/dpflib.cpython*.so
}
