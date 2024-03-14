DESCRIPTION = "enigma.info used by BoxInfo"
SUMMARY = "enigma.info used by BoxInfo"
PRIORITY = "required"
MAINTAINER = "OpenPLi team"

DEPENDS = "virtual/kernel"

require conf/license/openpli-gplv2.inc

deltask fetch
deltask unpack
deltask patch
deltask prepare_recipe_sysroot
deltask configure
deltask compile
deltask source_date_epoch

SSTATE_SKIP_CREATION = "1"

inherit linux-kernel-base
KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_DIR}') or oe.utils.read_file('${PKGDATA_DIR}/kernel-depmod/kernel-abiversion')}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PV = "${DISTRO_VERSION}"
PR[vardepsexclude] = "DATE"

PACKAGES = "${PN}"

inherit ${PYTHON_PN}-dir ${PYTHON_PN}native

# Hardware Branding

#Display type
DISPLAY_TYPE = "\
${@bb.utils.contains('MACHINE_FEATURES', 'textlcd', bb.utils.contains('MACHINE_FEATURES', '7segment', '' , 'textlcd', d) , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', '7segment', '7segment' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'bwlcd96', 'bwlcd96' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'bwlcd128', 'bwlcd128' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'bwlcd140', 'bwlcd140' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'bwlcd255', 'bwlcd255' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd', 'colorlcd' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd220', 'colorlcd220' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd240', 'colorlcd240' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd128', 'colorlcd128' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd400', 'colorlcd400' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd390', 'colorlcd390' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd480', 'colorlcd480' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd720', 'colorlcd720' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd800', 'colorlcd800' , '', d)}\
"

# Connectors
HAVE_HDMI = "${@bb.utils.contains('MACHINE_FEATURES', 'HDMI', 'True' , 'False', d)}"
HAVE_YUV = "${@bb.utils.contains('MACHINE_FEATURES', 'YUV', 'True' , 'False', d)}"
HAVE_RCA = "${@bb.utils.contains('MACHINE_FEATURES', 'RCA', 'True' , 'False', d)}"
HAVE_AV_JACK = "${@bb.utils.contains('MACHINE_FEATURES', 'AV_JACK', 'True' , 'False', d)}"
HAVE_SCART = "${@bb.utils.contains('MACHINE_FEATURES', 'SCART', 'True' , 'False', d)}"
HAVE_SCART_YUV = "${@bb.utils.contains('MACHINE_FEATURES', 'SCART-YUV', 'True' , 'False', d)}"
HAVE_NO_SCART_SWITCH = "${@bb.utils.contains('MACHINE_FEATURES', 'NO_SCART_SWITCH', 'True' , 'False', d)}"
HAVE_DVI = "${@bb.utils.contains('MACHINE_FEATURES', 'DVI', 'True' , 'False', d)}"
HAVE_SVIDEO = "${@bb.utils.contains("MACHINE_FEATURES", "SVIDEO", "True", "False", d)}"

#Extra Features
HAVE_HDMI_IN_HD = "${@bb.utils.contains('MACHINE_FEATURES', 'HDMI-IN-HD', 'True' , 'False', d)}"
HAVE_HDMI_IN_FHD = "${@bb.utils.contains('MACHINE_FEATURES', 'HDMI-IN-FHD', 'True' , 'False', d)}"
HAVE_WOL = "${@bb.utils.contains('MACHINE_FEATURES', 'wol', 'True' , 'False', d)}"
HAVE_WWOL = "${@bb.utils.contains('MACHINE_FEATURES', 'wwol', 'True' , 'False', d)}"
HAVE_CI = "${@bb.utils.contains('MACHINE_FEATURES', 'ci', 'True' , 'False', d)}"
HAVE_TRANSCODING = "${@bb.utils.contains('MACHINE_FEATURES', 'transcoding', 'True' , 'False', d)}"
HAVE_MULTITRANSCODING = "${@bb.utils.contains('MACHINE_FEATURES', 'multitranscoding', 'True' , 'False', d)}"
HAVE_FHDSKIN = "${@bb.utils.contains('MACHINE_FEATURES', 'skins1080', 'True' , 'False', d)}"
HAVE_SMALLFLASH = "${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "True", "False", d)}"
HAVE_MIDDLEFLASH = "${@bb.utils.contains("MACHINE_FEATURES", "middleflash", "True", "False", d)}"
HAVE_VFDSYMBOL = "${@bb.utils.contains("MACHINE_FEATURES", "vfdsymbol", "True", "False", d)}"
HAVE_KEXECMB = "${@bb.utils.contains("MACHINE_FEATURES", "kexecmb", "True", "False", d)}"

