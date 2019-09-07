SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=21c760b839b67e25d55d6dabc6abee53"

inherit pypi setuptools

SRC_URI[md5sum] = "9fe70cbc812b2e789915bedea7e45b1a"
SRC_URI[sha256sum] = "580f7dcf8048656d2924c2f78731feaeaf6f9fa8ef5c251fc6964d39bfd3a6b9"

include python-package-split.inc
