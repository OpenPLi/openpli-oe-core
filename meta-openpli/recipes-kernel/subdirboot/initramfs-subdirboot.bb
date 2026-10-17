SUMMARY = "Minimal initramfs for booting root filesystems in subdirectories"
LICENSE = "MIT"

inherit image

PACKAGE_INSTALL = "subdirboot"
PACKAGE_INSTALL_ATTEMPTONLY = ""
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""
IMAGE_CLASSES = ""
IMAGE_NAME_SUFFIX = ""
IMAGE_NAME = "${IMAGE_BASENAME}-${MACHINE}"
IMAGE_LINK_NAME = ""
INITRAMFS_FSTYPES = "cpio.gz"

# The kernel needs this image before configuration; avoid a dependency cycle.
EXTRA_IMAGEDEPENDS = ""
KERNELDEPMODDEPEND = ""
ROOTFS_BOOTSTRAP_INSTALL = ""
IMAGE_ROOTFS_SIZE = "0"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
