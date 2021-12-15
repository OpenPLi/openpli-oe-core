DESCRIPTION = "Search the internet bases themoviedb.org/kinopoisk.ru"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=a1f8725511fa113a2b2a282860d4fc19"

PLUGINNAME = "enigma2-plugin-extensions-tmbd"

require dima-plugins.inc

RDEPENDS_${PN} = " \
	${PYTHON_VER}-twisted-web \
	${PYTHON_VER}-xml \
	${PYTHON_VER}-shell \
	${PYTHON_VER}-misc \
	${PYTHON_VER}-html \
	${PYTHON_VER}-subprocess \
	${PYTHON_VER}-unixadmin \
	${PYTHON_VER}-lxml \
	"
