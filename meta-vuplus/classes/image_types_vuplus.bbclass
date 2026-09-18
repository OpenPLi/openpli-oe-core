inherit image_types

# make rootfs.tar.bz2 compatible with older progs like WinRAR 3.51
IMAGE_CMD:tar = "tar --sort=name --format=gnu --numeric-owner -cvf ${IMGDEPLOYDIR}/${IMAGE_NAME}.tar -C ${IMAGE_ROOTFS} . || [ $? -eq 1 ]"
CONVERSION_CMD:bz2 = "bzip2 -f -k ${IMAGE_NAME}.${type}"
