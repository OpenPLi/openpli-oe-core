FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-2.33:"

SRC_URI_remove = "file://0001-CVE-2021-38604.patch file://0002-CVE-2021-38604.patch"

SRC_URI += " file://0001-ptrace-protect-ptrace_peeksiginfo_args-from-redefint.patch \
             file://0004-sunrpc-use-snprintf-instead-of-an-implied-length-gua.patch \
"

SRC_URI_append_arm = " file://stdlib-canonicalize-realpath_stk-dest-maybe-uninit.patch \
                       file://add-support-for-SHT_RELR-sections.patch \
                       file://tls-libwidevinecdm.so-since-4.10.2252.0-has-TLS-with.patch \
"
