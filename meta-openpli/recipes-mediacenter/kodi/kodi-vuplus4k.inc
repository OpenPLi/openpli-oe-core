SRC_URI_append = " file://EGLNativeTypeV3D-vuplus4k.patch file://Makefile-vuplus4k.patch"
EXTRA_OECONF += "--with-gpu=v3dvuplus"

DEPENDS += "kodiegl"
RDEPENDS_${PN} += "kodiegl"
