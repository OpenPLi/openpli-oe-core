
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://utf8.sh"

do_install_append() {
	rm -rf ${D}/mnt
	rm -rf ${D}/hdd
	ln -sf media/hdd ${D}/hdd
	ln -sf media ${D}/mnt
	rm -rf ${D}/media/*
	rm -fr ${D}/tmp

	install -d ${D}${sysconfdir}/profile.d
	install -m 0644 ${WORKDIR}/utf8.sh ${D}${sysconfdir}/profile.d/utf8.sh
}
