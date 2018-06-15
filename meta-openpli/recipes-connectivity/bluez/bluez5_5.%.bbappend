EXTRA_OECONF += " --disable-udev"
DEPENDS := "${@oe.utils.str_filter_out('udev', '${DEPENDS}', d)}"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-tools-Add-support-for-rtk_h5-type.patch"
