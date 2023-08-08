SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06f070053828db68be36bd1c8c95ca5e"

inherit setuptools3

SRCNAME = "pysmb"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.zip"

SRC_URI[md5sum] = "ddf766e0547f38cecf9a3f4c47e6e3c2"
SRC_URI[sha256sum] = "ad613988d54b1317ca0466dc3546f47b2dddea16e645d755d29fb75a86903326"

S = "${WORKDIR}/${SRCNAME}-${PV}"

include python3-package-split.inc
