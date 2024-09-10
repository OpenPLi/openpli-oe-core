# no longer available via mercurial
# see https://github.com/openembedded/meta-openembedded/commit/3dd74712ed7b5a281622af576cf8ffb6c68e9cb0

unset SRCREV

SRC_URI = "https://www.linuxtv.org/hg/dvb-apps/archive/3d43b280298c.tar.bz2;downloadfilename=${BPN}-3d43b280298c.tar.bz2 \
          file://dvb-scan-table \
          file://0001-Fix-generate-keynames.patch \
          file://0003-handle-static-shared-only-build.patch \
          file://0004-Makefile-remove-test.patch \
          file://0005-libucsi-optimization-removal.patch \
          file://0006-CA_SET_PID.patch \
          file://0001-dvbdate-Remove-Obsoleted-stime-API-calls.patch \
          "
SRC_URI[sha256sum] = "f39e2f0ebed7e32bce83522062ad4d414f67fccd5df1b647618524497e15e057"
S = "${WORKDIR}/${BPN}-3d43b280298c"
