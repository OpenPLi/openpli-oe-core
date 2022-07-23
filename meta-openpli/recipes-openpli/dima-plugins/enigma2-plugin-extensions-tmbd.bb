DESCRIPTION = "Search the internet bases themoviedb.org/kinopoisk.ru"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=a1f8725511fa113a2b2a282860d4fc19"

PLUGINNAME = "enigma2-plugin-extensions-tmbd"

require dima-plugins.inc

RDEPENDS_${PN} = " \
	${PYTHON_PN}-twisted-web \
	${PYTHON_PN}-xml \
	${PYTHON_PN}-shell \
	${PYTHON_PN}-misc \
	${PYTHON_PN}-html \
	${PYTHON_PN}-unixadmin \
	${PYTHON_PN}-lxml \
	"

SRC_URI = "git://github.com/Dima73/enigma2-plugin-extensions-tmbd.git;protocol=https;branch=python3"
