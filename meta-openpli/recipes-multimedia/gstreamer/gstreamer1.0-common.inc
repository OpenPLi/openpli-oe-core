GST_VERSION_FULL = "1.19.2"

inherit gitpkgv meson gettext

PV = "${GST_VERSION_FULL}+git${SRCPV}"
PKGV = "${GST_VERSION_FULL}+git${GITPKGV}"

S = "${WORKDIR}/git"
