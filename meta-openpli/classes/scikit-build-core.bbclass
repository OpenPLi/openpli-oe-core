DEPENDS += "scikit-build-core-native ninja-native"

inherit python_pep517 python3native python3-dir setuptools3-base

do_compile:prepend:class-target() {
        export _PYTHON_SYSCONFIGDATA_NAME="_sysconfigdata"
        export PYTHONPATH=${STAGING_LIBDIR}/python-sysconfigdata
        export PATH=${STAGING_EXECPREFIXDIR}/python-target-config/:$PATH
}

