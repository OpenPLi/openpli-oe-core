MODULE = "AutoBackup"
SUMMARY = "Backup settings and restore them automatically"
DESCRIPTION = "Can create daily backups. Backups created will be restored automaticaly after a new flash."

require conf/license/license-gplv2.inc

# I spent over 2 hours trying to get the shell file to be executable. Sorry, I just gave up
# and decided that this would be good enough until someone explains how to do this properly
# with distutils.
do_install:append() {
	chmod a+x ${D}${libdir}/enigma2/python/Plugins/*/*/*.sh
}

inherit distutils-openplugins python3-compileall

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

DEPENDS = "python3-future-native"

SRC_URI = "git://github.com/OpenPLi/AutoBackup.git;branch=master;protocol=https"

S="${WORKDIR}/git"
