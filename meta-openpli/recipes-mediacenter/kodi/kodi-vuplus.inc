SRC_URI_append = " file://EGLNativeTypeV3D-vuplus.patch file://Makefile-vuplus.patch"
EXTRA_OECONF += "--with-gpu=v3dvuplus"

GLPR ?= ""
GLPR_vuduo2 = "20160331_r0"
GLPR_vusolo2 = "20160331_r0"
GLPR_vusolo4k = "20160331_r0"
GLPR_vusolose = "20160331_r0"
GLPR_vuultimo4k = "20161206_r0"
GLPR_vuuno4k = "20161206_r1"

SRC_URI_append = " http://archive.vuplus.com/download/build_support/kodi/xbmc-support_${MACHINE}_${GLPR}.tar.gz;name=xbmc-${MACHINE}"

SRC_URI[xbmc-vuduo2.md5sum] = "f3db678550f3654fcc8dfbb875678943"
SRC_URI[xbmc-vuduo2.sha256sum] = "758e75966c1ca513bbeb7eaef0d0359207232ba0e7f4f5e2574c146f5e09cab3"
SRC_URI[xbmc-vusolo2.md5sum] = "e29a91b185133ec60a59e94a8229d2b4"
SRC_URI[xbmc-vusolo2.sha256sum] = "3c56b7ee890b3e21f378acd79db3752d721de0880b6d763bbd37fa942c2ae2b5"
SRC_URI[xbmc-vusolo4k.md5sum] = "1ae0c982f8db9625a7f831874f6f0605"
SRC_URI[xbmc-vusolo4k.sha256sum] = "1be88ba6d4cbc0a0b1f10c83995132aa9bd5a088cf5b7080e1b0ef90170d96f7"
SRC_URI[xbmc-vusolose.md5sum] = "831014212eed47e36ec084f2e803e2d8"
SRC_URI[xbmc-vusolose.sha256sum] = "97bfc26a316bcba4b897f81f31179e8861cc123a0b4d8589a2290f3cd7268c1d"
SRC_URI[xbmc-vuultimo4k.md5sum] = "765b8c18ca1e452cbb4b387a01cdefa8"
SRC_URI[xbmc-vuultimo4k.sha256sum] = "e4e469dd6cd21fa9d5fd59a662aa526169ace6ec59854ad0ee416baa66af45ae"
SRC_URI[xbmc-vuuno4k.md5sum] = "b0f62cb425b0f5bc5dc6ebcbe76f0890"
SRC_URI[xbmc-vuuno4k.sha256sum] = "b2b91ec85cc7edfef86218906a8743868e372fb5f2736fa1ce066335a23c6bb1"

do_configure_prepend() {
    cp -av ${WORKDIR}/xbmc-support/gles_init.* ${S}/xbmc/windowing/egl/
}

do_install_append(){
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/xbmc-support/xbmc.helper ${D}${bindir}
    install -m 0755 ${WORKDIR}/xbmc-support/libxbmc_base.so ${D}${libdir}
}

FILES_SOLIBSDEV = ""
FILES_${PN}_append = " ${bindir}/xbmc.helper ${libdir}/libxbmc_base.so"
INSANE_SKIP_${PN}_append = " already-stripped"
PRIVATE_LIBS_${PN}_append = " libxbmc_base.so"
