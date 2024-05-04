FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:${PN}-core += "${PYTHON_PN}-service-identity"

SRC_URI += " \
           file://0001-fix-writing-after-channel-is-closed.patch \
           file://0002-Revert-Remove-twisted.web.client.getPage-and-friends.patch \
"

include ${PYTHON_PN}-package-split.inc
