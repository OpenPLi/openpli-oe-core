KV = "4.4.176"
SRCDATE = "20261125"

PROVIDES += "virtual/blindscan-dvbc virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbc virtual-blindscan-dvbs"

require zgemma-dvb-himodules.inc

SRC_URI[arm.md5sum] = "7280e3cac9a8ccd833055fe0bbb86ff4"
SRC_URI[arm.sha256sum] = "a21444b88019d35ee82e5e199d48187a15fa9d0922e69a9c9b637cb15aeb1c6a"

COMPATIBLE_MACHINE = "h8se"

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
