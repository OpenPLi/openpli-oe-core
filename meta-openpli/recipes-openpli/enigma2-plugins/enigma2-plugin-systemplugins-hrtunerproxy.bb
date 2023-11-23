SUMMARY = "Setup Enigma2 to act as HR-Tuner Proxy"
DESCRIPTION = "Setup Enigma2 to act as HR-Tuner Proxy"
MAINTAINER = "OpenViX"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

inherit gitpkgv setuptools3-openplugins gettext python3-compileall

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"

SRC_URI = "git://github.com/OpenViX/HRTunerProxy.git;protocol=https;branch=master \
           file://revert-back-to-distutils.patch \
           "

S = "${WORKDIR}/git"

RCONFLICTS:${PN} = "enigma2-plugin-systemplugins-plexdvrapi"
RREPLACES:${PN} = "enigma2-plugin-systemplugins-plexdvrapi"

#do_install:prepend() {
#    echo ${GITPKGVTAG} | awk -F"-" '{print $1}'> ${S}/build/lib/SystemPlugins/HRTunerProxy/PLUGIN_VERSION
#}

python populate_packages:prepend() {
    e2_pdir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, e2_pdir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}
