FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

SRC_URI += " \
           file://Fix_system_error_return_value.patch \
           file://Fix_ldconfig_truncate_after_patchelf.patch \
           ${@bb.utils.contains("TARGET_ARCH", "arm", "file://glibc-add-support-for-SHT_RELR-sections.patch", "", d)} \
           ${@bb.utils.contains("TARGET_ARCH", "arm", "file://glibc-tls-libwidevinecdm.so-since-4.10.2252.0-has-TLS-with.patch", "", d)} \
"
