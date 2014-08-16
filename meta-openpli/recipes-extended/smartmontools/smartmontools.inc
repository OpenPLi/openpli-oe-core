SECTION = "console/utils"
SUMMARY = "Control and monitor storage systems using S.M.A.R.T."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
INC_PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/smartmontools/smartmontools-${PV}.tar.gz"
S = "${WORKDIR}/smartmontools-${PV}"

inherit autotools

# Fixup for missing "foreign" and "subdir-objects" in automake
do_configure_prepend() {
	sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.in
}
