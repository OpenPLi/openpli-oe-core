DESCRIPTION = "enigma.info used by BoxInfo"
SUMMARY = "enigma.info used by BoxInfo"
PRIORITY = "required"
MAINTAINER = "OpenPLi team"

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
IMAGE_VERSION = "${DISTRO_VERSION}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PV = "${IMAGE_VERSION}"
PR[vardepsexclude] = "DATE"

PACKAGES = "${PN}"

inherit python3-dir 

# Hardware Branding

#Display type
DISPLAY_TYPE = "\
${@bb.utils.contains('MACHINE_FEATURES', 'textlcd', 'textlcd' , '', d)}\
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
HAVE_MINITV = "${@bb.utils.contains('MACHINE_FEATURES', 'MINITV', 'True' , 'False', d)}"
HAVE_HDMI_IN_HD = "${@bb.utils.contains('MACHINE_FEATURES', 'HDMI-IN-HD', 'True' , 'False', d)}"
HAVE_HDMI_IN_FHD = "${@bb.utils.contains('MACHINE_FEATURES', 'HDMI-IN-FHD', 'True' , 'False', d)}"
HAVE_WOL = "${@bb.utils.contains('MACHINE_FEATURES', 'WOL', 'True' , 'False', d)}"
HAVE_WWOL = "${@bb.utils.contains('MACHINE_FEATURES', 'WWOL', 'True' , 'False', d)}"
HAVE_CI = "${@bb.utils.contains('MACHINE_FEATURES', 'ci', 'True' , 'False', d)}"
HAVE_TRANSCODING = "${@bb.utils.contains('MACHINE_FEATURES', 'transcoding', 'True' , 'False', d)}"
HAVE_MULTITRANSCODING = "${@bb.utils.contains('MACHINE_FEATURES', 'multitranscoding', 'True' , 'False', d)}"
HAVE_FHDSKIN = "${@bb.utils.contains('MACHINE_FEATURES', 'skins1080', 'True' , 'False', d)}"
HAVE_SMALLFLASH = "${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "True", "False", d)}"
HAVE_MIDDLEFLASH = "${@bb.utils.contains("MACHINE_FEATURES", "middleflash", "True", "False", d)}"
HAVE_MULTILIB = "${@bb.utils.contains("MACHINE_FEATURES", "multilib", "True", "False", d)}"
HAVE_VFDSYMBOL = "${@bb.utils.contains("MACHINE_FEATURES", "vfdsymbol", "True", "False", d)}"
HAVE_KEXECMB = "${@bb.utils.contains("MACHINE_FEATURES", "kexecmb", "True", "False", d)}"

RCTYPE ??= "0"
RCNAME ??= "dmm1"
RCIDNUM ??= "2"
RCHARDWARE ??= "N/A"

#deprecated setting
TRANSCODING = "\
${@bb.utils.contains('MACHINE_FEATURES', 'transcoding', 'transcoding' , '', d)}\
${@bb.utils.contains('MACHINE_FEATURES', 'multitranscoding', 'multitranscoding' , '', d)}\
"

LANGUAGECHECK = "${@bb.utils.contains_any("FLASHSIZE", "64 96", "DEU;ENG" , "multilang", d)}"
LANGUAGE = "${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash", "DEU;ENG", "${LANGUAGECHECK}", d)}"

STB_PLATFORM ?= "${MACHINE}"
MEDIASERVICE ?= "${@bb.utils.contains("MACHINE_FEATURES", "himedia", "servicehisilicon" , "servicegstreamer", d)}"
BLINDSCAN_BINARY ?= "blindscan"
FORCE ?= "no"
SUPPORT_DBOXLCD ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "textlcd", "True", "False", d)}"
DEVELOPER_NAME ?= "${DISTRO_NAME}"
FRIENDLY_FAMILY ?= "${MACHINE} ${BUILDMACHINE}"
HDMISTANDBY_MODE ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "HDMISTANDBY", "1", "0", d)}"
TIMERWAKEUP_MODE ?= "${@bb.utils.contains_any("MACHINE_FEATURES", "TIMERWAKEUP", "1", "0", d)}"

INFOFILE = "${libdir}/enigma.info"

do_install[nostamp] = "1"

