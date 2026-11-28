SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=148dfe87589388f86bab72dd27ab8fa1"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-pyasn1 python3-tqdm"

SRC_URI[sha256sum] = "1c12eb0e251152e80b4bde5d816b33cab14d04b261b5f790cb4a5d9fd9653881"

# S = "${WORKDIR}/${SRCNAME}-${PV}"

include python3-package-split.inc
