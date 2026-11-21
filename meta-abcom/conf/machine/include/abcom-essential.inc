MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	abcom-dvb-modules-${MACHINE} \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'abcom-libs-${MACHINE} abcom-param-${MACHINE} abcom-bootargs-${MACHINE} abcom-fastboot-${MACHINE} abcom-logo-${MACHINE} abcom-recovery-${MACHINE}' , '', d)} \
	"

COBALT_HILOADER_BUILD_DEPENDS = "${@bb.utils.contains('MACHINE_FEATURES', 'hisil mali', 'abcom-libs-${MACHINE} abcom-mali-${MACHINE}', '', d)}"
COBALT_HILOADER_RDEPENDS = "${COBALT_HILOADER_BUILD_DEPENDS}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	abcom-ethwol \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'abcom-mali-${MACHINE} kernel-module-mali-${MACHINE}' , '', d)} \
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
