require vuplus-bluetooth-util.inc

COMPATIBLE_MACHINE = "^(vuduo4kse)$"

SRCDATE = "20261009"
SRCDATE_PR = "r0"

CYW4373_FIRMWARE_PATH="cypress"
CYW4373_FIRMWARE_FILE="BCM4373A0-04b4-640c.hcd"

do_install:append() {
    if [ -n "${CYW4373_FIRMWARE_FILE}" ]; then
        install -d ${D}/lib/firmware/${CYW4373_FIRMWARE_PATH}
        install -m 0644 ${S}/${CYW4373_FIRMWARE_FILE} ${D}/lib/firmware/${CYW4373_FIRMWARE_PATH}
    fi
}

SRC_URI[md5sum] = "e33946f83ec259f4b0ef17c0582a705e"
SRC_URI[sha256sum] = "b2a197f4c1682d5ada0ca54d68bdbd279abac29c6da83aee39c84059e356bfdd"
