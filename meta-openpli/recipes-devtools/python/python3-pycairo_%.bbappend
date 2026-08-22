include ${PYTHON_PN}-package-split.inc

# hack, workaround for an odd meson install issue, installing
# the package into ./image/usr/usr instead of ./image/usr
do_install_append() {
    cp -r ${WORKDIR}/image/usr/usr/* ${WORKDIR}/image/usr
    rm -rf ${WORKDIR}/image/usr/usr
}
