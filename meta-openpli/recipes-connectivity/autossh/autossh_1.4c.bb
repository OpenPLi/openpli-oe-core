DESCRIPTION = "autossh"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://autossh.c;beginline=1;endline=24;md5=755a81ffe573faf6c18d1f1061d097c4"

SRC_URI = "http://www.harding.motd.ca/autossh/${P}.tgz"

inherit autotools-brokensep

TARGET_CC_ARCH += "${LDFLAGS}"

# Install target is broken (attempts to install in /usr/ and ignores
# DESTDIR environment)
do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/autossh ${D}${bindir}/autossh
}

SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
SRC_URI[sha256sum] = "6fcaba6a409a46bdf832086736bb8f09d245ebce11027f41d39588a95dc7fd1d"
