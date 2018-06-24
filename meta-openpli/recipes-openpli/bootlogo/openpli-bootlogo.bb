DESCRIPTION = "OpenPLi bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "PLi team"
LICENSE = "CC-BY-NC-ND-4.0"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/meta-openpli/licenses/CC-BY-NC-ND-4.0;md5=8009795292660aa9c8da059e5b1581c1"

RDEPENDS_${PN} += "showiframe"

PKGV = "${DATE}"

# required in bitbake 1.32-
PKGV[vardepsexclude] += "DATE"

# switch to this in bitbake 1.34+
BB_HASH_IGNORE_MISMATCH = "1"

S = "${WORKDIR}"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 21 S ."

# don't add allarch, it doesn't work due to RDEPENDS on showiframe
inherit update-rc.d

# This needs a small explanation; when the machine has 'switchoff' support, it switches itself off, so we don't need switchoff.mvi...
SWITCHOFFMVI = "${@bb.utils.contains("MACHINE_FEATURES", "switchoff", "" , "switchoff.mvi", d)}"

SRC_URI = " \
	file://bootlogo.mvi \
	file://switchoff.mvi \
	file://bootlogo.sh \
	file://logo-black-image.png \
	file://logo-black-square.png \
	file://logo-black.png \
	file://logo-white-image.png \
	file://logo-white-square.png \
	file://logo-white.png"

MVI = "${SWITCHOFFMVI} bootlogo.mvi"
MVISYMLINKS = "bootlogo_wait backdrop"

PNG = "logo-black-image.png \
	logo-black-square.png \
	logo-black.png \
	logo-white-image.png \
	logo-white-square.png \
	logo-white.png"

do_install() {
	install -d ${D}/boot
	install -d ${D}${datadir}
	for i in ${MVI}; do
		install ${S}/$i ${D}${datadir}
		ln -sf ${datadir}/$i ${D}/boot/$i
	done
	for i in ${MVISYMLINKS}; do
		ln -sf /boot/bootlogo.mvi ${D}/boot/$i.mvi
		ln -sf ${datadir}/bootlogo.mvi ${D}${datadir}/$i.mvi;
	done
	install -d ${D}${datadir}/logo
	for i in ${PNG}; do
		install ${S}/$i ${D}${datadir}/logo
	done
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}

FILES_${PN} = "/boot ${datadir} ${sysconfdir}/init.d"

do_compile[nostamp] = "1"
do_install[nostamp] = "1"
do_package[nostamp] = "1"
do_packagedata[nostamp] = "1"
do_package_write[nostamp] = "1"
do_populate_sysroot[nostamp] = "1"
do_strip[nostamp] = "1"
do_deploy[nostamp] = "1"
