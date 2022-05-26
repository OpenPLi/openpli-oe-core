# add dummy __pycache__ files to make happy rm later...
do_install_prepend() {
    mkdir -p ${D}${libdir}/python${PYTHON_MAJMIN}/site-packages/mesonbuild/dependencies/__pycache__
    touch ${D}${libdir}/python${PYTHON_MAJMIN}/site-packages/mesonbuild/dependencies/__pycache__/mpi.cpython
}
