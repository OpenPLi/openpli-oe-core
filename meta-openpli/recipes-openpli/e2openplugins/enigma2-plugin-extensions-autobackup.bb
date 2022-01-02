MODULE = "AutoBackup"
SUMMARY = "Backup settings and restore them automatically"
DESCRIPTION = "Can create daily backups. Backups created will be restored automaticaly after a new flash."

require conf/license/license-gplv2.inc

require openplugins-replace-pli.inc
require openplugins-distutils.inc

# I spent over 2 hours trying to get the shell file to be executable. Sorry, I just gave up
# and decided that this would be good enough until someone explains how to do this properly
# with distutils.
do_install_append() {
	chmod a+x ${D}${libdir}/enigma2/python/Plugins/*/*/*.sh
}

DEPENDS = "python3-future-native"
