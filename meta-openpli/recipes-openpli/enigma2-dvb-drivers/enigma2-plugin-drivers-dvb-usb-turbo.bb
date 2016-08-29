SUMMARY = "USB DVB driver for Vuplus Tuner Turbo"

LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = " \
    kernel-module-dvb-usb \
    kernel-module-dvb-usb-v2 \
    kernel-module-cypress-firmware \
    kernel-module-dvb-usb-cypress-firmware \
    vuplus-tuner-turbo \
    "

PV = "1.0"

ALLOW_EMPTY_${PN} = "1"
