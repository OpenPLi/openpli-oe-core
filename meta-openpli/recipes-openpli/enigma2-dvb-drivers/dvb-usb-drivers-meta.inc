require conf/license/openpli-gplv2.inc

inherit allarch

PACKAGES = "${PN}"
ALLOW_EMPTY_${PN} = "1"

# We only need the packaging tasks - disable the rest
do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
