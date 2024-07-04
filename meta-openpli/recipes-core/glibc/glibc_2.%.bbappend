FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://0001-ptrace-protect-ptrace_peeksiginfo_args-from-redefint.patch"
SRC_URI:append:arm = " file://tls-libwidevinecdm.so-since-4.10.2252.0-has-TLS-with.patch"

OLDEST_KERNEL = "3.2.0"
