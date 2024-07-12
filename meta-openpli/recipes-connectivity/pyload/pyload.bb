SUMMARY = "pyLoad is a fast, lightweight and full featured download manager for many One-Click-Hoster"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=2d20d11c1dccf4454ce811d6bfb05a2b"
HOMEPAGE = "http://pyload.org/"

RDEPENDS:${PN} = "\
	python3-compression \
	python3-db \
	python3-email \
	python3-html \
	python3-pillow  \
	python3-numbers \
	python3-pprint \
	python3-pycryptodome \
	python3-pycurl \
	python3-sqlite3 \
	python3-terminal \
	python3-unixadmin \
	python3-xmlrpc \
"
RRECOMMENDS:${PN} = "unrar"

inherit gittag update-rc.d

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/pyload/pyload.git;protocol=https;branch=main \
	file://pyload.init \
	file://pyload.tar.gz.defaults"

S = "${WORKDIR}/git"

FILES:${PN} = "/usr/pyload/* /etc/*"

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

include python3-package-split.inc
