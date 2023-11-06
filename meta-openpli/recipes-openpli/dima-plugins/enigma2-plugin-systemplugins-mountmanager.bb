DESCRIPTION = "Mount devices at your decision (label,uuid)"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=0e36b30b1a9303e9763901f55c05e558"

PLUGINNAME = "enigma2-plugin-mountmanager"
PLUGIN_PATH = "SystemPlugins/MountManager"

require dima-plugins.inc

RDEPENDS:${PN} = "e2fsprogs-tune2fs"
