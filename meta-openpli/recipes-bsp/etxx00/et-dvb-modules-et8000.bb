KV = "3.13.6"
SRCDATE = "20140321"

require et-dvb-modules.inc

SRC_URI = "http://xtrendet.com/${MACHINE}-drivers-${KV}-${SRCDATE}.zip"

SRC_URI[md5sum] = "3ec127dea416667fdfe7d9ee58ad0ad4"
SRC_URI[sha256sum] = "fa6619bdc6babe3018bb5ac72bb6ebe464bf0556845c1f601d08287158d52b04"

COMPATIBLE_MACHINE = "et8000"
