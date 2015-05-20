# Get rid of udev dependency
PACKAGECONFIG = ""
# Broken alsa recipe has udev hardcoded
DEPENDS := "${@oe_filter_out('udev', '${DEPENDS}', d)}"
