# Qt packages are machine specific
QT_PACKAGES_ARCH = "${MACHINE_ARCH}"

PACKAGECONFIG += "gles2 eglfs gif linuxfb glib openssl directfb"
