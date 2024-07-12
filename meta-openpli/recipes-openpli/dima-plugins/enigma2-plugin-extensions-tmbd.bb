DESCRIPTION = "Search the internet bases themoviedb.org/kinopoisk.ru"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=a1f8725511fa113a2b2a282860d4fc19"

PLUGINNAME = "enigma2-plugin-extensions-tmbd"

require dima-plugins.inc

RDEPENDS:${PN} = " \
	python3-twisted-web \
	python3-xml \
	python3-shell \
	python3-misc \
	python3-html \
	python3-unixadmin \
	python3-lxml \
	"
