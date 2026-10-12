require vuplus-platform-util.inc

RDEPENDS:${PN} += "gptfdisk"
RDEPENDS:${PN} += "mmc-utils-vu"

PV="18.1"
SRCDATE = "20191218"
SRCDATE_PR = "r0"
PR:append = ".3"

SRC_URI += " file://bp3flash.py"

do_install:append() {
	install -m 0755 ${WORKDIR}/bp3flash.py ${D}${bindir}
}

SRC_URI[md5sum] = "f8f1aa29d4c89c5ec02f99ab3a0ce198"
SRC_URI[sha256sum] = "8a66977b1dc948414acde064447d5e372bc77a4ed0741a4d6c8dd1ae6f6413bb"
