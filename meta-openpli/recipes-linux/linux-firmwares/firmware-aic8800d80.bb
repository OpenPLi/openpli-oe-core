require linux-firmware.inc

DESCRIPTION = "Firmware for AIC8800D80"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/aic8800D80
    install -m 0644 aic8800d80/aic_powerlimit_8800d80.txt ${D}${nonarch_base_libdir}/firmware/aic8800D80/aic_powerlimit_8800d80.txt
    install -m 0644 aic8800d80/aic_userconfig_8800d80.txt ${D}${nonarch_base_libdir}/firmware/aic8800D80/aic_userconfig_8800d80.txt
    install -m 0644 aic8800d80/calibmode_8800d80.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/calibmode_8800d80.bin
    install -m 0644 aic8800d80/fmacfw_8800d80_u02.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fmacfw_8800d80_u02.bin
    install -m 0644 aic8800d80/fmacfw_8800d80_u02_ipc.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fmacfw_8800d80_u02_ipc.bin
    install -m 0644 aic8800d80/fw_adid_8800d80_u02.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fw_adid_8800d80_u02.bin
    install -m 0644 aic8800d80/fw_ble_scan_ad_filter.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fw_ble_scan_ad_filter.bin
    install -m 0644 aic8800d80/fw_patch_8800d80_u02.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fw_patch_8800d80_u02.bin
    install -m 0644 aic8800d80/fw_patch_table_8800d80_u02.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/fw_patch_table_8800d80_u02.bin
    install -m 0644 aic8800d80/lmacfw_rf_8800d80_u02.bin ${D}${nonarch_base_libdir}/firmware/aic8800D80/lmacfw_rf_8800d80_u02.bin
}
