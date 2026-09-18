SECTION = "base"
LICENSE = "CLOSED"
PRIORITY = "required"

COMPATIBLE_MACHINE = "^(vuduo4klite)$"

SRCDATE = "20260827r4"


# todo: cleanup

# not available at code.vuplus.com
SRC_URI += "https://code.vuplus.de/download/release/kernel/initrd_${MACHINE}_${SRCDATE}.zip"

SRC_URI[md5sum] = "26dec08469801c156afb6b16acba3d9c"
SRC_URI[sha256sum] = "4dccac8c0e69e510e33ad4697130a0d248087503cd8b3066ceba7b55c6ee8e1f"


SRC_URI += "file://bootlogo.sh file://splash_auto.bin "
S = "${WORKDIR}"

do_install:append() {
    install -d ${D}/boot
    # initrd gb
    install -m 0755 ${S}/boot.bin ${D}/boot/boot_gb.bin
    install -m 0755 ${S}/gpt.bin ${D}/boot/gpt_gb.bin
    install -m 0755 ${S}/rescue.bin ${D}/boot/rescue_gb.bin

    # initrd vu
    install -m 0755 ${S}/gpt_vu.bin ${D}/boot/gpt_vu.bin
    install -m 0755 ${S}/initrd_vu.bin ${D}/boot/initrd_vu.bin
    install -m 0755 ${S}/rescue_vu.bin ${D}/boot/rescue_vu.bin

    # splash
    install -m 0755 ${S}/splash_auto.bin ${D}/boot/splash_auto.bin

    # bootup
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    echo '#! /bin/sh' > ${WORKDIR}/bootup
    echo 'echo 1 > /proc/stb/lcd/mode' >> ${WORKDIR}/bootup
    echo 'touch /dev/dbox/lcd0' >> ${WORKDIR}/bootup
    echo 'echo "duo4klite" > /proc/stb/info/vumodel' >> ${WORKDIR}/bootup
    echo 'echo "dm8000\n" > /proc/stb/info/model' >> ${WORKDIR}/bootup
    echo 'echo "vuplus" > /proc/stb/info/boxtype' >> ${WORKDIR}/bootup
    echo 'echo "1.0" > /proc/stb/info/version' >> ${WORKDIR}/bootup
    echo 'sleep 1' >> ${WORKDIR}/bootup
    echo 'echo 50 > /proc/progress' >> ${WORKDIR}/bootup
    install -m 0755 ${WORKDIR}/bootup ${D}${sysconfdir}/init.d
    ln -sf ../init.d/bootup ${D}${sysconfdir}/rcS.d/S66bootup
}
FILES:${PN} = "/boot"

INHIBIT_PACKAGE_STRIP = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"
