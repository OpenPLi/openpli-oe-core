SUMMARY = "Cobalt 25 LTS Evergreen core"
DESCRIPTION = "Builds the pinned ARM hard-float Starboard 16 core, using the matching upstream release content."
PR = "r0"
HOMEPAGE = "https://github.com/youtube/cobalt"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d"
require cobalt-source.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "${COBALT_COMMON_SRC_URI} \
"

S = "${WORKDIR}/source"

inherit python3native

DEPENDS = "ninja-native ccache-native bison-native nodejs-native python3-six-native python3-jinja2-native python3-ply-native"

cobalt_core_env() {
    export PYTHONPATH="${S}"
    export PATH="${WORKDIR}/gn-bin:${WORKDIR}/clang17/bin:$PATH"
    export CCACHE_DIR="${TMPDIR}/cobalt-core-ccache"
    export CCACHE_TEMPDIR="${TMPDIR}/cobalt-core-ccache/tmp"
    export CCACHE_BASEDIR="${S}"
    export COBALT_SOURCE_REVISION="32b44a9be760b859b3228d07d5e93da983976fd3"
    export COBALT_SOURCE_BUILD_ID="1000001"
    install -d "$CCACHE_TEMPDIR"
}

do_configure() {
    install -d ${WORKDIR}/gn-bin ${S}/out/evergreen-arm-hardfp_qa
    install -m 0755 ${WORKDIR}/gn ${WORKDIR}/gn-bin/gn
    ln -sf ${PYTHON} ${WORKDIR}/gn-bin/python
    printf '%s\n' 'target_platform = "evergreen-arm-hardfp"' \
        'target_cpu = "arm"' 'target_os = "linux"' 'sb_api_version = 16' \
        'build_type = "qa"' 'clang_base_path = "${WORKDIR}/clang17"' \
        > ${S}/out/evergreen-arm-hardfp_qa/args.gn
    cobalt_core_env
    cd ${S}
    gn gen out/evergreen-arm-hardfp_qa
}

do_compile() {
    cobalt_core_env
    cd ${S}
    ninja -C out/evergreen-arm-hardfp_qa -j 12 cobalt
}

# The Evergreen core is loaded by Starboard rather than the system linker.
INHIBIT_PACKAGE_STRIP = "1"

python do_install() {
    import os
    import shutil
    import zipfile

    archive = os.path.join(d.getVar('WORKDIR'),
        'cobalt_evergreen_5.40.2_arm-hardfp_sbversion-16_qa_20260323230717.crx')
    root = os.path.join(d.getVar('D'), d.getVar('datadir').lstrip('/'),
                        'cobalt', 'evergreen')
    library = os.path.join(d.getVar('D'), d.getVar('libdir').lstrip('/'),
                           'cobalt', 'libcobalt.so')
    os.makedirs(root, exist_ok=True)
    with zipfile.ZipFile(archive) as package:
        for info in package.infolist():
            if info.is_dir():
                continue
            if not (info.filename.startswith('content/') or
                    info.filename == 'manifest.json'):
                continue
            destination = os.path.join(root, info.filename)
            os.makedirs(os.path.dirname(destination), exist_ok=True)
            with package.open(info) as source, open(destination, 'wb') as target:
                shutil.copyfileobj(source, target)
            os.chmod(destination, 0o755 if info.filename.endswith('.so') else 0o644)
    os.makedirs(os.path.dirname(library), exist_ok=True)
    shutil.copyfile(os.path.join(d.getVar('S'), 'out',
                                'evergreen-arm-hardfp_qa', 'libcobalt.so'), library)
    os.chmod(library, 0o755)
}

FILES_${PN} = "${datadir}/cobalt ${libdir}/cobalt"
INSANE_SKIP_${PN} += "already-stripped"
