do_compile () {
        oe_runmake -C ${S} tzdata.zi
        for zone in ${TZONES}; do \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}/build/zoneinfo -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
        done
}

TZONES:remove = "systemv"

TZ_PACKAGES:remove = "tzdata-posix tzdata-right"

RPROVIDES:tzdata-posix:remove = "tzdata-posix"
RPROVIDES:tzdata-right:remove = "tzdata-right"

DEFAULT_TIMEZONE = "Europe/Amsterdam"

PACKAGES += "${PN}-base"

FILES:${PN}-base = "${datadir}/zoneinfo"
