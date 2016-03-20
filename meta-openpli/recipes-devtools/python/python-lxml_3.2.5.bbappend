DISTUTILS_BUILD_ARGS = " \
                     --with-xslt-config='pkg-config libxslt' \
                     --with-xml2-config='pkg-config libxml-2.0' \
"

DISTUTILS_INSTALL_ARGS = " \
                     --with-xslt-config='pkg-config libxslt' \
                     --with-xml2-config='pkg-config libxml-2.0' \
                     --root=${D} \
                     --install-lib=${PYTHON_SITEPACKAGES_DIR} \
"

do_configure_prepend() {
    sed -i -e 's/--version/--modversion/' ${B}/setupinfo.py
}

do_install_append() {
	rm -fR ${D}/${PYTHON_SITEPACKAGES_DIR}/*.egg-info
}
