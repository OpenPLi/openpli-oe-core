HOMEPAGE="mediainfo.sourceforge.net"
SUMMARY = "Mediainfo is a tool to analyze multimedia files"

LICENSE = "MEDIAINFO"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/meta-openpli/licenses/MEDIAINFO;md5=3b19611f7ea2253f534b20692b22ce50"

inherit autotools

DEPENDS = "zlib"

SRC_URI="http://mediaarea.net/download/binary/mediainfo/${PV}/MediaInfo_CLI_${PV}_GNU_FromSource.tar.bz2"

SRC_URI[md5sum] = "6b699ee634d77f40cbc905c667be9d7b"
SRC_URI[sha256sum] = "aaa70120ce7100f37c41c545d1a26d946e614dc858e6bd5ea91fa2e10b00f696"

S = "${WORKDIR}/MediaInfo_CLI_GNU_FromSource/MediaInfo/Project/GNU/CLI/"

do_configure () {
    #build zenlib
    cd ${WORKDIR}/MediaInfo_CLI_GNU_FromSource/ZenLib/Project/GNU/Library/
    my_runconf
    oe_runmake

    #build media info lib
    cd ${WORKDIR}/MediaInfo_CLI_GNU_FromSource/MediaInfoLib/Project/GNU/Library/
    my_runconf
    oe_runmake

    #build media info
    cd ${S}
    oe_runconf
}

do_compile_prepend () {
    #compile media info
    cd ${S}
}

do_install_prepend () {
    install -d ${D}${libdir}
    oe_libinstall -so -C ${WORKDIR}/MediaInfo_CLI_GNU_FromSource/ZenLib/Project/GNU/Library/ libzen ${D}${libdir}
    oe_libinstall -so -C ${WORKDIR}/MediaInfo_CLI_GNU_FromSource/MediaInfoLib/Project/GNU/Library/ libmediainfo ${D}${libdir}
    #install media info
    cd ${S}
}

my_runconf() {
    cfgscript="./configure"
    if [ -x "$cfgscript" ] ; then
        bbnote "Running $cfgscript --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} --prefix=${prefix} --exec_prefix=${exec_prefix} --bindir=${bindir} --sbindir=${sbindir} --libexecdir=${libexecdir} --datadir={datadir} --sysconfdir=${sysconfdir} --sharedstatedir=${sharedstatedir} --localstatedir=${localstatedir} --libdir=${libdir} --includedir=${includedir} --oldincludedir=${oldincludedir} --infodir=${infodir} --mandir=${mandir} --disable-silent-rules --disable-dependency-tracking --with-libtool-sysroot=/home/tegradev/oe-core/build/out-eglibc/sysroots/colibri-t20 $@"
        set +e
        ${CACHED_CONFIGUREVARS} $cfgscript --build=${BUILD_SYS} --host=${HOST_SYS} --target=${TARGET_SYS} --prefix=${prefix} --exec_prefix=${exec_prefix} --bindir=${bindir} --sbindir=${sbindir} --libexecdir=${libexecdir} --datadir=${datadir} --sysconfdir=${sysconfdir} --sharedstatedir=${sharedstatedir} --localstatedir=${localstatedir} --libdir=${libdir} --includedir=${includedir} --oldincludedir=${oldincludedir} --infodir=${infodir} --mandir=${mandir} --disable-silent-rules --disable-dependency-tracking ${@append_libtool_sysroot(d)} $@
        if [ "$?" != "0" ]; then
            echo "Configure failed. Check the contents of all config.log files"
            bbfatal "oe_runconf failed"
        fi
        set -e
    else
        bbfatal "no configure script found at $cfgscript"
    fi
}

