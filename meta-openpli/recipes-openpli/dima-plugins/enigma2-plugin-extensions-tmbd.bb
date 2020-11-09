DESCRIPTION = "Search the internet bases themoviedb.org/kinopoisk.ru"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=a1f8725511fa113a2b2a282860d4fc19"

PLUGINNAME = "enigma2-plugin-extensions-tmbd"

require dima-plugins.inc

RDEPENDS_${PN} = " \
	python-twisted-web \
	python-xml \
	python-shell \
	python-misc \
	python-html \
	python-subprocess \
	python-unixadmin \
	python-lxml \
	"
