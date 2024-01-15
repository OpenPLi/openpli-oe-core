require wireguard.inc

SRC_URI = "https://git.zx2c4.com/wireguard-tools/snapshot/wireguard-tools-${PV}.tar.xz file://wg-quick.patch;striplevel=2"
SRC_URI[md5sum] = "53a0f06dbb298bbae7532fd10bcc3af1"
SRC_URI[sha256sum] = "b288b0c43871d919629d7e77846ef0b47f8eeaa9ebc9cedeee8233fc6cc376ad"

S = "${WORKDIR}/wireguard-tools-${PV}/src"

inherit bash-completion systemd pkgconfig

DEPENDS = "libmnl"
RDEPENDS:${PN} = "bash, iproute2-ip, kernel-module-wireguard, openresolv"

do_install () {
    oe_runmake DESTDIR="${D}" PREFIX="${prefix}" SYSCONFDIR="${sysconfdir}" \
        SYSTEMDUNITDIR="${systemd_unitdir}" \
        WITH_SYSTEMDUNITS=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'yes', '', d)} \
        WITH_BASHCOMPLETION=yes \
        WITH_WGQUICK=yes \
        install
}

FILES:${PN} = " \
    ${sysconfdir} \
    ${systemd_unitdir} \
    ${bindir} \
"
