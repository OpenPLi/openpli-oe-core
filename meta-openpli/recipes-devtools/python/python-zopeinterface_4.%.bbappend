PACKAGES =+ "${PN}-src"
RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*.py"
FILES_${PN}-src += "${PYTHON_SITEPACKAGES_DIR}/zope/interface/*/*.py"

# apparently missing in python-zopeinterface_3.5.1.bb:
PACKAGES =+ "${PN}-tests"

# some txt files which should go into -doc
FILES_${PN}-doc += "${PYTHON_SITEPACKAGES_DIR}/*-info"
