PR = "r2"

inherit setuptools

PACKAGES =+ "${PN}-src"
RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = " \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*.py \
	"
