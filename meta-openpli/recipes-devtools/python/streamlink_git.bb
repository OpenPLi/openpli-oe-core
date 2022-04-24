SUMMARY = "CLI for extracting streams from various websites to a video player of your choosing"
DESCRIPTION = "Streamlink is a command-line utility that pipes video streams from various services into a video player, such as VLC."
HOMEPAGE = "https://github.com/streamlink/streamlink"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=381ff91bf309000e0ec58dafe27a97b0"

RDEPENDS_${PN} = "${PYTHON_PN}-core \
	${PYTHON_PN}-ctypes \
	${PYTHON_PN}-isodate \
	${PYTHON_PN}-pycountry \
	${PYTHON_PN}-lxml \
	${PYTHON_PN}-misc \
	${PYTHON_PN}-pkgutil \
	${PYTHON_PN}-pycryptodome \
	${PYTHON_PN}-pysocks \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-shell \
	${PYTHON_PN}-singledispatch \
	${PYTHON_PN}-websocket-client \
"

inherit setuptools3 python3-dir gittag

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRCREV_plugins = "${AUTOREV}"
SRCREV_FORMAT = "streamlink_plugins"

SRC_URI = " git://github.com/streamlink/streamlink;protocol=https;branch=master \
			file://0001-Revert-build-move-imports-in-setup.py.patch \
			file://0002-Revert-build-add-pyproject.toml-switch-to-versioning.patch \
			git://github.com/oe-mirrors/streamlink-plugins;protocol=https;branch=master;name=plugins;destsuffix=additional-plugins \
"

S = "${WORKDIR}/git"

do_unpack_append() {
    bb.build.exec_func('do_prepare_plugins_dir', d)
}

do_prepare_plugins_dir() {
	cp -f ${WORKDIR}/additional-plugins/*.py ${S}/src/streamlink/plugins
}

do_install_append() {
	rm -rf ${D}${bindir}
	rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/streamlink_cli
	rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/*.egg-info
	rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/streamlink/plugins/.removed
	rm -rf ${D}${datadir}
}

PACKAGES = "${PN}"

FILES_${PN} = " \
	${libdir}/${PYTHON_DIR}/site-packages/streamlink/* \
"
