PV = "2020c"

SRC_URI[tzcode.md5sum] = "f6631485211c8a2ac1d6a8a3f59ba974"
SRC_URI[tzcode.sha256sum] = "9aa97f40f7b5d90c76c1af80295194daef9c427302f50c278d10ca31c3ccbfe4"
SRC_URI[tzdata.md5sum] = "43309518079463377086aa67e87389e6"
SRC_URI[tzdata.sha256sum] = "7890ac105f1aa4a5d15c5be2409580af401ee2f3fffe2a1e4748af589e194bd9"

do_compile () {
        for zone in ${TZONES}; do \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
        done
}

TZONES_remove = "systemv"

TZ_PACKAGES_remove = "tzdata-posix tzdata-right"

RPROVIDES_tzdata-posix_remove = "tzdata-posix"
RPROVIDES_tzdata-right_remove = "tzdata-right"

DEFAULT_TIMEZONE = "Europe/Amsterdam"

