# make the package version depend on the name/version of the distro
# this is required for release-to-release upgrades.

do_install_basefilesissue[vardeps] += "DISTRO_NAME DISTRO_VERSION"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://utf8.sh"
SRC_URI += "file://filesystems"

do_install_append() {
	rm -rf ${D}/mnt
	rm -rf ${D}/hdd
	ln -sf media/hdd ${D}/hdd
	ln -sf media ${D}/mnt
	rm -rf ${D}/media/*
	rm -fr ${D}/tmp

	install -d ${D}${sysconfdir}/profile.d
	install -m 0644 ${WORKDIR}/utf8.sh ${D}${sysconfdir}/profile.d/utf8.sh
	install -m 0644 ${WORKDIR}/filesystems ${D}${sysconfdir}/filesystems
}
