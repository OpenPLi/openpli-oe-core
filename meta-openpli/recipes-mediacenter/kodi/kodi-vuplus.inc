GLPR ?= ""
GLPR_vuduo2 = "20160331_r0"
GLPR_vusolo2 = "20160331_r0"
GLPR_vusolose = "20160331_r0"

SRC_URI_append = " http://archive.vuplus.com/download/build_support/kodi/xbmc-support_${MACHINE}_${GLPR}.tar.gz;name=xbmc-${MACHINE}"

SRC_URI[xbmc-vuduo2.md5sum] = "f3db678550f3654fcc8dfbb875678943"
SRC_URI[xbmc-vuduo2.sha256sum] = "758e75966c1ca513bbeb7eaef0d0359207232ba0e7f4f5e2574c146f5e09cab3"
SRC_URI[xbmc-vusolo2.md5sum] = "e29a91b185133ec60a59e94a8229d2b4"
SRC_URI[xbmc-vusolo2.sha256sum] = "3c56b7ee890b3e21f378acd79db3752d721de0880b6d763bbd37fa942c2ae2b5"
SRC_URI[xbmc-vusolose.md5sum] = "831014212eed47e36ec084f2e803e2d8"
SRC_URI[xbmc-vusolose.sha256sum] = "97bfc26a316bcba4b897f81f31179e8861cc123a0b4d8589a2290f3cd7268c1d"
