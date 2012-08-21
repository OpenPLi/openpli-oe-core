DESCRIPTION = "Oscam binary, freshly compiled"
LICENSE = "GPLv3"

PV = "svn${SRCPV}"
PR = "r4"

DEPENDS = "libusb openssl"
RDEPENDS_${PN} = "openssl oscam-util-list-smargo"
RREPLACES_${PN} = "oscam-bin"
RCONFLICTS_${PN} = "oscam-bin"
RPROVIDES_${PN} = "${OSCAMBIN}"

#the three options below are not needed but makes it easier to trace the cmake generated cross compile files
#All generated files by cmake will be in a subdir called build_dir
OECMAKE_BUILDPATH = "${WORKDIR}/${MODULE}/build_dir"
OECMAKE_SOURCEPATH = "${WORKDIR}/${MODULE}"
EXTRA_OEMAKE = "-c ${OECMAKE_BUILDPATH} "

INHIBIT_PACKAGE_STRIP = "1"

include oscamurl.inc

SRC_URI = "svn://${OSCAMHOST}/${SUBURI}"

S = "${WORKDIR}/${MODULE}"

#added byc cvr to add wright libusb location
#For dreambox it does compile but doesn't  work, cause kernel needs to be above 2.6.30. by boxes with such high kernel it should work
#You still can use smartreader with mouse protocol and set speed with ttys0 utility on the smargo it works up to 600 mhz
#with libusb it works up to 1000 mhz for seca 3 and viaccess.


do_generate_toolchain_file_append() {
	echo "set( OPTIONAL_INCLUDE_DIR \"${STAGING_DIR_HOST}/usr/include/PCSC\" CACHE STRING \"Some optional include directories\" )" >> ${WORKDIR}/toolchain.cmake \
}

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWEBIF=1 \
	-DWITH_STAPI=0 \
	${LIBUSB} \
	-DSTATIC_LIBUSB=1 \
	-DWITH_SSL=1 \
	-DHAVE_PCSC=0 \
	"

inherit cmake update-alternatives

do_install_append() {
	mv "${D}${bindir}/oscam" "${D}${bindir}/${PN}"
}

ALTERNATIVE_NAME = "${OSCAMBIN}"
ALTERNATIVE_PATH = "${bindir}/${PN}"

