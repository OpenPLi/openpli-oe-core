KV = "4.4.176"
SRCDATE = "20261119"

PROVIDES += "virtual/blindscan-dvbc virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbc virtual-blindscan-dvbs"

require zgemma-dvb-himodules.inc

SRC_URI[arm.md5sum] = "6629da1a24429ce5d2419057ab02c656"
SRC_URI[arm.sha256sum] = "e39fa98f5dcc9d82c8cc3deef89c2949da65e99f08504d44cd5c054004026f05"

COMPATIBLE_MACHINE = "i55se"

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

FILES:${PN} += " ${bindir} ${sysconfdir}/init.d"

# The vendor power-off executable cannot be relinked with OE linker flags.
INSANE_SKIP:${PN} += "already-stripped ldflags"

# Vendor libraries are excluded from automatic shared-library dependencies.
RDEPENDS:${PN} += "zgemma-libs-${HICHIPSET}"
