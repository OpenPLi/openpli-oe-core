SUMMARY = "OE-Alliance compatibility TrueType fonts for OpenPLi"
DESCRIPTION = "Provides common TrueType fonts (OpenSans, Roboto, DejaVu, Droid) required by OE-Alliance skins and plugins"
LICENSE = "OFL-1.1 & Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://opensans/OFL.txt;md5=925fdc2893725d6f20ee2830a9829569 \
    file://roboto/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
"

inherit allarch fontcache

RDEPENDS:${PN} = " \
    ttf-dejavu-sans \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-sans-mono \
    ttf-droid-sans \
    ttf-droid-sans-fallback \
    ttf-droid-sans-mono \
"

SRCREV_opensans = "6cbc400d4855abee59d85c4ed871d510399bce7b"
SRCREV_roboto = "38062f4b4a0be4346d07a928408da21602545e9e"
SRCREV_FORMAT = "opensans_roboto"

SRC_URI = " \
    git://://github.com \
    git://://github.com \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts
    install -m 0644 ${S}/opensans/fonts/ttf/*.ttf ${D}${datadir}/fonts/
    install -m 0644 ${S}/roboto/src/hinted/*.ttf ${D}${datadir}/fonts/

    # Symlink meta-oe truetype fonts into /usr/share/fonts/ so Enigma2 resolves them directly
    for font in DejaVuSans.ttf DejaVuSans-Bold.ttf DejaVuSansCondensed.ttf DejaVuSansCondensed-Bold.ttf DejaVuSansMono.ttf DroidSans.ttf DroidSans-Bold.ttf DroidSansFallback.ttf DroidSansMono.ttf; do
        ln -sf truetype/$font ${D}${datadir}/fonts/$font
    done
}

FILES:${PN} = "${datadir}/fonts/*"
