FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://importlib.patch"

ALTERNATIVE_${PN}-core = "python python_config"
ALTERNATIVE_LINK_NAME[python] = "${bindir}/python"
ALTERNATIVE_LINK_NAME[python_config] = "${bindir}/python-config"
ALTERNATIVE_TARGET[python] = "${bindir}/python3"
ALTERNATIVE_TARGET[python_config] = "${bindir}/python3-config"

# add dummy __pycache__ files to make happy rm later...
do_install_prepend() {
    mkdir -p ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__
    touch ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__/test_range.cpython
    touch ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__/test_xml_etree.cpython
}
