SUMMARY = "ISO country, subdivision, language, currency and script definitions and their translations"
HOMEPAGE = ""
AUTHOR = "Christian Theune <ct@flyingcircus.io>"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8cf1799061bb2f68625332ab7039e11f"

include python3-package-split.inc

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/33/24/033604d30f6cf82d661c0f9dfc2c71d52cafc2de516616f80d3b0600cb7c/pycountry-22.3.5.tar.gz \
            file://version.patch"
SRC_URI[md5sum] = "47a8668fc5d86fcd2c608c19846e2912"
SRC_URI[sha256sum] = "b2163a246c585894d808f18783e19137cb70a0c18fb36748dc01fc6f109c1646"

S = "${WORKDIR}/pycountry-${PV}"
