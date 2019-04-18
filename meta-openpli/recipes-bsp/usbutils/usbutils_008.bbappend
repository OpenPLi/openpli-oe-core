SUMMARY = "Host side USB console utilities"
DESCRIPTION = "Contains the lsusb utility for inspecting the devices connected to the USB bus."
HOMEPAGE = "http://www.linux-usb.org"
SECTION = "base"

DEPENDS_remove = "udev"
RRECOMMENDS_${PN}_remove = "udev-hwdb"
