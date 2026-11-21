MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	maxytec-dvb-modules-${MACHINE} \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'maxytec-libs-${MACHINE} maxytec-param-${MACHINE} maxytec-bootargs-${MACHINE} maxytec-fastboot-${MACHINE} maxytec-logo-${MACHINE} maxytec-recovery-${MACHINE} maxytec-dtbo-${MACHINE}' , '', d)} \
	"

COBALT_HILOADER_BUILD_DEPENDS = "${@bb.utils.contains('MACHINE_FEATURES', 'hisil mali', 'maxytec-libs-${MACHINE} maxytec-mali-${MACHINE}', '', d)}"
COBALT_HILOADER_RDEPENDS = "${COBALT_HILOADER_BUILD_DEPENDS}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	maxytec-ethwol \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'maxytec-mali-${MACHINE} kernel-module-mali-${MACHINE}' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'emmc', 'resizerootfs partitions-by-name' , '', d)} \
	\
	firmware-mt7601u \
	firmware-rt2870 \
	firmware-rtl8188eu \
	firmware-rtl8192cu \
	firmware-rtl8192eu \
	firmware-rtl8723bu \
	firmware-rtl8xxxu \
	\
	kernel-module-8192eu \
	kernel-module-8723bu \
	kernel-module-8821cu \
	kernel-module-88x2bu \
	\
	kernel-module-mt7601u \
	kernel-module-r8723au \
	kernel-module-r8188eu \
	kernel-module-rt2800usb \
	kernel-module-rtl8192cu \
	kernel-module-8192fu \
	kernel-module-rtl8188fu \
	kernel-module-rtl8xxxu \
	"
