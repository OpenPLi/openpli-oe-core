DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPL"
SECTION = "kernel"
KV = "3.3.8"

PR_INC = ".7"

SRCREV = ""

SRC_URI[md5sum] = "bfd424a21d3daeb3a100bf8e7443d302"
SRC_URI[sha256sum] = "f8a482de06251761d792ff7ea7fcb73eca4139e5cb80c3b38e39d1c4ef3a35c9"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-vuplus-${KV}:"

SRC_URI += "http://archive.vuplus.com/download/kernel/stblinux-3.3.6-2.0.tar.bz2 \
	file://brcm_3.3.patch;patch=1;pnum=1 \
	file://fix_cpu_proc.patch;patch=1;pnum=1 \
	file://brcm_mtd_mac.patch;patch=1;pnum=1 \
	file://dvb_core_5.5.patch;patch=1;pnum=1 \
	file://bmips-no-array-bounds.patch;patch=1;pnum=1 \
	file://mips-refactor-clearpage-and-copypage.patch \
	file://dvb-usb-af9035.patch \
	file://tda18218-7mhz-lopass.patch \
	file://dvb-usb-a867.patch \
	file://dvb-usb-rtl2832.patch \
	file://cxd2820r-enable-LNA-for-DVB-T.patch \
	file://cxd2820r-changed-condition-to-break-out-from-wait-lock-loop.patch \
	file://cxd2820r-output-full-range-SNR.patch \
	file://cinergy_s2_usb_r2.patch \
	file://as102-scale-MER-to-full-range.patch \
	file://as102-adjust-signal-strength-report.patch \
	file://em28xx-pre-allocate-DVB-isoc-transfer-buffers.patch \
	file://em28xx-dvb-stop-URBs-when-stopping-the-streaming.patch \
	file://af9015-output-full-range-SNR.patch \
	file://it913x-backports-from-kernel-3.4.patch \
	file://it913x-backports-from-kernel-3.5.patch \
	file://it913x-switch-off-PID-filter-by-default.patch \
	file://it913x-fix-bulk-read-write-retry-loop.patch \
	file://it913x-enable-endpoint-3-on-devices-with-HID-interface.patch \
	file://dvb-usb-dib0700-disable-sleep.patch \
	file://dvb_usb_disable_rc_polling.patch \
	file://dvb-usb-siano-always-load-smsdvb.patch \
	file://${MACHINE}_defconfig \
	file://nfs-max-rwsize-8k.patch \
"

S = "${WORKDIR}/linux"

inherit kernel machine_kernel_pr

export OS = "Linux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "vmlinux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"


do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
	oe_runmake oldconfig
}

kernel_do_install_append() {
	install -d ${D}/${KERNEL_IMAGEDEST}
	install -m 0755 ${KERNEL_OUTPUT} ${D}${KERNEL_IMAGEDEST}
	gzip ${D}${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}
}

pkg_postinst_kernel-image () {
	if [ -d /proc/stb ] ; then
		flash_eraseall /dev/mtd2
		nandwrite -p /dev/mtd2 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	fi
	rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
	true
}


