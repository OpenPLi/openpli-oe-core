SUMMARY = "Standalone IPTV browser and player for Enigma2"
DESCRIPTION = "GT IPTV Player Pro provides Xtream Codes, M3U and Stalker/MAC portal browsing and playback."
HOMEPAGE = "https://github.com/VicTuS59/GT-IPTV-Player-Pro"
MAINTAINER = "VicTuS59"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"
PRIORITY = "optional"

inherit allarch python3-compileall

SRC_URI = "git://github.com/VicTuS59/GT-IPTV-Player-Pro.git;protocol=https;branch=main"
SRCREV = "8ce3454e293247263879f34b22df3144a8a0ecb3"

PV = "1.0.0"
PR = "r0"

S = "${WORKDIR}/git"

RDEPENDS:${PN} = "\
    python3-core \
    python3-crypt \
    python3-datetime \
    python3-difflib \
    python3-html \
    python3-io \
    python3-json \
    python3-netclient \
    python3-stringold \
    python3-threading \
"

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/GTIPTVPlayerPro"

FILES:${PN} = "${PLUGINPATH}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${PLUGINPATH}
    cp -r --no-preserve=ownership ${S}/GTIPTVPlayerPro/. ${D}${PLUGINPATH}/
    chmod -R a+rX ${D}${PLUGINPATH}
}

pkg_postrm:${PN}() {
#!/bin/sh
plugin_dir="${PLUGINPATH}"
if [ ! -L "$plugin_dir" ] && [ -d "$plugin_dir" ]; then
    rm -rf "$plugin_dir"
fi
exit 0
}
