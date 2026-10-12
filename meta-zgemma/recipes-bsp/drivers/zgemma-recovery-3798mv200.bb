SRCDATE = "20201123"
SRCDATE:nand = "20261009"

require zgemma-recovery.inc

SRC_URI:nand = "http://downloads.openpli.org/archive/zgemma/zgemma-recovery-${HICHIPSET}-nand-${SRCDATE}.zip"

SRC_URI[md5sum] = "${LIBS_MD5}"
SRC_URI[sha256sum] = "${LIBS_SHA256}"

LIBS_MD5 = "68444188fd91d88012e753ed537c4120"
LIBS_SHA256 = "5d22fb0d008d6fa106dc51685c5f202beea7d01205681f5753494af19c8c8762"

LIBS_MD5:nand = "9c19efd8dc2debe9c4c305fb8c350d6c"
LIBS_SHA256:nand = "cd3136cac7a2f68592cf1f73b05dba09951fa7949a68141d6905fe31a73711f9"

COMPATIBLE_MACHINE = "^h8se$|^h9$|^i55plus$|^h9se$|^h9combo$|^h9combose$|^h10$|^i55se$"