do_install() {
    if [ "${MACHINE}" = "vusolo4k" -o "${MACHINE}" = "vusolo2" -o "${MACHINE}" = "vusolose" -o "${MACHINE}" = "vuduo2" -o "${MACHINE}" = "vuuno4k" -o "${MACHINE}" = "vuuno4kse" -o "${MACHINE}" = "vuultimo4k" -o "${MACHINE}" = "vuzero4k" -o "${MACHINE}" = "vuduo4k" -o "${MACHINE}" = "vuduo4kse" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-VUPLUS-BASE}/recipes-drivers/vuplus-dvb-proxy-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "vuplus" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-VUPLUS-BASE}/recipes-drivers/vuplus-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "amiko" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-AMIKO-BASE}/recipes-drivers/amiko-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "qviart" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-QVIART-BASE}/recipes-drivers/qviart-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "xtrend" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-XTREND-BASE}/recipes-drivers/et-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "xsarius" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-DAGS-BASE}/recipes-drivers/dags-dvb-modules-${MACHINE_DRIVER}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "gb7252" -o "${MACHINE}" = "gb72604" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-GIGABLUE-BASE}/recipes-drivers/gigablue-platform-util-${MACHINE_DRIVER}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "gigablue" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-GIGABLUE-BASE}/recipes-drivers/gigablue-dvb-modules-${MACHINE_DRIVER}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "octagon" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-OCTAGON-BASE}/recipes-drivers/octagon-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "uclan" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-UCLAN-BASE}/recipes-drivers/uclan-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "xp" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-XP-BASE}/recipes-drivers/xp-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "formuler" ]; then
        if [ "${MACHINE}" = "formuler1" ] || [ "${MACHINE}" = "formuler3" ] || [ "${MACHINE}" = "formuler4" ]; then
            DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-FORMULER-BASE}/recipes-drivers/formuler-dvb-modules-${MACHINE}.bb | cut -b 12-19`
        else
            DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-FORMULER-BASE}/recipes-drivers/formuler-dvb-modules-al-${MACHINE}.bb | cut -b 12-19`
        fi
    elif [ "${BRAND_OEM}" = "gfutures" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-GFUTURES-BASE}/recipes-drivers/gfutures-dvb-modules-${MACHINE_DRIVER}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "airdigital" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-AIRDIGITAL-BASE}/recipes-drivers/airdigital-dvb-modules-${MACHINE_DRIVER}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "edision" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-EDISION-BASE}/recipes-drivers/edision-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "maxytec" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-MAXYTEC-BASE}/recipes-drivers/maxytec-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "abcom" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-ABCOM-BASE}/recipes-drivers/abcom-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "dreambox" ]; then
        if [ "${MACHINE}" = "dm8000" ]; then
            DRIVERSDATE="20140604"
        fi
    else
        DRIVERSDATE='N/A'
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
    printf "imagebuild='${BUILD_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imagedevbuild='${DEVELOPER_BUILD_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imagedir=${IMAGEDIR}\n" >> ${D}${INFOFILE}
    printf "imagefs=${IMAGE_FSTYPES}\n" >> ${D}${INFOFILE}
    printf "imagetype=${DISTRO_TYPE}\n" >> ${D}${INFOFILE}
    printf "imageversion='${DISTRO_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imglanguage=${LANGUAGE}\n" >> ${D}${INFOFILE}
    printf "imgrevision='${BUILD_VERSION}'\n" >> ${D}${INFOFILE}
    printf "imgversion='${DISTRO_VERSION}'\n" >> ${D}${INFOFILE}
    printf "kernel='${KERNEL_VERSION}'\n" >> ${D}${INFOFILE}
    printf "kexecmb=${HAVE_KEXECMB}\n" >> ${D}${INFOFILE}
    printf "kernelfile=${KERNEL_FILE}\n" >> ${D}${INFOFILE}
    printf "machinebuild=${MACHINE}\n" >> ${D}${INFOFILE}
    printf "mediaservice=${MEDIASERVICE}\n" >> ${D}${INFOFILE}
    printf "middleflash=${HAVE_MIDDLEFLASH}\n" >> ${D}${INFOFILE}
    printf "mkubifs=${MKUBIFS_ARGS}\n" >> ${D}${INFOFILE}
    printf "model=${MACHINE}\n" >> ${D}${INFOFILE}
    printf "mtdbootfs=${MTD_BOOTFS}\n" >> ${D}${INFOFILE}
    printf "mtdkernel=${MTD_KERNEL}\n" >> ${D}${INFOFILE}
    printf "mtdrootfs=${MTD_ROOTFS}\n" >> ${D}${INFOFILE}
    printf "multilib=${HAVE_MULTILIB}\n" >> ${D}${INFOFILE}
    printf "multitranscoding=${HAVE_MULTITRANSCODING}\n" >> ${D}${INFOFILE}
    printf "oe=${OE_VER}\n" >> ${D}${INFOFILE}
    printf "platform=${STB_PLATFORM}\n" >> ${D}${INFOFILE}
    printf "python='${PYTHON_BASEVERSION}'\n" >> ${D}${INFOFILE}
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
	install -d ${DEPLOY_DIR_IMAGE}/enigma-info
	install -m 0644 ${D}${INFOFILE} ${DEPLOY_DIR_IMAGE}/enigma-info/${MACHINE}.txt
}

addtask deploy before do_package after do_install
