DESCRIPTION = "OE-Alliance remote control and box image files for Enimga2 and OpenWebIF."
MAINTAINER = "OE-Alliance"
require conf/license/license-gplv2.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv

SRCREV = "${AUTOREV}"

PV = "${DISTRO_VERSION}+git"
PKGV = "${DISTRO_VERSION}+git${GITPKGV}"

do_configure[nostamp] = "1"

SRC_URI = "git://github.com/oe-alliance/remotes;protocol=https;branch=master"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${datadir}/enigma2
    install -d ${D}${datadir}/enigma2/hardware

    if [ ${MACHINE} = "h17" ]; then
        install -m 0644 ${S}/boxes/zgemmah17combo.png ${D}${datadir}/enigma2/hardware/zgemmah17combo_front.png
    elif [ ${MACHINE} = "h17twin" ]; then
        install -m 0644 ${S}/boxes/zgemmah17twin.png ${D}${datadir}/enigma2/hardware/zgemmah17twin_front.png
    elif [ ${MACHINE} = "h8se" ]; then
        install -m 0644 ${S}/boxes/zgemmah8se.png ${D}${datadir}/enigma2/hardware/zgemmah8se_front.png
    elif [ ${MACHINE} = "lunix3-4k" ]; then
        install -m 0644 ${S}/boxes/lunix34k.png ${D}${datadir}/enigma2/hardware/lunix34k_front.png
    elif [ ${MACHINE} = "et6x00" ]; then
        install -m 0644 ${S}/boxes/et6x00.png ${D}${datadir}/enigma2/hardware/et6x00_front.png
        install -m 0644 ${S}/boxes/et6500.png ${D}${datadir}/enigma2/hardware/et6500_front.png
        install -m 0644 ${S}/png/et6500.png ${D}${datadir}/enigma2/hardware/et6500.png
    elif [ ${MACHINE} = "et7x00" ]; then
        install -m 0644 ${S}/boxes/et7000.png ${D}${datadir}/enigma2/hardware/et7000_front.png
        install -m 0644 ${S}/boxes/et7100.png ${D}${datadir}/enigma2/hardware/et7100_front.png
        install -m 0644 ${S}/boxes/et7500.png ${D}${datadir}/enigma2/hardware/et7500_front.png
    elif [ ${MACHINE} = "et9x00" ]; then
        install -m 0644 ${S}/png/et9500.png ${D}${datadir}/enigma2/hardware/et9500.png
    elif [ ${MACHINE} = "sf8008" ]; then
        install -m 0644 ${S}/boxes/sf8008.png ${D}${datadir}/enigma2/hardware/sf8008_front.png
        install -m 0644 ${S}/boxes/sf8008.png ${D}${datadir}/enigma2/hardware/sf8008c_front.png
        install -m 0644 ${S}/boxes/sf8008s.png ${D}${datadir}/enigma2/hardware/sf8008s_front.png
        install -m 0644 ${S}/boxes/sf8008t.png ${D}${datadir}/enigma2/hardware/sf8008t_front.png.png
    elif [ ${MACHINE} = "sfx6008" ]; then
        install -m 0644 ${S}/boxes/sfx6008.png ${D}${datadir}/enigma2/hardware/sfx6008_front.png
        install -m 0644 ${S}/boxes/sfx6018.png ${D}${datadir}/enigma2/hardware/sfx6018_front.png
    else
        install -m 0644 ${S}/boxes/${MACHINE}.png ${D}${datadir}/enigma2/hardware/${MACHINE}_front.png
    fi
}

FILES:${PN} = "${datadir}/enigma2/hardware"
