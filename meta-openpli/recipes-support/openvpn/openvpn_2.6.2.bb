SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "https://openvpn.net/"
SECTION = "net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=3170e982baae61dbb8de963317d1ac94"
DEPENDS = "lzo lz4 openssl iproute2 libcap-ng ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
RDEPENDS:{$PN} = "lzo lz4"

inherit autotools systemd update-rc.d pkgconfig

SRC_URI = "http://swupdate.openvpn.org/community/releases/${BP}.tar.gz \
           file://0001-configure.ac-eliminate-build-path-from-openvpn-versi.patch \
           file://openvpn \
           file://update-resolv-conf.sh \
          "

UPSTREAM_CHECK_URI = "https://openvpn.net/community-downloads"

SRC_URI[sha256sum] = "42d561a9af150b21bc914e3b7aa09f88013d2ffa6d5ce75a025a3b34caa948d4"

# CVE-2020-7224 and CVE-2020-27569 are for Aviatrix OpenVPN client, not for openvpn.
CVE_CHECK_IGNORE += "CVE-2020-7224 CVE-2020-27569"

INITSCRIPT_PACKAGES = "${PN}"
# make sure it starts automatically after installation
INITSCRIPT_NAME:${PN} = "openvpn"
INITSCRIPT_PARAMS:${PN} = "defaults"

CFLAGS += "-fno-inline"

# I want openvpn to be able to read password from file (hrw)
EXTRA_OECONF += "--enable-iproute2"
EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'pam', '', '--disable-plugin-auth-pam', d)}"

# Explicitly specify IPROUTE to bypass the configure-time check for /sbin/ip on the host.
EXTRA_OECONF += "IPROUTE=${base_sbindir}/ip"

EXTRA_OECONF += "SYSTEMD_UNIT_DIR=${systemd_system_unitdir} \
                 TMPFILES_DIR=${nonarch_libdir}/tmpfiles.d \
                "

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
                   ${@bb.utils.filter('DISTRO_FEATURES', 'selinux', d)} \
                  "

PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux"

do_install:append() {
    install -d ${D}/${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/openvpn ${D}/${sysconfdir}/init.d

    install -d ${D}/${sysconfdir}/openvpn
    install -d ${D}/${sysconfdir}/openvpn/server
    install -d ${D}/${sysconfdir}/openvpn/client

    install -d ${D}/${sysconfdir}/openvpn/sample
    install -m 644 ${S}/sample/sample-config-files/loopback-server  ${D}${sysconfdir}/openvpn/sample/loopback-server.conf
    install -m 644 ${S}/sample/sample-config-files/loopback-client  ${D}${sysconfdir}/openvpn/sample/loopback-client.conf
    install -dm 755 ${D}${sysconfdir}/openvpn/sample/sample-config-files
    install -dm 755 ${D}${sysconfdir}/openvpn/sample/sample-keys
    install -dm 755 ${D}${sysconfdir}/openvpn/sample/sample-scripts
    install -m 644 ${S}/sample/sample-config-files/* ${D}${sysconfdir}/openvpn/sample/sample-config-files
    install -m 644 ${S}/sample/sample-keys/* ${D}${sysconfdir}/openvpn/sample/sample-keys
    install -m 644 ${S}/sample/sample-scripts/* ${D}${sysconfdir}/openvpn/sample/sample-scripts

    install -d -m 710 ${D}/${localstatedir}/lib/openvpn
    install -m 775 ${S}/../update-resolv-conf.sh ${D}${sysconfdir}/openvpn/update-resolv-conf.sh
}

PACKAGES =+ " ${PN}-sample "

RDEPENDS:${PN} = "kernel-module-tun"

FILES:${PN}-dbg += "${libdir}/openvpn/plugins/.debug"
FILES:${PN} += "${systemd_system_unitdir}/openvpn-server@.service \
                ${systemd_system_unitdir}/openvpn-client@.service \
                ${nonarch_libdir}/tmpfiles.d \
               "
FILES:${PN}-sample = "${sysconfdir}/openvpn/sample/ \
                     "

pkg_postinst_${PN} () {
    modprobe tun >/dev/null 2>&1 || true
    if [ ! -c /dev/net/tun ]; then
        if [ ! -d /dev/net ]; then
            mkdir /dev/net
        fi
        mknod /dev/net/tun c 10 200
        chmod 600 /dev/net/tun
    fi
}
