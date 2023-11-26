SUMMARY = "Service identity verification for pyOpenSSL & cryptography."
DESCRIPTION = "service_identity aspires to give you all the tools you need for verifying whether a certificate is valid for the intended purposes."
HOMEPAGE = "https://service-identity.readthedocs.io/en/stable"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a0f079f4e6a215d6bd6f9d97cab4d5f"

RDEPENDS:${PN} = "python3-attrs python3-idna python3-pyasn1 python3-pyasn1-modules python3-pyopenssl"

SRCNAME = "service-identity"
SRC_URI = "https://files.pythonhosted.org/packages/09/2e/26ade69944773df4748c19d3053e025b282f48de02aad84906d34a29d28b/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "5e5c195d8fcedc72f9068be2ad9b5a13"
SRC_URI[sha256sum] = "6e6c6086ca271dc11b033d17c3a8bea9f24ebff920c587da090afc9519419d34"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools3

include python3-package-split.inc
