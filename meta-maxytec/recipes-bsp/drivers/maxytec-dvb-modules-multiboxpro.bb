KV = "4.4.176"
SRCDATE = "20261126"

require maxytec-dvb-modules.inc

SRC_URI[md5sum] = "84ff830a77aad021b3f8c265cd5733cb"
SRC_URI[sha256sum] = "0652e54b5ddc384b64edb0a554a65a87e85bb831847c4683cc0dbed3893da33d"

COMPATIBLE_MACHINE = "multiboxpro"

INITSCRIPT_NAME = "suspend"
INITSCRIPT_PARAMS = "start 89 0 ."
inherit update-rc.d

do_configure[noexec] = "1"

# Generate a simplistic standard init script
do_compile:append () {
	cat > suspend << EOF
#!/bin/sh

runlevel=runlevel | cut -d' ' -f2

if [ "\$runlevel" != "0" ] ; then
	exit 0
fi

mount -t sysfs sys /sys

/usr/bin/turnoff_power
EOF
}

do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${bindir}
	install -m 0755 ${S}/suspend ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/turnoff_power ${D}${bindir}
}

do_package_qa() {
}

FILES:${PN} += " ${bindir} ${sysconfdir}/init.d"

INSANE_SKIP:${PN} += "already-stripped"
