FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:${PN} = "lzo lz4"

SRC_URI:append = " file://update-resolv-conf.sh"

# make sure it starts automatically after installation
INITSCRIPT_NAME:${PN} = "openvpn"
INITSCRIPT_PARAMS:${PN} = "defaults"

do_install:append () {
    install -m 775 ${WORKDIR}/update-resolv-conf.sh ${D}${sysconfdir}/openvpn/update-resolv-conf.sh
}

pkg_postinst:${PN} () {
    modprobe tun >/dev/null 2>&1 || true
    if [ ! -c /dev/net/tun ]; then
        if [ ! -d /dev/net ]; then
            mkdir /dev/net
        fi
        mknod /dev/net/tun c 10 200
        chmod 600 /dev/net/tun
    fi
}
