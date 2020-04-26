# compatibility need to remove the inherit and use directly bluez5
BLUEZ ?= "${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez5', '', d)}"
