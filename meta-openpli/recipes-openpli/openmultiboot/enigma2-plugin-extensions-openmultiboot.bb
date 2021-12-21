DESCRIPTION = "Multi boot loader manager for enigma2 box"
HOMEPAGE = "https://github.com/Dima73/pli-openmultibootmanager"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI = "git://github.com/Dima73/pli-openmultibootmanager.git;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins

RRECOMMENDS_${PN} = "mtd-utils mtd-utils-ubifs kernel-module-nandsim openmultiboot"

pkg_preinst_${PN}() {
#!/bin/sh
if mountpoint -q ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot; then
    echo "openMultiBoot will only install on main image."
    echo "Child image is running - canceling installation!"
    sleep 3
    exit 1
else
    echo "Main image is running - proceeding installation..."
    exit 0
fi
}

pkg_postrm_${PN}() {
#!/bin/sh

if mountpoint -q ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot; then
    echo "openMultiBoot will only remove on main image."
    exit 0
else
    echo "Main image is running - proceeding removing..."
fi

rm -rf /sbin/init
ln -s /sbin/init.sysvinit /sbin/init
rm -rf /sbin/open-multiboot-branding-helper.py

chown -Rh root:root ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot
rm -rf ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot
exit 0

}
