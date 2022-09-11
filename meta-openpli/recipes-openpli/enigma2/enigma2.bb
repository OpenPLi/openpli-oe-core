SUMMARY = "GUI frontend for Open Source Linux based receivers"
DESCRIPTION = "Enigma2 is a framebuffer based frontend for DVB functions on Linux settop boxes"
MAINTAINER = "OpenPLi team <info@openpli.org>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
	avahi \
	freetype \
	gettext-native \
	jpeg \
	libdreamdvd libdvbsi++ fribidi libmad libpng libsigc++-3 giflib libxml2 \
	openssl libudfread \
	${PYTHON_PN}-twisted ${PYTHON_PN}-wifi \
	${PYTHON_PN}-pillow \
	swig-native \
	tuxtxt-enigma2 \
	"

# SoftcamSetup, SkinSelector and Systemtime is integrated now
RREPLACES_${PN} = "enigma2-plugin-pli-softcamsetup enigma2-plugin-systemplugins-skinselector enigma2-plugin-systemplugins-systemtime"
RCONFLICTS_${PN} = "enigma2-plugin-pli-softcamsetup enigma2-plugin-systemplugins-skinselector enigma2-plugin-systemplugins-systemtime"

RDEPENDS_${PN} = " \
	alsa-conf \
	enigma2-fonts \
	ethtool \
	${PYTHON_RDEPS} \
	"

RDEPENDS_${PN}_append_libc-glibc = " glibc-gconv-iso8859-15"

RRECOMMENDS_${PN} = " \
	enigma2-plugin-skins-pli-hd \
	hotplug-e2-helper \
	${PYTHON_PN}-sendfile \
	ofgwrite \
	virtual/enigma2-mediaservice \
	"

RRECOMMENDS_${PN}_append_libc-glibc = " glibc-gconv-utf-16"

PYTHON_RDEPS = " \
	${PYTHON_PN}-codecs \
	${PYTHON_PN}-core \
	${PYTHON_PN}-crypt \
	${PYTHON_PN}-fcntl \
	${PYTHON_PN}-logging \
	${PYTHON_PN}-mmap \
	${PYTHON_PN}-netclient \
	${PYTHON_PN}-netifaces \
	${PYTHON_PN}-netserver \
	${PYTHON_PN}-numbers \
	${PYTHON_PN}-pickle \
	${PYTHON_PN}-shell \
	${PYTHON_PN}-threading \
	${PYTHON_PN}-twisted-core \
	${PYTHON_PN}-twisted-web \
	${PYTHON_PN}-xml \
	${PYTHON_PN}-zopeinterface \
	"

# DVD and iso playback is integrated, we need the libraries
RDEPENDS_${PN} += "libdreamdvd libudfread"
RRECOMMENDS_${PN} += "libdvdcss"

# We depend on the font which we use for TXT subtitles (defined in skin_subtitles.xml)
RDEPENDS_${PN} += "font-valis-enigma"

RDEPENDS_${PN} += "${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbc", "virtual/blindscan-dvbc" , "", d)}"

DEMUXTOOL ?= "replex"

DESCRIPTION_append_enigma2-plugin-extensions-cutlisteditor = "enables you to cut your movies."
DESCRIPTION_append_enigma2-plugin-extensions-graphmultiepg = "shows a graphical timeline EPG."
DESCRIPTION_append_enigma2-plugin-extensions-pictureplayer = "displays photos on the TV."
DESCRIPTION_append_enigma2-plugin-systemplugins-positionersetup = "helps you installing a motorized dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-satelliteequipmentcontrol = "allows you to fine-tune DiSEqC-settings."
DESCRIPTION_append_enigma2-plugin-systemplugins-satfinder = "helps you to align your dish."
DESCRIPTION_append_enigma2-plugin-systemplugins-videomode = "selects advanced video modes"
DESCRIPTION_append_enigma2-plugin-systemplugins-wirelesslan = "helps you configuring your wireless lan"
DESCRIPTION_append_enigma2-plugin-systemplugins-networkwizard = "provides easy step by step network configuration"

