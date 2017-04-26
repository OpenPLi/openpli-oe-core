DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-subssupport"

PROVIDES += "enigma2-plugin-extensions-kodi"
RPROVIDES_${PN} += "enigma2-plugin-extensions-kodi"

SRC_URI = "git://github.com/mx3L/kodiext;protocol=git;branch=master \
        file://0001-make-transparant.patch \
        "

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext"

inherit autotools
