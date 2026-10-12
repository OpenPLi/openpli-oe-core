DESCRIPTION = "Vu+ bluetooth plugin"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "^(vuuno4k|vuuno4kse|vusolo4k|vuduo4k|vuduo4kse|vuultimo4k|vuzero4k|vuduo4klite)$"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCDATE = "20221118"
SRCDATE_PR = "r2"

PR = "${SRCDATE}-py3.${SRCDATE_PR}"

SRC_URI[md5sum] = "f621e95b938fd33402a94707ae4fe1c3"
SRC_URI[sha256sum] = "74e72062e4ce4c141c465d1c895ec926a7342e84ac8480cb41cf80cebf3cbee6"

DEPENDS = "python3-native"
RDEPENDS:${PN} = "vuplus-bluetooth-util libusb-compat"

inherit python3native

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    file://bluetoothsetup.tar.gz \
    file://${MACHINE}/_vubt.so \
"

S = "${WORKDIR}/plugin"

BLUETOOTH_PLUGIN_PATH = "/usr/lib/enigma2/python/Plugins/SystemPlugins/BluetoothSetup"

FILES:${PN} = "${BLUETOOTH_PLUGIN_PATH}"
FILES:${PN}-src = "${BLUETOOTH_PLUGIN_PATH}/*.py"

do_compile() {
	python3 -O -m compileall ${S}
}

do_install() {
	install -d  ${D}${BLUETOOTH_PLUGIN_PATH}
	cp -r --preserve=mode,links ${S}/* ${D}${BLUETOOTH_PLUGIN_PATH}
	cp ${WORKDIR}/${MACHINE}/_vubt.so ${D}${BLUETOOTH_PLUGIN_PATH}
}

INSANE_SKIP:${PN} = "already-stripped file-rdeps"

