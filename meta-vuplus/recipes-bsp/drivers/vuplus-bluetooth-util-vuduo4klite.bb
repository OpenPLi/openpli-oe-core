SECTION = "base"
LICENSE = "CLOSED"

PROVIDES += "vuplus-bluetooth-util"
RPROVIDES:${PN} += "vuplus-bluetooth-util"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

SRCDATE = "20190502"
SRCDATE_PR = "r0"

SRC_URI[md5sum] = "4d22218325029652a0ee9e5ebe06be10"
SRC_URI[sha256sum] = "b922d0a87168343027384148e1ec397a4b987b9d63dc39d5acba51dd5a461fd1"

PR = "${SRCDATE}.${SRCDATE_PR}"

# not available at code.vuplus.com
SRC_URI = " \
    https://code.vuplus.de/download/release/bt/vuplus-bluetooth-util-${MACHINE}-${PR}.zip \
"

S = "${WORKDIR}/vuplus-bluetooth-util-${MACHINE}"

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES:${PN} = "/"

do_install() {
	install -d ${D}/usr/local/bin
	install -m 0755 ${S}/bsa_server ${D}/usr/local/bin/

	install -d ${D}/usr/local/lib
	install -d ${D}/usr/local/lib/modules
	install -m 0644 ${S}/btusb.ko ${D}/usr/local/lib/modules/btusb.ko
	install -m 0644 ${S}/bthid.ko ${D}/usr/local/lib/modules/bthid.ko
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} = "already-stripped"
