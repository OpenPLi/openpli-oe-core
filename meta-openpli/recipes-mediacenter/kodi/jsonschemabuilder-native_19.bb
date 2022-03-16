SUMMARY = "Kodi json schema builder"

require kodi_19.inc
inherit autotools-brokensep gettext native

KODIWORKDIR = "${WORKDIR}/git/tools/depends/native/JsonSchemaBuilder/src"

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

BBCLASSEXTEND = "native"
