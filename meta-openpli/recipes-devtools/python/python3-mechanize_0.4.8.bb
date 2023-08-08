SUMMARY = "Stateful programmatic web browsing, after Andy Lester's Perl module WWW::Mechanize."
HOMEPAGE = "https://github.com/python-mechanize/mechanize"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=621053d4e9daec9454e15e60fe40214f"

RDEPENDS_${PN} = "${PYTHON_PN}-core"

SRC_URI = "https://files.pythonhosted.org/packages/b0/02/6c3d393c72db98e8732ec85020a525494fdbb076c7511e3d331188a48154/mechanize-${PV}.tar.gz"

SRC_URI[md5sum] = "3e71ca8de26f4cec3406250a3de2708e"
SRC_URI[sha256sum] = "5e86ac0777357e006eb04cd28f7ed9f811d48dffa603d3891ac6d2b92280dc91"

S = "${WORKDIR}/mechanize-${PV}"

inherit setuptools3

include ${PYTHON_PN}-package-split.inc
