SUMMARY = "pyLoad is a fast, lightweight and full featured download manager for many One-Click-Hoster"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=2d20d11c1dccf4454ce811d6bfb05a2b"
HOMEPAGE = "http://pyload.org/"

RDEPENDS_${PN} = "\
	${PYTHON_PN}-compression \
	${PYTHON_PN}-db \
	${PYTHON_PN}-email \
	${PYTHON_PN}-html \
	${PYTHON_PN}-pillow  \
	${PYTHON_PN}-numbers \
	${PYTHON_PN}-pprint \
	${PYTHON_PN}-pycryptodome \
	${PYTHON_PN}-pycurl \
	${PYTHON_PN}-sqlite3 \
	${PYTHON_PN}-terminal \
	${PYTHON_PN}-unixadmin \
	${PYTHON_PN}-xmlrpc \
"
RRECOMMENDS_${PN} = "unrar"

inherit gittag update-rc.d

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/pyload/pyload.git;protocol=https;branch=main \
	file://pyload.init \
	file://pyload.tar.gz.defaults"

S = "${WORKDIR}/git"

FILES_${PN} = "/usr/pyload/* /etc/*"

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "defaults 60 "

do_compile() {
	python3 -m compileall ${S}
}

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/usr/pyload

	cp ${WORKDIR}/pyload.tar.gz.defaults ${D}/usr/pyload/pyload-defaults.tar.gz

	install -m 0755 ${WORKDIR}/pyload.init ${D}/etc/init.d/pyload
}
