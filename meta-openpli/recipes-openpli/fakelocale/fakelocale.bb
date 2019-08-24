SUMMARY = "LC_TIME locale support"
LICENSE = "GPLv3"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "OpenPli team"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS += "cross-localedef-native"

SRC_URI = "git://github.com/OpenPLi/fakelocale-locales.git \
	file://locale.alias \
	file://SYS_LC_MESSAGES \
"

S = "${WORKDIR}/git"

LOCALEDIR = "${libdir}/locale"
LOCALEDIR2 = "/usr/share/locale"

LANGUAGES = "ar_AE bg_BG ca_AD cs_CZ da_DK de_DE el_GR en_EN es_ES et_EE fa_IR fi_FI \
	fr_FR fy_NL he_IL hr_HR hu_HU id_ID is_IS it_IT lt_LT lv_LV nl_NL nb_NO nn_NO pl_PL pt_BR pt_PT \
	ru_RU sk_SK sl_SI sr_YU sv_SE th_TH tr_TR uk_UA vi_VN zh_CN zh_HK"

RPROVIDES_${PN} = "virtual-locale-ar virtual-locale-bg virtual-locale-ca virtual-locale-cs \
	virtual-locale-da virtual-locale-de virtual-locale-el virtual-locale-en virtual-locale-es \
	virtual-locale-et virtual-locale-fa virtual-locale-fi virtual-locale-fr virtual-locale-fy \
	virtual-locale-he virtual-locale-hr virtual-locale-hu virtual-locale-id virtual-locale-is \
	virtual-locale-it virtual-locale-lt virtual-locale-lv virtual-locale-nb virtual-locale-nl \
	virtual-locale-nn virtual-locale-pl virtual-locale-pt virtual-locale-ru virtual-locale-sk \
	virtual-locale-sl virtual-locale-sr virtual-locale-sv virtual-locale-th virtual-locale-tr \
	virtual-locale-uk virtual-locale-vi virtual-locale-zh"

do_compile() {
    install -d ${S}/output
    cd ${S}/locales
    for lang in ${LANGUAGES}; do
		cross-localedef --no-archive -i ${S}/locales/${lang} -f ${S}/locales/UTF-8 ${S}/output/${lang}
	done
}

do_install() {
	install -d ${D}${LOCALEDIR2}
	install ${WORKDIR}/locale.alias ${D}${LOCALEDIR2}

	install -d ${D}${LOCALEDIR}/fake/LC_MESSAGES
	install ${WORKDIR}/SYS_LC_MESSAGES ${D}${LOCALEDIR}/fake/LC_MESSAGES/

	for lang in ${LANGUAGES}; do
		install -d ${D}${LOCALEDIR}/${lang}
		cp ${S}/output/${lang}/LC_TIME ${D}${LOCALEDIR}/${lang}
		ln -s ../fake/LC_MESSAGES ${D}${LOCALEDIR}/${lang}/LC_MESSAGES
	done
}

FILES_${PN} = "${LOCALEDIR} ${LOCALEDIR2}"
