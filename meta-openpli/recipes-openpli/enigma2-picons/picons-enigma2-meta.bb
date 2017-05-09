# meta package for enigma2 picon sets

require conf/license/openpli-gplv2.inc

inherit allarch
ALLOW_EMPTY_${PN} = "1"

DEPENDS = " \
		enigma2-plugin-picons-ziggo.metal-look-100x60 \
		enigma2-plugin-picons-ziggo.metal-look-220x132 \
		enigma2-plugin-picons-ziggo.metal-stamp-100x60 \
		enigma2-plugin-picons-ziggo.metal-stamp-220x132 \
		enigma2-plugin-picons-ziggo.black-white-220x132 \
		enigma2-plugin-picons-ziggo.reflection-black-220x132 \
		enigma2-plugin-picons-ziggo.transparent-white-220x132 \
		"
