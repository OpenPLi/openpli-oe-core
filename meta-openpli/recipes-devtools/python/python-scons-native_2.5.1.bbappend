do_install_append() {
    sed -i 's#/usr/bin/env python#/usr/bin/env python2#g' ${D}${bindir}/scons*
}
