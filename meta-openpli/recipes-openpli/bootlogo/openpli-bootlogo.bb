DESCRIPTION = "OpenPLi bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "PLi team"

require conf/license/openpli-gplv2.inc

RDEPENDS_${PN} += "showiframe"

PV = "3.0"
PR = "r3"

S = "${WORKDIR}/"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 21 S ."

inherit update-rc.d

# This needs a small explanation; when the machine has 'switchoff' support, it switches itself off, so we don't need switchoff.mvi...
SWITCHOFFMVI = "${@base_contains("MACHINE_FEATURES", "switchoff", "" , "switchoff.mvi", d)}"

SRC_URI = " \
	file://bootlogo.mvi \
	file://switchoff.mvi \
	file://bootlogo.jpg \
	file://bootlogo.sh \
	"

BINARY_VERSION = "9"

SRC_URI += "${@base_contains("MACHINE_FEATURES", "dreambox", "http://sources.dreamboxupdate.com/download/7020/bootlogo-${MACHINE}-${BINARY_VERSION}.elf" , "", d)}"

SRC_URI[md5sum] = "5b7aa440ef459b2470fe45af9e123811"
SRC_URI[sha256sum] = "81363d7ab6497da8a905080871ebc8268cf7a946d4ea0fa18d6f126ed77e13b7"

MVI = "${SWITCHOFFMVI} bootlogo.mvi"
MVISYMLINKS = "bootlogo_wait backdrop"

do_install() {
	if [ -n "${BRANDINGDIR}" -a -d "${BRANDINGDIR}/bootlogo" ] ; then
		cp -p ${BRANDINGDIR}/bootlogo/* ${S}/.
	fi

	install -d ${D}/boot
	install -d ${D}/usr/share
	for i in ${MVI}; do
		install -m 0755 ${S}/$i ${D}/usr/share/
		ln -sf /usr/share/$i ${D}/boot/$i
	done;
	${@base_contains("MACHINE_FEATURES", "dreambox", "install -m 0755 ${S}/bootlogo-${MACHINE}-${BINARY_VERSION}.elf ${D}/boot/bootlogo.elf; install -m 0755 ${S}/bootlogo.jpg ${D}/boot/", "", d)}
	for i in ${MVISYMLINKS}; do
		ln -sf /boot/bootlogo.mvi ${D}/boot/$i.mvi
		ln -sf /usr/share/bootlogo.mvi ${D}/usr/share/$i.mvi;
	done;
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}

pkg_preinst() {
	[ -d /proc/stb ] && mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
	true
}

pkg_postinst() {
	[ -d /proc/stb ] && umount /boot
	true
}

pkg_prerm() {
	[ -d /proc/stb ] && mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
	true
}

pkg_postrm() {
	[ -d /proc/stb ] && umount /boot
	true
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot /usr/share /etc/init.d"
