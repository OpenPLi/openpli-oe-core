#! /bin/sh
IPKG=/usr/bin/opkg
if ! $IPKG list_installed | grep -q $1
then
	$IPKG install ${packageoption} $1
else
	echo skipped already installed package $1
fi
