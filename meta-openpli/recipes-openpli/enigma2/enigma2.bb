SUMMARY = "GUI frontend for Open Source Linux based receivers"
DESCRIPTION = "Enigma2 is a framebuffer based frontend for DVB functions on Linux settop boxes"
MAINTAINER = "OpenPLi team <info@openpli.org>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
	avahi \
	freetype \
	gettext-native \
	jpeg \
	libdreamdvd libdvbsi++ fribidi libmad libpng libsigc++-3 giflib libxml2 \
	openssl libudfread \
	python3-twisted python3-wifi \
	python3-six-native \
	swig-native \
	tuxtxt-enigma2 \
	"

# SoftcamSetup, SkinSelector and Systemtime is integrated now
RREPLACES:${PN} = "enigma2-plugin-pli-softcamsetup enigma2-plugin-systemplugins-skinselector enigma2-plugin-systemplugins-systemtime"
RCONFLICTS:${PN} = "enigma2-plugin-pli-softcamsetup enigma2-plugin-systemplugins-skinselector enigma2-plugin-systemplugins-systemtime"

RDEPENDS:${PN} = " \
	alsa-conf \
	enigma2-fonts \
	enigma-info \	
	ethtool \
	${PYTHON_RDEPS} \
	"

RDEPENDS:${PN}:append:libc-glibc = " \
	glibc-gconv-iso8859-15 \
	glibc-gconv-cp1250 \
	"

RRECOMMENDS:${PN} = " \
	enigma2-plugin-skins-pli-hd \
	hotplug-e2-helper \
	python3-sendfile \
	ofgwrite \
	virtual/enigma2-mediaservice \
	"

RRECOMMENDS:${PN}:append:libc-glibc = " glibc-gconv-utf-16"

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
    ${PYTHON_PN}-urllib3 \
    ${PYTHON_PN}-xml \
    ${PYTHON_PN}-zopeinterface \
    ${PYTHON_PN}-pillow \
    ${PYTHON_PN}-smtpd \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-treq \
"

# DVD and iso playback is integrated, we need the libraries
RDEPENDS:${PN} += "libdreamdvd libudfread"
RRECOMMENDS:${PN} += "libdvdcss"

# We depend on the font which we use for TXT subtitles (defined in skin_subtitles.xml)
RDEPENDS:${PN} += "font-valis-enigma"

RDEPENDS:${PN} += "${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbc", "virtual/blindscan-dvbc" , "", d)}"

DEMUXTOOL ?= "replex"

DESCRIPTION:append:enigma2-plugin-extensions-cutlisteditor = "enables you to cut your movies."
DESCRIPTION:append:enigma2-plugin-extensions-graphmultiepg = "shows a graphical timeline EPG."
DESCRIPTION:append:enigma2-plugin-extensions-pictureplayer = "displays photos on the TV."
DESCRIPTION:append:enigma2-plugin-systemplugins-positionersetup = "helps you installing a motorized dish."
DESCRIPTION:append:enigma2-plugin-systemplugins-satelliteequipmentcontrol = "allows you to fine-tune DiSEqC-settings."
DESCRIPTION:append:enigma2-plugin-systemplugins-satfinder = "helps you to align your dish."
DESCRIPTION:append:enigma2-plugin-systemplugins-videomode = "selects advanced video modes"
DESCRIPTION:append:enigma2-plugin-systemplugins-wirelesslan = "helps you configuring your wireless lan"
DESCRIPTION:append:enigma2-plugin-systemplugins-networkwizard = "provides easy step by step network configuration"

RDEPENDS:enigma2-plugin-extensions-cutlisteditor = "aio-grab"
RDEPENDS:enigma2-plugin-systemplugins-nfiflash = "python3-twisted-web"
RDEPENDS:enigma2-plugin-systemplugins-softwaremanager = "python3-twisted-web"
RDEPENDS:enigma2-plugin-systemplugins-wirelesslan = "wpa-supplicant wireless-tools python3-wifi"

# Note that these tools lack recipes
RDEPENDS:enigma2-plugin-extensions-dvdburn = "dvd+rw-tools dvdauthor mjpegtools genisoimage python3-imaging ${DEMUXTOOL}"
RDEPENDS:enigma2-plugin-systemplugins-hotplug = "hotplug-e2-helper"
RRECOMMENDS:enigma2-plugin-extensions-dvdplayer = "kernel-module-udf"

# Fake package that doesn't actually get built, but allows OE to detect
# the RDEPENDS for the plugins above, preventing [build-deps] warnings.
RDEPENDS:${PN}-build-dependencies = "\
	aio-grab \
	dvd+rw-tools dvdauthor mjpegtools cdrkit ${DEMUXTOOL} \
	python3-pillow \
	wpa-supplicant wireless-tools python3-wifi \
	python3-twisted-web \
	"
RRECOMMENDS:${PN}-build-dependencies = "\
	kernel-module-udf \
	"

inherit gitpkgv setuptools3 python3targetconfig

PV = "${PYTHON_BASEVERSION}+git${SRCPV}"
PKGV = "${PYTHON_BASEVERSION}+git${GITPKGV}"

ENIGMA2_BRANCH ?= "develop"
GITHUB_URI ?= "git://github.com"

# make the origin overridable from OE config, for local mirroring
SRC_URI = "${GITHUB_URI}/OpenPLi/enigma2.git;branch=${ENIGMA2_BRANCH};protocol=https \
			file://06-fix-build-gcc11.patch \
			file://07-suppress-compile-errors.patch \
			file://11-Add-remote-control-dmm2.patch \
			file://16-fix-write-console.patch \
			file://21-cast-to-integers.patch \
"

LDFLAGS:prepend = " -lxml2 "

S = "${WORKDIR}/git"

PACKAGES += "${PN}-meta ${PN}-build-dependencies enigma2-fonts"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

PKGV:enigma2-fonts = "2020.10.17"

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
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

# pass the enigma branch to automake
EXTRA_OEMAKE = "\
	ENIGMA2_BRANCH=${ENIGMA2_BRANCH} \
	"

FILES:enigma2-fonts = "${datadir}/fonts"

FILES:${PN} += "${datadir}/keymaps"

FILES:${PN}-meta = "${datadir}/meta"

# some plugins contain so's, their stripped symbols should not end up in the enigma2 package
FILES:${PN}-dbg += "\
	${libdir}/enigma2/python/Plugins/*/*/.debug \
	"

# Swig generated 200k enigma.py file has no purpose for end users
# Save some space by not installing sources (Startup.py must remain)
FILES:${PN}-src += "\
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
	${libdir}/enigma.info \
	"

do_install:append() {
	install -d ${D}${datadir}/keymaps
}

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.py$', 'enigma2-plugin-%s-src', '%s (sources)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True, extra_depends='')
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True, extra_depends='')
}
