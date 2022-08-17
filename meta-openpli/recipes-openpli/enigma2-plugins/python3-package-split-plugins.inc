inherit ${PYTHON_PN}-dir

RDEPENDS_{PN}-src = "${PN}"

FILES_${PN}-src += " \
    ${libdir}/enigma2/python/Plugins/*/*/*.py \
    ${libdir}/enigma2/python/Plugins/*/*/*/*.py \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/*.py \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/*/*.py \
    ${libdir}/enigma2/python/Plugins/*/*/*/*/*/*/*.py \
    "

fakeroot do_compileall() {
    python3 -m compileall -b "${D}"
}

addtask compileall before do_package after do_install
