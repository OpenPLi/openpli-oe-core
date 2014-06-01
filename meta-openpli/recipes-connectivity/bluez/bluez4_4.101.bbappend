PR = "r6"

EXTRA_OECONF += " --disable-udevrules"

DEPENDS := "${@oe_filter_out('udev', '${DEPENDS}', d)}"
