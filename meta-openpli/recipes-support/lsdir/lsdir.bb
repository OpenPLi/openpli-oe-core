DESCRIPTION = "Allows to list directory"
MAINTAINER = "samsamsam"

require conf/license/openpli-gplv2.inc

SRC_URI = "file://lsdir.tar.gz"

# same version as the last git commit before the repo disappeared
PV = "1.0+git4+4be3f6b"

S = "${WORKDIR}/lsdir"

SOURCE_FILES = "src/lsdir.c"

do_compile() {
    ${CC} ${SOURCE_FILES} -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE=1 -D_LARGEFILE_SOURCE -I${S}/src -I${D}/${libdir} -I${D}/${includedir} -o lsdir ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/lsdir ${D}${bindir}
}
