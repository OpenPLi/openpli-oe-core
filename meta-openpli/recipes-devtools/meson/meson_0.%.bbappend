# add dummy __pycache__ files to make happy rm later...
do_install:prepend() {
    mkdir -p ${D}${PYTHON_SITEPACKAGES_DIR}/mesonbuild/dependencies/__pycache__
    touch ${D}${PYTHON_SITEPACKAGES_DIR}/mesonbuild/dependencies/__pycache__/mpi.cpython
}
