SUMMARY = "Console text editor with good functionality, good choice for vi-haters."
HOMEPAGE = "http://joe-editor.sourceforge.net/"
SECTION = "console/utils"
LICENSE = "GPL-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "${SOURCEFORGE_MIRROR}/joe-editor/joe-${PV}.tar.gz"
SRC_URI[md5sum] = "9017484e6116830d846678b625ea5c43"
SRC_URI[sha256sum] = "495a0a61f26404070fe8a719d80406dc7f337623788e445b92a9f6de512ab9de"

inherit autotools-brokensep update-alternatives pkgconfig 

RDEPENDS_${PN} = "ncurses-terminfo"

EXTRA_OECONF_prepend="--bindir=/bin"

ALTERNATIVE_${PN} = "editor"
ALTERNATIVE_LINK_NAME[editor] = "${base_bindir}/editor"
ALTERNATIVE_TARGET[editor] = "${base_bindir}/joe"
ALTERNATIVE_PRIORITY = "125"
