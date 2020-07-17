PACKAGE_DEBUG_SPLIT_STYLE := "debug-with-srcpkg"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
            file://04-default-is-optimized.patch \
            file://99-ignore-optimization-flag.patch \
            file://no-ldconfig.patch \
            file://setuptweaks-2.patch \
            file://pgettext.patch \
"

EXTRA_OECONF += " \
    ac_cv_file__dev_ptmx=yes \
    ac_cv_file__dev_ptc=no \
    ac_cv_no_strict_aliasing_ok=yes \
    ac_cv_pthread=yes \
    ac_cv_cxx_thread=yes \
    ac_cv_sizeof_off_t=8 \
"

