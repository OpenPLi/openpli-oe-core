inherit image_types_vuplus

IMAGEDIR ?= "${MACHINE}"

FACTORY_IMAGE ?= "no"
USB_IMAGE_NAME ?= "${IMAGE_NAME}"

# todo: import from environment
#FACTORY_IMAGE = "yes"
#USB_IMAGE_NAME = "vuplus-FACTORY${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}${IMAGE_VERSION_SUFFIX}"

IMAGE_CMD:tar:prepend = "\
        echo "###### prepare imagedir ##########################"; \
        mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}; \
        cp ${IMAGE_ROOTFS}/boot/splash_auto.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/splash.bin; \
        [ ${FACTORY_IMAGE} = "yes" ] && cp ${IMAGE_ROOTFS}/boot/boot_gb.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/boot.bin; \
        [ ${FACTORY_IMAGE} = "dummy" ] && cp ${IMAGE_ROOTFS}/boot/gpt_vu.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/gpt.bin; \
        cp ${IMAGE_ROOTFS}/boot/initrd_vu.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/initrd.bin; \
        [ ${FACTORY_IMAGE} = "yes" ] && cp ${IMAGE_ROOTFS}/boot/rescue_vu.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/rescue.bin; \
        cp ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/kernel_auto.bin; \
        echo "###### remove unneeded files from rootfs #######################"; \
        rm ${IMAGE_ROOTFS}/boot/*.bin; \
        rm -rf ${IMAGE_ROOTFS}/tmp/*; \
        echo "####### write image #########################"; \
        [ ${FACTORY_IMAGE} != "yes" ] && echo ${IMAGE_DISTRO_NAME}-${IMAGE_DISTRO_VERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/imageversion; \
        [ ${FACTORY_IMAGE} = "yes" ] && echo vu-factory-${IMAGE_DISTRO_VERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/imageversion; \
        [ ${FACTORY_IMAGE} != "yes" ] && touch ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/${MULTIBOOT_STARTUP_DEFAULT}; \
        [ ${FACTORY_IMAGE} = "yes" ] && touch ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/FACTORY; \
        cd ${IMAGE_ROOTFS}; \
        tar -cvf ${DEPLOY_DIR_IMAGE}/rootfs.tar -C ${IMAGE_ROOTFS} .; \
        mv ${DEPLOY_DIR_IMAGE}/rootfs.tar ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/rootfs.tar; \
        bzip2 -f ${DEPLOY_DIR_IMAGE}/${IMAGE_UNPACK_PATH}/rootfs.tar; \
        cd ${DEPLOY_DIR_IMAGE}; \
        zip ${IMAGE_NAME_PREZIP}${USB_IMAGE_NAME}_usb.zip ${IMAGE_UNPACK_PATH}/*; \
        echo "######## clean ########################"; \
        rm -f ${DEPLOY_DIR_IMAGE}/*.rootfs.tar.bz2; \
        rm -f ${DEPLOY_DIR_IMAGE}/.tar.bz2; \
        rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
        rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
        rm -Rf vuplus; \
"
