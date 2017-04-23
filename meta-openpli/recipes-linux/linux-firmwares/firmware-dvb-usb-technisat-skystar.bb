require linux-firmware.inc

DESCRIPTION = "Firmware for Technisat Skystar USB HD"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 dvb-usb-SkyStar_USB_HD_FW_v17_63.HEX.fw ${D}${base_libdir}/firmware
}
