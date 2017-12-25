MACHINE_KERNEL_PR_append = ".${INC_PR}.52"

COMPATIBLE_MACHINE = "dm8000"

PATCHREV = "3c7230bc0819495db75407c365f4d1db70008044"
PATCHLEVEL = "68"

SRC_URI = " \
			${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-3.2.tar.bz2;name=kernel \
			${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-3.2.${PATCHLEVEL}.xz;apply=yes;name=kernel-patch \
			http://sources.dreamboxupdate.com/download/kernel-patches/${P}-${PATCHREV}.patch.bz2;name=dmm-patch \
			http://download.filesystems.org/unionfs/unionfs-2.x-latest/unionfs-2.6_for_3.2.62.diff.gz;name=unionfs \
			file://clear_sublevel.patch \
			file://0001-Revert-MIPS-Fix-potencial-corruption.patch \
			file://fadvise_dontneed_change.patch \
			file://fix-proc-cputype.patch \
			file://mips-refactor-clearpage-and-copypage.patch \
			file://rtl8712-backport-b.patch \
			file://rtl8712-backport-c.patch \
			file://rtl8712-backport-d.patch \
			file://make-3.82-hack.patch \
			file://0001-SCSI-sd-Use-SCSI-read-write-16-with-32-bit-LBA-drive.patch;apply=no \
			file://0002-add-crypto-api-xz-support.patch;apply=no \
			file://0003-add-XZ-compression-support-to-UBIFS.patch;apply=no \
			file://0004-block2mtd-add-possibility-to-change-the-writesize.patch;apply=no \
			file://0005-block2mtd-add-possibility-to-remove-block2mtd-device.patch;apply=no \
			file://0006-mtd-block2mtd-fix-recursive-call-of-mtd_writev.patch;apply=no \
			file://0007-mtd-block2mtd-throttle-writes-by-calling-balance_dir.patch;apply=no \
			file://0001-brmcnand_base-disable-flash-BBT-on-64MB-nand.patch \
			file://0002-ubifs-add-config-option-to-use-zlib-as-default-compr.patch \
			file://em28xx_fix_terratec_entries.patch \
			file://em28xx_add_terratec_h5_rev3.patch \
			file://dvb-usb-siano-always-load-smsdvb.patch \
			file://dvb-usb-af9035.patch \
			file://dvb-usb-a867.patch \
			file://dvb-usb-rtl2832.patch \
			file://dvb_usb_disable_rc_polling.patch \
			file://dvb-usb-smsdvb_fix_frontend.patch \
			file://0001-it913x-backport-changes-to-3.2-kernel.patch \
			file://0001-linuxtv-api-DMM-drivers-are-now-ready-for-linux-tv-a.patch;apply=no \
			file://rtl8712-fix-warnings.patch \
			file://fixme-hardfloat.patch \
			file://0001-correctly-initiate-nand-flash-ecc-config-when-old-2n.patch \
			file://kernel-add-support-for-gcc7.patch \
			file://0001-misc-latin1-to-utf8-conversions.patch \
			file://fix-build-with-gcc7.patch \
			file://defconfig \
"

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[kernel-patch.md5sum] = "8ba205b73dcd6aa6748d916af294b6f0"
SRC_URI[kernel-patch.sha256sum] = "77368e2ab9d8d9282ff6e00973fe0ba7948e6b519f2efcab3b008c59526f1bd3"
SRC_URI[dmm-patch.md5sum] = "9bce4d986a4bfcccdc4b2fecd849269d"
SRC_URI[dmm-patch.sha256sum] = "8914df36eb1f6a270d2b32c46d93cb81bbaae02604fba6135a9b1509e1ec1d84"
SRC_URI[unionfs.md5sum] = "348e5021d5340f12e2968ff4eb74d45d"
SRC_URI[unionfs.sha256sum] = "c0c449a445e9e07c5b1ba8e8c40013c0e40a5948c30a7190677d19ba7358c11a"

require linux-dreambox.inc

S = "${WORKDIR}/linux-3.2"
