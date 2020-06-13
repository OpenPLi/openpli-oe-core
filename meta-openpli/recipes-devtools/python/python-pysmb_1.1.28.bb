SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f696da4bf6c34ef3b926285a84dfa60c"

inherit setuptools

SRCNAME = "pysmb"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.zip"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "eda1770d77888b85dabf4336efcc2452"
SRC_URI[sha256sum] = "58e4cae8057282968fbe47ae9a2c321d7e779df82fe2436d1f32304008f68474"

include python-package-split.inc
