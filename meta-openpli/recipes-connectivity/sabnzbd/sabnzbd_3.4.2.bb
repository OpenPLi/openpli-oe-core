MAINTAINER = "team@sabnzbd.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=d4bfc20f13bd0e7363c57525136734ee"

PR = "r1"

DEPENDS = "python3"
RDEPENDS:${PN} = "\
	python3-core python3-shell python3-compression python3-crypt python3-ctypes python3-sqlite3 \
	python3-cheetah python3-misc python3-html python3-email python3-yenc \
	"
RRECOMMENDS:${PN} = "par2cmdline unrar"

SRC_URI = "https://github.com/sabnzbd/sabnzbd/archive/${PV}.tar.gz \
	file://sabnzbd \
	file://sabnzbd.conf \
	file://init-functions \
	"

SRC_URI[md5sum] = "963a07c3fac84e79494728092cfad9cd"
SRC_URI[sha256sum] = "ac0794a697a25bfff55e3d3fe8af3941ec50c01e51e6cd6a869c35329587db33"

S = "${WORKDIR}/sabnzbd-${PV}"

INSTALLDIR = "${libdir}/${PN}"

PACKAGES = "${PN}-doc ${PN}-src ${PN}"

FILES:${PN}-src = "${INSTALLDIR}/*/*.py ${INSTALLDIR}/*/*/*.py"
RDEPENDS:${PN}-src = "python3"
FILES:${PN}-doc = "${INSTALLDIR}/*.txt ${INSTALLDIR}/licenses ${INSTALLDIR}/interfaces/*/licenses"
FILES:${PN} = "${INSTALLDIR} /etc/init.d/sabnzbd /etc/init.d/init-functions /etc/enigma2/sabnzbd.conf"

inherit update-rc.d
INITSCRIPT_NAME = "sabnzbd"
INITSCRIPT_PARAMS = "defaults"

do_compile() {
	python3 -O -m compileall .
}

do_install() {
	install -d ${D}${INSTALLDIR}
	cp -r . ${D}${INSTALLDIR}/
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/sabnzbd ${D}${sysconfdir}/init.d/sabnzbd
	install -m 755 ${WORKDIR}/init-functions ${D}${sysconfdir}/init.d/init-functions
	install -d ${D}${sysconfdir}/enigma2
	install -m 644 ${WORKDIR}/sabnzbd.conf ${D}/etc/enigma2/sabnzbd.conf
}
