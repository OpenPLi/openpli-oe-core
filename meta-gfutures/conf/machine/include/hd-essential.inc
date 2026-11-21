MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	hd-dvb-modules-${MACHINE} \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'hd-libs-${MACHINE} hd-partitions-${MACHINE} hd-bootargs-${MACHINE}' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil emmc', 'hd-recovery-${MACHINE}' , '', d)} \
	"

COBALT_HILOADER_BUILD_DEPENDS = "${@bb.utils.contains('MACHINE_FEATURES', 'hisil mali', 'hd-libs-${MACHINE} hd-mali-${MACHINE}', '', d)}"
COBALT_HILOADER_RDEPENDS = "${COBALT_HILOADER_BUILD_DEPENDS}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-mipsel', 'hd-v3ddriver-${MACHINE}' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-cortexa15', 'hd-v3ddriver-${MACHINE}' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'qtegls', 'hd-qteglfs-platform', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'hd-mali-${MACHINE} kernel-module-mali-${MACHINE}' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'emmc', 'resizerootfs partitions-by-name' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'v3d-nxpl', 'hd-v3ddriver-${MACHINE}' , '', d)} \	
	\
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-mt7601u \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-rtl8723bu \
	firmware-rtl8188eu \
	firmware-rtl8192cu \
	firmware-rtl8192eu \
	firmware-rtl8xxxu \
	firmware-zd1211 \
	\
	kernel-module-8192eu \
	kernel-module-8192fu \
	kernel-module-8723bu \
	kernel-module-88x2bu \
	kernel-module-8821cu \
	\
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-mt7601u \
	kernel-module-r8712u \
	kernel-module-r8723au \
	kernel-module-r8188eu \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-rtl8192cu \
	kernel-module-rtl8xxxu \
	kernel-module-zd1211rw \
	"
