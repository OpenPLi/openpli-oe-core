DESCRIPTION = "Extra fan control - using HDD/SSD/CPU temp"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=8e3a3f140c512edb33d2e027096fd1cc"

PLUGINNAME = "enigma2-plugin-systemplugins-extrafancontrol"

require dima-plugins.inc

RDEPENDS_${PN} = "hddtemp"