RDEPENDS_enigma2-plugin-extensions-cutlisteditor = "aio-grab"
RDEPENDS_enigma2-plugin-systemplugins-nfiflash = "${PYTHON_PN}-twisted-web"
RDEPENDS_enigma2-plugin-systemplugins-softwaremanager = "${PYTHON_PN}-twisted-web"
RDEPENDS_enigma2-plugin-systemplugins-wirelesslan = "wpa-supplicant wireless-tools ${PYTHON_PN}-wifi"

# Note that these tools lack recipes
RDEPENDS_enigma2-plugin-extensions-dvdburn = "dvd+rw-tools dvdauthor mjpegtools genisoimage ${PYTHON_PN}-imaging ${DEMUXTOOL} \
                                              ${PYTHON_PN}-pillow"
RDEPENDS_enigma2-plugin-systemplugins-hotplug = "hotplug-e2-helper"
RRECOMMENDS_enigma2-plugin-extensions-dvdplayer = "kernel-module-udf"

# Fake package that doesn't actually get built, but allows OE to detect
# the RDEPENDS for the plugins above, preventing [build-deps] warnings.
RDEPENDS_${PN}-build-dependencies = "\
	aio-grab \
	dvd+rw-tools dvdauthor mjpegtools cdrkit ${DEMUXTOOL} \
	${PYTHON_PN}-pillow \
	wpa-supplicant wireless-tools ${PYTHON_PN}-wifi \
	${PYTHON_PN}-twisted-web \
	"
RRECOMMENDS_${PN}-build-dependencies = "\
	kernel-module-udf \
	"

inherit gitpkgv setuptools3

PV = "${PYTHON_BASEVERSION}+git${SRCPV}"
PKGV = "${PYTHON_BASEVERSION}+git${GITPKGV}"

ENIGMA2_BRANCH ?= "develop"

# make the origin overridable from OE config, for local mirroring
SRC_ORIGIN ?= "git://github.com/OpenPLi/enigma2.git;protocol=https"
SRC_URI := " ${SRC_ORIGIN};branch=${ENIGMA2_BRANCH}"

LDFLAGS_prepend = " -lxml2 "

S = "${WORKDIR}/git"

PACKAGES += "${PN}-meta ${PN}-build-dependencies enigma2-fonts"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

PKGV_enigma2-fonts = "2020.10.17"

def get_crashaddr(d):
    if d.getVar('CRASHADDR', True):
        return '--with-crashlogemail="${CRASHADDR}"'
    else:
        return ''

EXTRA_OECONF = "\
	--with-libsdl=no --with-boxtype=${MACHINE} \
	--enable-dependency-tracking \
	ac_cv_prog_c_openmp=-fopenmp \
	${@get_crashaddr(d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7segment", "--with-7segment" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7seg", "--with-7segment" , "", d)} \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

# pass the enigma branch to automake
EXTRA_OEMAKE = "\
	ENIGMA2_BRANCH=${ENIGMA2_BRANCH} \
	"

FILES_enigma2-fonts = "${datadir}/fonts"

FILES_${PN} += "${datadir}/keymaps"

FILES_${PN}-meta = "${datadir}/meta"

# some plugins contain so's, their stripped symbols should not end up in the enigma2 package
FILES_${PN}-dbg += "\
	${libdir}/enigma2/python/Plugins/*/*/.debug \
	"

# Swig generated 200k enigma.py file has no purpose for end users
# Save some space by not installing sources (Startup.py must remain)
FILES_${PN}-src += "\
	${libdir}/enigma2/python/e2reactor.py \
	${libdir}/enigma2/python/enigma.py \
	${libdir}/enigma2/python/GlobalActions.py \
	${libdir}/enigma2/python/keyids.py \
	${libdir}/enigma2/python/keymapparser.py \
	${libdir}/enigma2/python/Navigation.py \
	${libdir}/enigma2/python/NavigationInstance.py \
	${libdir}/enigma2/python/RecordTimer.py \
	${libdir}/enigma2/python/ServiceReference.py \
	${libdir}/enigma2/python/SleepTimer.py \
	${libdir}/enigma2/python/skin.py \
	${libdir}/enigma2/python/timer.py \
	${libdir}/enigma2/python/*/*.py \
	${libdir}/enigma2/python/*/*/*.py \
	${libdir}/enigma2/python/*/*/*/*.py \
	"

do_install_append() {
	install -d ${D}${datadir}/keymaps
}

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.py$', 'enigma2-plugin-%s-src', '%s (sources)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True, extra_depends='')
}
