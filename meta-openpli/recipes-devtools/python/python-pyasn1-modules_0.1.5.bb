SUMMARY = "A collection of ASN.1-based protocols modules"
DESCRIPTION = "A collection of ASN.1 modules expressed in form of pyasn1 classes. Includes protocols PDUs definition (SNMP, LDAP etc.) and various data structures (X.509, PKCS etc.)."
HOMEPAGE = "https://github.com/etingof/pyasn1-modules"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=280606d9c18f200e03e0c247ac61475a"

inherit pypi setuptools

SRC_URI[md5sum] = "45b7ea93e3513d1198cac6c9471f0a75"
SRC_URI[sha256sum] = "1d303eed5aa54cafeca209d16b8c7ea2c6064735fb61f1bee2e0ed63a0816988"

RDEPENDS_${PN} = "python-pyasn1"
