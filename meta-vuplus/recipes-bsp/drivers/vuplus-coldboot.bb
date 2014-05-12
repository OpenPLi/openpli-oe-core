DESCIPTION = "S3 cold boot"
MAINTAINER = "vuplus team"
LICENSE = "CLOSED"

SRCDATE="20130726_p0"

PV = "1.0"
PR = "${SRCDATE}_r0"
SRC_REV = ""

SRC_URI = "http://archive.vuplus.com/openpli-support/vuplus-coldboot_${SRCDATE}.tar.gz"

do_install() {
	install -d ${D}/etc/init.d \
		${D}/usr/bin \
        	${D}${sysconfdir}/rc0.d 

	install -m 0755 ${WORKDIR}/${PN}/coldboot.sh ${D}/etc/init.d/coldboot.sh
	install -m 0755 ${WORKDIR}/${PN}/ethwol.sh ${D}/etc/init.d/ethwol.sh
	install -m 0755 ${WORKDIR}/${PN}/coldboot ${D}/usr/bin/coldboot
        ln -sf   ../init.d/coldboot.sh ${D}${sysconfdir}/rc0.d/S30coldboot.sh
	ln -sf   ../init.d/ethwol.sh ${D}${sysconfdir}/rc0.d/K32ethwol.sh
}

SRC_URI[md5sum] = "16b1a001d5aad67aa786cc580103e053"
SRC_URI[sha256sum] = "d475c7d266dcf3aa62b740485683fe98d18f19cca3ba844b8d96341c1ced71d0"
