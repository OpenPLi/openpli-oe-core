DESCRIPTION = "Box logos for Enimga2."
MAINTAINER = "DimitarCC"
HOMEPAGE = "https://github.com/DimitarCC/e2-boxlogos"
require conf/license/license-gplv2.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"

inherit gitpkgv ${PYTHON_PN}native

PV = "1.0+gitr${SRCPV}"
PKGV = "1.0+gitr${GITPKGV}"

do_configure[nostamp] = "1"

SRC_URI = "git://github.com/DimitarCC/e2-boxlogos.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

FILES:${PN} = "${datadir}/enigma2/logos/ ${libdir}/enigma2/python/Components/Renderer/"
FILES:${PN}-src = "${libdir}/enigma2/python/Components/Renderer/*.py"

do_install() {
	# make sure the enigma info file exists
	INFOFILE=${DEPLOY_DIR_IMAGE}/../../enigma-info/${MACHINE}.txt
	if [ ! -f $INFOFILE ]; then
		exit 1
	fi

	install -d ${D}${datadir}/enigma2/logos

	MACHINE_BRAND=$( cat $INFOFILE | grep "displaybrand=" | cut -d "=" -f 2- )
	DISTRO_NAME=$( cat $INFOFILE | grep "distro=" | cut -d "=" -f 2- )

	if [ -f "${S}/brand/${MACHINE_BRAND}.svg" ] ; then
		install -m 0644 "${S}/brand/${MACHINE_BRAND}.svg" ${D}${datadir}/enigma2/logos/brandlogo.svg
	fi

	if [ -f "${S}/distro/${DISTRO_NAME}.svg" ] ; then
		install -m 0644 "${S}/distro/${DISTRO_NAME}.svg" ${D}${datadir}/enigma2/logos/distrologo.svg
	fi

	if [ -f "${S}/box/${MACHINE}.svg" ] ; then
		install -m 0644 "${S}/box/${MACHINE}.svg" ${D}${datadir}/enigma2/logos/boxlogo.svg
	elif [ "${MACHINE}" = "h10" ]; then
		cp ${S}/box/zgemmah10*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "h11" ]; then
		cp ${S}/box/zgemmah11*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "h3" ]; then
		cp ${S}/box/zgemmah3*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "h5" ]; then
		cp ${S}/box/zgemmah5*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "h9" -o "${MACHINE}" = "h9se" ]; then
		cp ${S}/box/zgemmah9*.svg  ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "sf8008" ]; then
		cp ${S}/box/sf8008*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "sfx6008" ]; then
		cp ${S}/box/sfx60*.svg ${D}${datadir}/enigma2/logos/ || true
	elif [ "${MACHINE}" = "ustym4kpro" ]; then
		cp ${S}/box/ustym4k*.svg ${D}${datadir}/enigma2/logos/ || true
	fi
	chmod -f 644 "${D}${datadir}/enigma2/logos/*.svg" || true

    install -d ${D}${libdir}/enigma2/python/Components/Renderer
    cp -r ${S}/Renderer/* ${D}${libdir}/enigma2/python/Components/Renderer
    python3 -m compileall -o2 -b ${D}${libdir}/enigma2/python/Components/Renderer
}

pkg_postinst_ontarget_${PN} () {
#!/bin/sh
set -e

if [ -n "$D" ]; then
    $INTERCEPT_DIR/postinst_intercept delay_to_first_boot enigma2-boxlogos mlprefix=
    exit 0
fi

#
# enigma.info file, bail out if the file does not exist
#
INFOFILE=/usr/lib/enigma.info
if [ ! -f $INFOFILE ]; then
    exit 1
fi

# get the machine brand name for this image
MACHINEBUILD=`grep "machinebuild=" $INFOFILE | cut -d '=' -f 2`

# move the image if it exists
[ -f "${datadir}/enigma2/logos/$MACHINEBUILD.svg" ] && mv "${datadir}/enigma2/logos/$MACHINEBUILD.svg" "${datadir}/enigma2/logos/boxlogo.svg"

# remove all other box images
rm -f ${datadir}/enigma2/logos/zgemmah10*.svg
rm -f ${datadir}/enigma2/logos/zgemmah11*.svg
rm -f ${datadir}/enigma2/logos/zgemmah3*.svg
rm -f ${datadir}/enigma2/logos/zgemmah9*.svg
rm -f ${datadir}/enigma2/logos/sf8008*.svg
rm -f ${datadir}/enigma2/logos/sfx60*.svg
rm -f ${datadir}/enigma2/logos/ustym4k*.svg

exit 0
}
