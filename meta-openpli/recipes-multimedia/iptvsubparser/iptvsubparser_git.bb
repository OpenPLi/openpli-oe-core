DESCRIPTION = "Python module for text subtitles parsing"
MAINTAINER = "samsamsam"
require conf/license/openpli-gplv2.inc

DEPENDS = "${PYTHON_PN}"

inherit gitpkgv python3native

PV = "1.1+git${SRCPV}"
PKGV = "1.1+git${GITPKGV}"

SRC_URI = "git://github.com/OpenVisionE2/e2isubparser.git;protocol=https"

S = "${WORKDIR}/git"

SOURCE_FILES = "src/subparsermodule.c"
SOURCE_FILES =+ "src/vlc/src/subtitle.c"
SOURCE_FILES =+ "src/ffmpeg/src/htmlsubtitles.c"
SOURCE_FILES =+ "src/expat-2.2.0/xmlparse.c"
SOURCE_FILES =+ "src/expat-2.2.0/xmlrole.c"
SOURCE_FILES =+ "src/expat-2.2.0/xmltok.c"
SOURCE_FILES =+ "src/expat-2.2.0/xmltok_impl.c"
SOURCE_FILES =+ "src/expat-2.2.0/xmltok_ns.c"
SOURCE_FILES =+ "src/ttml/src/ttmlparser.c"
SOURCE_FILES =+ "src/html/src/htmlcleaner.c"

do_compile() {
    ${CC} ${SOURCE_FILES} -shared -pipe -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE -D_LARGEFILE_SOURCE -D_GNU_SOURCE=1 -DNDEBUG -Os -shared -Wall -Wstrict-prototypes -fPIC -DMAJOR_VERSION=0 -DMINOR_VERSION=2 -DHAVE_EXPAT_CONFIG_H -I${S}/src -I${S}/src/vlc/include -I${S}/src/ffmpeg/include -I${S}/src/expat-2.2.0 -I${S}/src/ttml/include -I${S}/src/html/include -I${D}/${libdir} -I${D}/${includedir} -I${STAGING_DIR_TARGET}/${includedir}/${PYTHON_DIR} -lm -l${PYTHON_DIR} -o _subparser.so -Wl,--gc-sections ${LDFLAGS}
}

do_install() {
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/libs/iptvsubparser
    install -m 0777 ${S}/_subparser.so ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/libs/iptvsubparser/
}

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/libs/iptvsubparser"
