require linux-firmware.inc

DESCRIPTION = "Firmware for as102_data1_st"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 as102_data1_st.hex ${D}${base_libdir}/firmware
}