RCTYPE ??= "0"
RCNAME ??= "dmm1"
RCIDNUM ??= "2"
RCHARDWARE ??= "N/A"

STB_PLATFORM ?= "${MACHINE}"
MACHINE_MODEL ?= "${MACHINE}"
MEDIASERVICE ?= "${@bb.utils.contains("MACHINE_FEATURES", "himedia", "servicehisilicon" , "servicegstreamer", d)}"
BLINDSCAN_BINARY ?= "blindscan"
FORCE ?= "no"
SUPPORT_DBOXLCD ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "textlcd", "True", "False", d)}"
DEVELOPER_NAME ?= "${DISTRO_NAME}"
FRIENDLY_FAMILY ?= "${MACHINE}"
HDMISTANDBY_MODE ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "HDMISTANDBY", "1", "0", d)}"
TIMERWAKEUP_MODE ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "TIMERWAKEUP", "1", "0", d)}"

INFOFILE = "${libdir}/enigma.info"

do_install[nostamp] = "1"

do_install() {
# Python version

	PATTERN=`echo ${BBFILE_PATTERN_core} | cut -c 2-`
	PYTHON_FULLVERSION=`ls ${PATTERN}recipes-devtools/python/python?_*.bb | cut -d '_' -f 2 | xargs basename -s .bb`
	if [ "${PYTHON_FULLVERSION}" = "" ]; then
		PYTHON_FULLVERSION=${PYTHON_BASEVERSION}
	fi

# Image version
	IMAGE_VERSION=`echo ${DISTRO_VERSION} | cut -d "-" -f 1`

# OE version info
	OE_NAME=`cd ${OPENPLI_BASE} && git submodule | grep "meta-openembedded" | cut -d '(' -f 2 | cut -d ')' -f 1 | cut -d '/' -f 3`
	OE_VERSION=`cd ${OPENPLI_BASE} && git submodule | grep "openembedded-core" | cut -d '(' -f 2 | cut -d ')' -f 1 | cut -d '-' -f 2`

# OE-A compatible machine names

	MACHINEBUILD=${MACHINE}
	if [ "$MACHINE" = "alphatriplehd" ]; then
		MACHINEBUILD=alphatriple
	elif [ "$MACHINE" = "et1x000" ]; then
		MACHINEBUILD=gi11000
	elif [ "$MACHINE" = "et1x000" ]; then
		MACHINEBUILD=et7x00mini
	elif [ "$MACHINE" = "h3" ]; then
		MACHINEBUILD=zgemmah3
	elif [ "$MACHINE" = "h4" ]; then
		MACHINEBUILD=zgemmah4
	elif [ "$MACHINE" = "h5" ]; then
		MACHINEBUILD=zgemmah5
	elif [ "$MACHINE" = "h6" ]; then
		MACHINEBUILD=zgemmah6
	elif [ "$MACHINE" = "h7" ]; then
		MACHINEBUILD=zgemmah7
	elif [ "$MACHINE" = "h8" ]; then
		MACHINEBUILD=zgemmah82h
	elif [ "$MACHINE" = "h9" ]; then
		MACHINEBUILD=zgemmah9s
	elif [ "$MACHINE" = "h9combo" ]; then
		MACHINEBUILD=zgemmah9combo
	elif [ "$MACHINE" = "h9combose" ]; then
		MACHINEBUILD=zgemmah9combose
	elif [ "$MACHINE" = "h9se" ]; then
		MACHINEBUILD=zgemmah9se
	elif [ "$MACHINE" = "hd11" ]; then
		MACHINEBUILD=mutant11
	elif [ "$MACHINE" = "hd1100" ]; then
		MACHINEBUILD=mutant1100
	elif [ "$MACHINE" = "hd1200" ]; then
		MACHINEBUILD=mutant1200
	elif [ "$MACHINE" = "hd1265" ]; then
		MACHINEBUILD=mutant1265
	elif [ "$MACHINE" = "hd1500" ]; then
		MACHINEBUILD=mutant1500
	elif [ "$MACHINE" = "hd2400" ]; then
		MACHINEBUILD=mutant2400
	elif [ "$MACHINE" = "hd500c" ]; then
		MACHINEBUILD=mutant500c
	elif [ "$MACHINE" = "hd530c" ]; then
		MACHINEBUILD=mutant530c
	elif [ "$MACHINE" = "hd60" ]; then
		MACHINEBUILD=mutant60
	elif [ "$MACHINE" = "hd66se" ]; then
		MACHINEBUILD=mutant66se
	elif [ "$MACHINE" = "i55" ]; then
		MACHINEBUILD=zgemmai55
	elif [ "$MACHINE" = "i55plus" ]; then
		MACHINEBUILD=zgemmai55plus
	elif [ "$MACHINE" = "i55se" ]; then
		MACHINEBUILD=zgemmai55se
	elif [ "$MACHINE" = "lc" ]; then
		MACHINEBUILD=zgemmaslc
	elif [ "$MACHINE" = "lunix3-4k" ]; then
		MACHINEBUILD=lunix34k
	elif [ "$MACHINE" = "multibox" ]; then
		MACHINEBUILD=maxytecmulti
	elif [ "$MACHINE" = "multiboxse" ]; then
		MACHINEBUILD=maxytecmultise
	elif [ "$MACHINE" = "sh1" ]; then
		MACHINEBUILD=zgemmash1
	elif [ "$MACHINE" = "vs1000" ]; then
		MACHINEBUILD=vimastec1000
	elif [ "$MACHINE" = "vs1500" ]; then
		MACHINEBUILD=vimastec1500
	elif [ "$MACHINE" = "xp1000" ]; then
		MACHINEBUILD=xp1000max
	elif [ "$MACHINE" = "xpeedc" ]; then
		MACHINEBUILD=xpeedlxcc
	fi

#   driver data

    DRIVERSDATE='N/A'
    # machine specific
    if [ "${MACHINE}" = "vusolo4k" -o "${MACHINE}" = "vusolo2" -o "${MACHINE}" = "vusolose" -o "${MACHINE}" = "vuduo2" -o "${MACHINE}" = "vuuno4k" -o "${MACHINE}" = "vuuno4kse" -o "${MACHINE}" = "vuultimo4k" -o "${MACHINE}" = "vuzero4k" -o "${MACHINE}" = "vuduo4k" -o "${MACHINE}" = "vuduo4kse" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-VUPLUS}/recipes-bsp/drivers/vuplus-dvb-proxy-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "dm8000" ]; then
        DRIVERSDATE="20140604"
    elif [ "${MACHINE}" = "hd2400" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GFUTURES}/recipes-bsp/drivers/hd-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "bre2ze4k" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GFUTURES}/recipes-bsp/drivers/hd-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "et1x000" -o "${MACHINE}" = "et7000mini" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GI}/recipes-bsp/drivers/nextv-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "gbquad4k" -o "${MACHINE}" = "gbue4k" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GIGABLUE}/recipes-bsp/drivers/gigablue-platform-util-gb7252.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "xpeedc" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-XPEEDC}/recipes-bsp/drivers/nextv-dvb-modules-${MACHINE}.bb | cut -b 12-19`
	# by retail brand
    elif [ "${MACHINE_BRAND}" = "AB-COM" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-ABCOM}/recipes-bsp/drivers/abcom-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "AMIKO" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-AMIKO}/recipes-bsp/drivers/amiko-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "AXAS" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-AXAS}/recipes-bsp/drivers/axas-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Edision" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-EDISION}/recipes-bsp/drivers/edision-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Formuler" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-FORMULER}/recipes-bsp/drivers/formuler-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Miraclebox" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-MIRACLEBOX}/recipes-bsp/drivers/miraclebox-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Mut@nt" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GFUTURES}/recipes-bsp/drivers/hd-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "GigaBlue" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GIGABLUE}/recipes-bsp/drivers/gigablue-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Maxytec" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-MAXYTEC}/recipes-bsp/drivers/maxytec-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Octagon" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-OCTAGON}/recipes-drivers/octagon-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "qviart" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-QVIART}/recipes-drivers/qviart-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "SAB" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-SAB}/recipes-bsp/drivers/sab-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "SPYCAT" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-SPYCAT}/recipes-bsp/spycat-dvb-modules/spycat-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Uclan" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-UCLAN}/recipes-drivers/uclan-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Vimastec" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-GFUTURES}/recipes-bsp/drivers/hd-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Vu+" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-VUPLUS}/recipes-bsp/drivers/vuplus-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "MaxDigital" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-XP}/recipes-bsp/drivers/xp-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "XSARIUS" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-XSARIUS}/recipes-bsp/drivers/xsarius-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Xtrend" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-XTREND}/recipes-bsp/drivers/et-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE_BRAND}" = "Zgemma" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${BSP-BASE-ZGEMMA}/recipes-bsp/drivers/zgemma-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    fi

    install -d ${D}${libdir}
    printf "architecture=${DEFAULTTUNE}\n" > ${D}${INFOFILE}
    printf "avjack=${HAVE_AV_JACK}\n" >> ${D}${INFOFILE}
    printf "blindscanbinary=${BLINDSCAN_BINARY}\n" >> ${D}${INFOFILE}
    printf "brand=${BRAND_OEM}\n" >> ${D}${INFOFILE}
    printf "ci=${HAVE_CI}\n" >> ${D}${INFOFILE}
    printf "compiledate='${DATE}'\n" >> ${D}${INFOFILE}
    printf "dboxlcd=${SUPPORT_DBOXLCD}\n" >> ${D}${INFOFILE}
    printf "developername=${DEVELOPER_NAME}\n" >> ${D}${INFOFILE}
    printf "displaybrand=${MACHINE_BRAND}\n" >> ${D}${INFOFILE}
    printf "displaydistro=OpenPLi\n" >> ${D}${INFOFILE}
    printf "displaymodel=${MACHINE_NAME}\n" >> ${D}${INFOFILE}
    printf "displaytype=${DISPLAY_TYPE}\n" >> ${D}${INFOFILE}
    printf "distro=${DISTRO_NAME}\n" >> ${D}${INFOFILE}
    printf "driversdate='${DRIVERSDATE}'\n" >> ${D}${INFOFILE}
    printf "dvi=${HAVE_DVI}\n" >> ${D}${INFOFILE}
    printf "feedsurl=${DISTRO_FEED_URI}\n" >> ${D}${INFOFILE}
    printf "fhdskin=${HAVE_FHDSKIN}\n" >> ${D}${INFOFILE}
    printf "forcemode=${FORCE}\n" >> ${D}${INFOFILE}
    printf "fpu=${TARGET_FPU}\n" >> ${D}${INFOFILE}
    printf "friendlyfamily=${FRIENDLY_FAMILY}\n" >> ${D}${INFOFILE}
    printf "hdmi=${HAVE_HDMI}\n" >> ${D}${INFOFILE}
    printf "hdmifhdin=${HAVE_HDMI_IN_FHD}\n" >> ${D}${INFOFILE}
    printf "hdmihdin=${HAVE_HDMI_IN_HD}\n" >> ${D}${INFOFILE}
    printf "hdmistandbymode=${HDMISTANDBY_MODE}\n" >> ${D}${INFOFILE}
    printf "imagebuild='${DATE}'\n" >> ${D}${INFOFILE}
    printf "imagedevbuild='${DEVELOPER_BUILD_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imagedir=${IMAGEDIR}\n" >> ${D}${INFOFILE}
    printf "imagefs=${IMAGE_FSTYPES}\n" >> ${D}${INFOFILE}
    printf "imagetype=${DISTRO_TYPE}\n" >> ${D}${INFOFILE}
    printf "imageversion='${DISTRO_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imglanguage=multilang\n" >> ${D}${INFOFILE}
    printf "imgrevision='${DATE}'\n" >> ${D}${INFOFILE}
    printf "imgversion='${IMAGE_VERSION}'\n" >> ${D}${INFOFILE}
    printf "kernel='${KERNEL_VERSION}'\n" >> ${D}${INFOFILE}
    printf "kexecmb=${HAVE_KEXECMB}\n" >> ${D}${INFOFILE}
    printf "kernelfile=${KERNEL_FILE}\n" >> ${D}${INFOFILE}
    printf "machine=${MACHINE}\n" >> ${D}${INFOFILE}
    printf "machinebuild=${MACHINEBUILD}\n" >> ${D}${INFOFILE}
    printf "mediaservice=${MEDIASERVICE}\n" >> ${D}${INFOFILE}
    printf "middleflash=${HAVE_MIDDLEFLASH}\n" >> ${D}${INFOFILE}
    printf "mkubifs=${MKUBIFS_ARGS}\n" >> ${D}${INFOFILE}
    printf "model=${MACHINE_MODEL}\n" >> ${D}${INFOFILE}
    printf "mtdbootfs=${MTD_BOOTFS}\n" >> ${D}${INFOFILE}
    printf "mtdkernel=${MTD_KERNEL}\n" >> ${D}${INFOFILE}
    printf "mtdrootfs=${MTD_ROOTFS}\n" >> ${D}${INFOFILE}
    printf "multilib=False\n" >> ${D}${INFOFILE}
    printf "multitranscoding=${HAVE_MULTITRANSCODING}\n" >> ${D}${INFOFILE}
    printf "oe=${OE_NAME} (${OE_VERSION})\n" >> ${D}${INFOFILE}
    printf "platform=${STB_PLATFORM}\n" >> ${D}${INFOFILE}
    printf "python='${PYTHON_FULLVERSION}'\n" >> ${D}${INFOFILE}
    printf "rca=${HAVE_RCA}\n" >> ${D}${INFOFILE}
    printf "rcidnum=${RCIDNUM}\n" >> ${D}${INFOFILE}
    printf "rcname=${RCNAME}\n" >> ${D}${INFOFILE}
    printf "rctype=${RCTYPE}\n" >> ${D}${INFOFILE}
    printf "rootfile=${ROOTFS_FILE}\n" >> ${D}${INFOFILE}
    printf "scart=${HAVE_SCART}\n" >> ${D}${INFOFILE}
    printf "noscartswitch=${NO_SCART_SWITCH}\n" >> ${D}${INFOFILE}
    printf "scartyuv=${HAVE_SCART_YUV}\n" >> ${D}${INFOFILE}
    printf "smallflash=${HAVE_SMALLFLASH}\n" >> ${D}${INFOFILE}
    printf "socfamily='${SOC_FAMILY}'\n" >> ${D}${INFOFILE}
    printf "svideo=${HAVE_SVIDEO}\n" >> ${D}${INFOFILE}
    printf "timerwakeupmode=${TIMERWAKEUP_MODE}\n" >> ${D}${INFOFILE}
    printf "transcoding=${HAVE_TRANSCODING}\n" >> ${D}${INFOFILE}
    printf "ubinize=${UBINIZE_ARGS}\n" >> ${D}${INFOFILE}
    printf "vfdsymbol=${HAVE_VFDSYMBOL}\n" >> ${D}${INFOFILE}
    printf "wol=${HAVE_WOL}\n" >> ${D}${INFOFILE}
    printf "wwol=${HAVE_WWOL}\n" >> ${D}${INFOFILE}
    printf "yuv=${HAVE_YUV}\n" >> ${D}${INFOFILE}
    printf "checksum=%s\n" $(md5sum "${D}${INFOFILE}" | awk '{print $1}') >> ${D}${INFOFILE}
}

do_install[vardepsexclude] += " DATE DATETIME IMAGE_BUILD"

FILES:${PN}:append = " /usr"

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/../../enigma-info
	install -m 0644 ${D}${INFOFILE} ${DEPLOY_DIR_IMAGE}/../../enigma-info/${MACHINE}.txt
}

addtask deploy before do_package after do_install

pkg_postinst_ontarget_${PN} () {
#!/bin/sh
set -e

if [ -n "$D" ]; then
    $INTERCEPT_DIR/postinst_intercept delay_to_first_boot enigma-info mlprefix=
    exit 0
fi

# description: Update the enigma.info file with runtime data

INFOFILE=/usr/lib/enigma.info
PROC_MODEL=/proc/stb/info/model
PROC_HWMODEL=/proc/stb/info/hwmodel
PROC_BOXTYPE=/proc/stb/info/boxtype
PROC_TYPE=/proc/stb/info/type
WIFI1=/sys/devices/platform/soc/f9890000.ehci/usb1/1-1/idProduct
WIFI2=/sys/devices/platform/soc/f9890000.ehci/usb1/1-2/idProduct

#
# bail out if the info file does not exist
#
if [ ! -f $INFOFILE ]; then
    exit 0
fi

#
# fetch the boxes' reported hardware model
#
hwmodel() { value=""; if [ -f $PROC_BOXTYPE ]; then value=$(head -n 1 $PROC_BOXTYPE); elif [ -f $PROC_HWMODEL ]; then value=$(head -n 1 $PROC_HWMODEL); elif [ -f $PROC_MODEL ]; then value=$(head -n 1 $PROC_MODEL); fi; echo $value | tr '[:upper:]' '[:lower:]'; }

#
# fetch the boxes' reported stb type
#
hwtype() { prefix=$1; value=""; if [ -f $PROC_TYPE ]; then value=$(head -n 1 $PROC_TYPE); fi; echo $value; }

#
# check if $1 starts with $2
#
startswith() { prefix=$1; value=$2; case $prefix in "$value"*) return 0;; esac; return 1; }

#
# update TMPFILE with new information
#
updateinfo() { key=$1; value=$2; sed -i "s/^${key}=.*/$key=$value/" $TMPFILE; }

#
# add the runime values to the enigma.info file
#
TMPFILE=$(mktemp /tmp/enigma-info.XXXXXX)

# make a copy, remove the checksum
grep -v checksum $INFOFILE > $TMPFILE

# get the machine name for this image
MACHINE=`grep "machine=" $TMPFILE | cut -d '=' -f 2`

# get runtime data
type=`hwtype`
model=`hwmodel`

# runtime fixes for the Zgemma H10
if [ "$MACHINE" = "h10" ]; then
	if [ "$model" = "h10.t" ]; then
		updateinfo "displaymodel" "H10 Combo"
		updateinfo "machinebuild" "zgemmah10combo"
	elif [ "$model" = "h10.2s" ]; then
		updateinfo "displaymodel" "H10.2S"
		updateinfo "machinebuild" "zgemmah102s"
	elif [ "$model" = "h10.2h" ]; then
		updateinfo "displaymodel" "H10.2H"
		updateinfo "machinebuild" "zgemmah102h"
	fi

# runtime fixes for the Zgemma H11
elif [ "$MACHINE" = "h11" ]; then
	if [ "$model" = "h11.s" ]; then
		updateinfo "displaymodel" "H11.S"
		updateinfo "machinebuild" "zgemmah11s"
	elif [ "$model" = "h11.2h" ]; then
		updateinfo "displaymodel" "H11.2H"
		updateinfo "machinebuild" "zgemmah112h"
	fi

# runtime fixes for the Zgemma H3
elif [ "$MACHINE" = "h3" ]; then
	:
	# displaymodel=H2.H
	# machinebuild=zgemmah2h

	# displaymodel=H3.2TC
	# machinebuild=zgemmah32tc

	# displaymodel=H3 AC
	# machinebuild=zgemmah3ac

# runtime fixes for the Zgemma H5
elif [ "$MACHINE" = "h5" ]; then
	:
	# displaymodel=H5.2S PLUS
	# machinebuild=zgemmah52splus

	# displaymodel=H5.2S
	# machinebuild=zgemmah52s

	# displaymodel=H5.2TC
	# machinebuild=zgemmah52tc

	# displaymodel=H5 AC
	# machinebuild=zgemmah5ac

	# displaymodel=H5
	# machinebuild=zgemmah5

# runtime fixes for the Zgemma H9
elif [ "$MACHINE" = "h9" ]; then
	if [ "$model" = "h9.s" ]; then
		updateinfo "displaymodel" "H9.S"
		updateinfo "machinebuild" "zgemmah9s"
	elif [ "$model" = "h9.t" ]; then
		updateinfo "displaymodel" "H9.T"
		updateinfo "machinebuild" "zgemmah9t"
	elif [ "$model" = "h9.2h" -o  "$model" = "h9" ]; then
		updateinfo "displaymodel" "H9.2H"
		updateinfo "machinebuild" "zgemmah92h"
	elif [ "$model" = "h9.2s" ]; then
		updateinfo "displaymodel" "H9.2S"
		updateinfo "machinebuild" "zgemmah92s"
	elif [ "$model" = "h9twin"  -o  "$model" = "h9 twin"  ]; then
		updateinfo "displaymodel" "H9 TWIN"
		updateinfo "machinebuild" "zgemmah9twin"
	elif [ "$model" = "h9combo" ]; then
		updateinfo "displaymodel" "H9 COMBO"
		updateinfo "machinebuild" "zgemmah9combo"
	fi

# runtime fixes for the Zgemma H9 SE
elif [ "$MACHINE" = "h9se" ]; then
	if [ "$model" = "h9.s.se" ]; then
		updateinfo "displaymodel" "H9.S SE"
		updateinfo "machinebuild" "zgemmah9sse"
	elif [ "$model" = "h9se" ]; then
		updateinfo "displaymodel" "H9S SE"
		updateinfo "machinebuild" "zgemmah9sse"
	elif [ "$model" = "h9.2s.se" ]; then
		updateinfo "displaymodel" "H9.2S SE"
		updateinfo "machinebuild" "zgemmah92sse"
	elif [ "$model" = "h9.2h.se" ]; then
		updateinfo "displaymodel" "H9.2H SE"
		updateinfo "machinebuild" "zgemmah9hse"
	elif [ "$model" = "h9twinse" ]; then
		updateinfo "displaymodel" "H9 TWIN SE"
		updateinfo "machinebuild" "zgemmah9twinse"
	elif [ "$model" = "h9combose" ]; then
		updateinfo "displaymodel" "H9 COMBO SE"
		updateinfo "machinebuild" "zgemmah9combose"
	fi

# runtime fixes for the Octagon SF8008
elif [ "$MACHINE" = "sf8008" ]; then
	if startswith "11" $type; then
		updateinfo "machinebrand" "sf8008t"
		updateinfo "displaymodel" "SF8008 4K Twin"
	elif startswith "12" $type; then
		if [ -f $WIFI2 ]; then
			value=$(head -n 1 $WIFI2)
		else
			value=""
		fi
		if [ "$value" = "c82c" ]; then
			updateinfo "machinebrand" "sf8008"
			updateinfo "displaymodel" "SF8008 4K Supreme"
		else
			updateinfo "machinebrand" "sf8008c"
			updateinfo "displaymodel" "SF8008 4K Combo"
		fi
	else  # startswith "10"
		updateinfo "machinebrand" "sf8008s"
		updateinfo "displaymodel" "SF8008 4K Single"
	fi

# runtime fixes for the Octagon SFX6008
elif [ "$MACHINE" = "sfx6008" ]; then
	if startswith "10" $type; then
		updateinfo "machinebrand" "sfx6018"
		updateinfo "displaymodel" "SFX6018 S2 IP"
	else # startswith "11"
		if [ -f $WIFI1 ]; then
			value=$(head -n 1 $WIFI1)
		else
			value=""
		fi
		if [ "$value" = "f179" ]; then
			updateinfo "machinebrand" "sfx6008wl"
			updateinfo "displaymodel" "SFX6008 WL"
		else
			updateinfo "machinebrand" "sfx6008"
			updateinfo "displaymodel" "SFX6008 IP"
		fi
	fi

# runtime fixes for the Uclan Ustym 4K Pro
elif [ "$MACHINE" = "ustym4kpro" ]; then
	if [ -f $WIFI2 ]; then
		value=$(head -n 1 $WIFI2)
	else
		value=""
	fi
	if [ "$value" = "c82c" ]; then
		updateinfo "machinebrand" "ustym4kpro"
		updateinfo "displaymodel" "Ustym 4K PRO Ultimate"
	elif startswith "11" $type; then
		updateinfo "machinebrand" "ustym4ktwin"
		updateinfo "displaymodel" "Ustym 4K Twin"
	fi
fi

# re-add the checksum
MD5SUM=`md5sum $TMPFILE | cut -d ' ' -f 1`
echo "checksum=$MD5SUM" >> $TMPFILE
mv $TMPFILE $INFOFILE

exit 0
}
