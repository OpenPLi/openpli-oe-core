FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "0.23.15"

SRCREV = "b8bfc986181a6824b7ee9c4f2c475369ca8ac931"

SRC_URI += " \
        file://mpd.conf \
        file://mpd.init \
        "

do_install:append() {
    install -d ${D}${localstatedir}/lib/mpd/playlists
    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/mpd.init ${D}${sysconfdir}/init.d/mpd
    install -m 644 ${WORKDIR}/mpd.conf ${D}${sysconfdir}/mpd.conf
}
