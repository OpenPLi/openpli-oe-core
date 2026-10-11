KV = "4.4.35"
SRCDATE = "20210727"

PROVIDES += "virtual/blindscan-dvbc virtual/blindscan-dvbs"
RPROVIDES:${PN} += "virtual-blindscan-dvbc virtual-blindscan-dvbs"

require zgemma-dvb-himodules.inc

SRC_URI[arm.md5sum] = "2f6092d1430ce822f363cb2937dddace"
SRC_URI[arm.sha256sum] = "f5f71b9835f274aee912bd6671616602be8cd40f7a2319508c29f7d0d71d5ad2"

COMPATIBLE_MACHINE = "h9combose"

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
