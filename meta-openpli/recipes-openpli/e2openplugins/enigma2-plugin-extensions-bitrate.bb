MODULE = "Bitrate"
DESCRIPTION = "Bitrate viewer"

require conf/license/license-gplv2.inc

require openplugins-replace-pli.inc
PR="r1"

require openplugins.inc

inherit autotools gettext ${PYTHON_PN}native

DEPENDS += "${PYTHON_PN}"

EXTRA_OECONF = " \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR}"

FILES_${PN} = "${libdir} ${bindir}"
