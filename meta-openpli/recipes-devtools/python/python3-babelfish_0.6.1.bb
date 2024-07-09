SUMMARY  = "A module to work with countries and languages"
HOMEPAGE = "https://github.com/Diaoul/babelfish"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6570714fbba8ff492626c8753620c54d"

SRC_URI[md5sum] = "a3ef27e5b12c795d48aa6fa4005d2826"
SRC_URI[sha256sum] = "decb67a4660888d48480ab6998309837174158d0f1aa63bebb1c2e11aab97aab"

inherit pypi python_poetry_core

include python3-package-split.inc
