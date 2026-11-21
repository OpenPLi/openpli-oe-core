# Shared upstream build inputs for the Cobalt core and receiver loader.
COBALT_SRCREV = "32b44a9be760b859b3228d07d5e93da983976fd3"
SRCREV = "${COBALT_SRCREV}"
COBALT_COMMON_SRC_URI = " \
    git://github.com/youtube/cobalt.git;protocol=https;nobranch=1;destsuffix=source \
    https://github.com/youtube/cobalt/releases/download/25.lts.40/cobalt_evergreen_5.40.2_arm-hardfp_sbversion-16_qa_20260323230717.crx;name=cobaltcrx;downloadfilename=cobalt_evergreen_5.40.2_arm-hardfp_sbversion-16_qa_20260323230717.crx \
    https://commondatastorage.googleapis.com/chromium-browser-clang/Linux_x64/clang-llvmorg-17-init-8029-g27f27d15-3.tgz;name=clang;downloadfilename=clang17.tgz;subdir=clang17 \
    https://chrome-infra-packages.appspot.com/dl/gn/gn/linux-amd64/+/git_revision%3A0725d7827575b239594fbc8fd5192873a1d62f44;name=gn;downloadfilename=gn.zip \
"

SRC_URI[cobaltcrx.sha256sum] = "6fa724c579860d84854694d052b51a0dd8418eae041ea723794d0c2d86912f4d"
SRC_URI[clang.sha256sum] = "1ac590c011158940037ce9442d4bf12943dc14a7ddaab6094e75a8750b47b861"
SRC_URI[gn.sha256sum] = "a67d1c6be35e4ad1b3f91343922ceb6b5b010578a74b4a8ae638ffd4a10f8e8b"
