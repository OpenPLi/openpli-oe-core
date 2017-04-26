DESCRIPTION = "Configuration files for 3rd-party feeds"

require conf/license/openpli-gplv2.inc

# Use the PLi download server, regardless of where we are. Even for "private" feeds,
# the 3rd party plugins originate here.
DISTRO_HOST = "downloads.pli-images.org"
FEEDS = "3rd-party 3rd-party-${MACHINE}"

# allow the complete 3rd party feed to be overridden
DISTRO_THIRD_PARTY_FEED_URI ?= "${DISTRO_FEED_URI}"

do_compile() {
    [ ! -d ${S}/${sysconfdir}/opkg ] && mkdir -p ${S}/${sysconfdir}/opkg
    for feed in ${FEEDS}; do
        echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_THIRD_PARTY_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
}
do_install () {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "${FEEDS}".split() ] ) }'
