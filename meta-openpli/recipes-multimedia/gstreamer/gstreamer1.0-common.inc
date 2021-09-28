GST_VERSION_FULL = "1.19.2"

inherit gitpkgv meson gettext

PV = "${GST_VERSION_FULL}+git${SRCPV}"
PKGV = "${GST_VERSION_FULL}+git${GITPKGV}"

SRCREV_gst = "${AUTOREV}"
SRCREV_gst_libav = "${AUTOREV}"

SRCREV_gst_plugins_bad = "${AUTOREV}"
SRCREV_gst_plugins_base = "${AUTOREV}"
SRCREV_gst_plugins_good = "${AUTOREV}"
SRCREV_gst_plugins_ugly = "${AUTOREV}"

S = "${WORKDIR}/git"
