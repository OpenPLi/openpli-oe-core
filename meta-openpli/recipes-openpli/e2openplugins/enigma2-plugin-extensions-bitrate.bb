MODULE = "Bitrate"
DESCRIPTION = "Bitrate viewer"

require conf/license/license-gplv2.inc

require openplugins-replace-pli.inc
PR="r1"

require openplugins.inc

inherit autotools gettext pythonnative

DEPENDS += "${PYTHON_VER}"

SRC_URI = " git://github.com/E2OpenPlugins/e2openplugin-Bitrate.git;branch=python3;protocol=https"

EXTRA_OECONF = " \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR}"

FILES_${PN} = "${libdir} ${bindir}"
