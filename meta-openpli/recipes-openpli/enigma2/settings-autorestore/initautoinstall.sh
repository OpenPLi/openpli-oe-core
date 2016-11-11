#! /bin/sh
# This script is run once when your box boots for the first time.

INSTALLED=/etc/installed
IPKG=/usr/bin/opkg
${IPKG} list_installed | cut -d ' ' -f 1 > ${INSTALLED}
chmod 444 ${INSTALLED}
# suicide...
rm -f /etc/rc?.d/S*initautoinstall*
