PV = "2021a"

SRC_URI[tzcode.md5sum] = "41a624869eed4cc36b9d163764f972a3"
SRC_URI[tzcode.sha256sum] = "eb46bfa124b5b6bd13d61a609bfde8351bd192894708d33aa06e5c1e255802d0"
SRC_URI[tzdata.md5sum] = "20eae7d1da671c6eac56339c8df85bbd"
SRC_URI[tzdata.sha256sum] = "39e7d2ba08c68cbaefc8de3227aab0dec2521be8042cf56855f7dc3a9fb14e08"

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

