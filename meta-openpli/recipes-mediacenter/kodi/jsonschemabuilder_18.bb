SUMMARY = "Kodi json schema builder"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.md;md5=7b423f1c9388eae123332e372451a4f7"

SRCREV = "0655c2c71821567e4c21c1c5a508a39ab72f0ef1"

PV = "18.9+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=Leia"

inherit autotools-brokensep gettext native

S = "${WORKDIR}/git/tools/depends/native/JsonSchemaBuilder/src"

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

BBCLASSEXTEND = "native"
