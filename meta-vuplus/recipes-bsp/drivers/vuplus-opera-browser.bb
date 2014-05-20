DESCRIPTION = "Opera HbbTV browser"
PRIORITY = "optional"
LICENSE = "CLOSED"
SECTION = "base"

DEPENDS = "tslib mpfr gmp"
RREPLACES_{$PN} = "vuplus-opera-browser-util"
RCONFLICTS_{$PN} = "vuplus-opera-browser-util"
PACKAGES = "${PN}"

SRC_DATE = "20140519_1"
PR = "r9_${SRC_DATE}"

SRC_URI = ""
SRC_FILE = "opera-hbbtv_${SRC_DATE}.tar.gz"
SRC_URI[md5sum] = "e2f6220403c2a946f8f9583aa084bc60"
SRC_URI[sha256sum] = "f8ec235ca0368bdcaaac2b61634204e2c41558d23d8d7d43ff4f02edc00d6ddb"

S = "${WORKDIR}/opera-hbbtv"

do_fetch() {
       if [ ! -e ${DL_DIR}/${SRC_FILE} -a -e /etc/vuplus_browser.pwd ]; then
sshpass -f /etc/vuplus_browser.pwd sftp -o StrictHostKeyChecking=no guestuser@code.vuplus.com << +
get ${SRC_FILE}
bye
+
       fi
       cp -av ${DL_DIR}/${SRC_FILE} ${WORKDIR}/
}

do_unpack() {
       tar xvfz ${SRC_FILE}
}

do_install() {
	install -d ${D}/usr/local/hbb-browser
	cp -avR ${S}/opera/* ${D}/usr/local/hbb-browser/
# workaround for slightly broken startup script
	sed -i -e '1,2d' ${D}/usr/local/hbb-browser/launcher

	install -d ${D}/etc
	cp -avR ${S}/dfb/etc/* ${D}/etc/

	install -d ${D}/usr/bin
	cp -avR ${S}/dfb/usr/bin/* ${D}/usr/bin/

	install -d ${D}/usr/lib
	cp -avR ${S}/dfb/usr/lib/* ${D}/usr/lib/

	install -d ${D}/usr/share
	cp -avR ${S}/dfb/usr/share/* ${D}/usr/share/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
	cp -avR ${S}/plugin/* ${D}/usr/lib/enigma2/python/Plugins/Extensions/HbbTV
}

do_package_qa() {
}

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} = "/usr/lib /usr/local /usr/share /usr/bin /etc "
