FILESEXTRAPATHS_prepend := "${THISDIR}/python-twisted:"

SRC_URI += " \
    file://0001-Revert-Prevent-CRLF-injections-described-in-CVE-2019.patch \
    "

FILES_${PN}-src += " \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*/*.py \
"

FILES_${PN}-dbg += " \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*.egg-info \
    ${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/test \
"

RDEPENDS_${PN}-core += "${PYTHON_PN}-service-identity"
