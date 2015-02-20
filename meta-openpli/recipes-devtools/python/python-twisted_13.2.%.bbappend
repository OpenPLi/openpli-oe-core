PR = "r2"

PACKAGES =+ "\
	${PN}-src \
	${PN}-bin \
"

RDEPENDS_{PN} += "${PN}-bin"
RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = " \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/twisted/*/*/*.py \
	"

RDEPENDS_${PN}-core = "python-core python-zopeinterface python-contextlib"
RDEPENDS_${PN}-test = "${PN}"
RDEPENDS_${PN}-conch = "${PN}-core ${PN}-protocols"
RDEPENDS_${PN}-lore = "${PN}-core"
RDEPENDS_${PN}-mail = "${PN}-core ${PN}-protocols"
RDEPENDS_${PN}-names = "${PN}-core"
RDEPENDS_${PN}-news = "${PN}-core ${PN}-protocols"
RDEPENDS_${PN}-runner = "${PN}-core ${PN}-protocols"
RDEPENDS_${PN}-web += "${PN}-core ${PN}-protocols"
RDEPENDS_${PN}-words += "${PN}-core"
RDEPENDS_${PN}-flow += "${PN}-core"
RDEPENDS_${PN}-pair += "${PN}-core"
RDEPENDS_${PN}-dbg = "${PN}"
