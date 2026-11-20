SUMMARY = "OE-Alliance compatibility TrueType fonts for OpenPLi"
DESCRIPTION = "Provides common TrueType fonts (OpenSans, Roboto, DejaVu, Droid) required by OE-Alliance skins and plugins"
LICENSE = "OFL-1.1 & Apache-2.0 & Bitstream-Vera"
LIC_FILES_CHKSUM = " \
    file://opensans/OFL.txt;md5=925fdc2893725d6f20ee2830a9829569 \
    file://roboto/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
    file://dejavu/LICENSE;md5=449b2c30bfe5fa897fe87b8b70b16cfa \
"

inherit allarch fontcache

SRCREV_opensans = "6cbc400d4855abee59d85c4ed871d510399bce7b"
SRCREV_roboto = "38062f4b4a0be4346d07a928408da21602545e9e"
SRCREV_dejavu = "9b5d1b2ffeec20c7b46aa89c0223d783c02762cf"
SRCREV_FORMAT = "opensans_roboto_dejavu"

SRC_URI = " \
    git://github.com/googlefonts/opensans.git;protocol=https;branch=main;name=opensans;destsuffix=opensans \
    git://github.com/googlefonts/roboto-2.git;protocol=https;branch=main;name=roboto;destsuffix=roboto \
    git://github.com/dejavu-fonts/dejavu-fonts.git;protocol=https;branch=master;name=dejavu;destsuffix=dejavu \
    https://raw.githubusercontent.com/aosp-mirror/platform_frameworks_base/donut-release/data/fonts/DroidSans.ttf;name=droid-reg \
    https://raw.githubusercontent.com/aosp-mirror/platform_frameworks_base/donut-release/data/fonts/DroidSans-Bold.ttf;name=droid-bold \
    https://raw.githubusercontent.com/aosp-mirror/platform_frameworks_base/donut-release/data/fonts/DroidSansFallback.ttf;name=droid-fall \
    https://raw.githubusercontent.com/aosp-mirror/platform_frameworks_base/donut-release/data/fonts/DroidSansMono.ttf;name=droid-mono \
"

SRC_URI[droid-reg.sha256sum] = "4e2371bc0e4cf6983342e150412f140da79d674c9be0b56458401f581072ecd3"
SRC_URI[droid-bold.sha256sum] = "b631b677af5aa7316297a8b56a1fe3bb1da706737f8c9785d5a5fc94faae1ea9"
SRC_URI[droid-fall.sha256sum] = "82a74d0ef369ec7979172023cfff87f04f0a617a2871c39ef6aeb0f399c5cdaf"
SRC_URI[droid-mono.sha256sum] = "089bdaac95caeed25a8392a6f0606328d009473119f1c7465b642d5cebe5320c"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${datadir}/fonts
    install -m 0644 ${S}/opensans/fonts/ttf/*.ttf ${D}${datadir}/fonts/
    install -m 0644 ${S}/roboto/src/hinted/*.ttf ${D}${datadir}/fonts/
    for font in DejaVuSans.ttf DejaVuSans-Bold.ttf DejaVuSansCondensed.ttf DejaVuSansCondensed-Bold.ttf DejaVuSansMono.ttf; do
        install -m 0644 ${S}/dejavu/ttf/$font ${D}${datadir}/fonts/
    done
    install -m 0644 ${S}/DroidSans*.ttf ${D}${datadir}/fonts/
}

FILES:${PN} = "${datadir}/fonts/*"
