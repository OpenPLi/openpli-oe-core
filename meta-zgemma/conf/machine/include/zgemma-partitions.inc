# Image packaging reads these files from the shared deployment directory.
ZGEMMA_PARTITION_DEPENDS = " \
    virtual/kernel:do_deploy \
    zgemma-bootargs-${HICHIPSET}:do_deploy \
    zgemma-bootlogo-${HICHIPSET}:do_deploy \
    zgemma-fastboot-${HICHIPSET}:do_deploy \
    zgemma-param-${HICHIPSET}:do_deploy \
    zgemma-recovery-${HICHIPSET}:do_deploy \
"

do_image_ubi[depends] += "${ZGEMMA_PARTITION_DEPENDS}"
do_image_zgemmafastboot8gb[depends] += "${ZGEMMA_PARTITION_DEPENDS}"
