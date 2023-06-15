SUMMARY = "libdvdcss is a simple library for accessing DVDs like block devices"
DESCRIPTION = "libdvdcss is a simple library designed for accessing DVDs like a block device without having to bother about the decryption."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/xbmc/libdvdcss.git;protocol=https;branch=nexus"

inherit autotools

SRCREV = "84a7ba82a31f4ac73d93de108ee8eaa2d250cf5e"
S = "${WORKDIR}/git"
PV = "1.4.3-Next-Nexus-Alpha2"

EXTRA_OECONF = " --disable-doc "

