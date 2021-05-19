SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06f070053828db68be36bd1c8c95ca5e"

inherit pypi setuptools

SRCNAME = "pysmb"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.zip"

SRC_URI[md5sum] = "72bb3b08a3ae3ff34eda519a49afff3f"
SRC_URI[sha256sum] = "f16e5e796b9dcc1d17ee76f87d53dd471f205fa19b4045eeda5bc7558a57d579"

include python-package-split.inc
