PV = "2023c"

SRC_URI[tzcode.md5sum] = "532bcd571bff983447f0596112bbc065"
SRC_URI[tzcode.sha256sum] = "46d17f2bb19ad73290f03a203006152e0fa0d7b11e5b71467c4a823811b214e7"
SRC_URI[tzdata.md5sum] = "5aa672bf129b44dd915f8232de38e49a"
SRC_URI[tzdata.sha256sum] = "3f510b5d1b4ae9bb38e485aa302a776b317fb3637bdb6404c4adf7b6cadd965c"

do_compile () {
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
