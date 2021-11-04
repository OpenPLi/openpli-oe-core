SUMMARY = "Secure Socket Layer - Backward Compatibility package"
DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "http://www.openssl.org/"
BUGTRACKER = "http://www.openssl.org/news/vulnerabilities.html"
SECTION = "libs/network"

# "openssl | SSLeay" dual license
LICENSE = "openssl"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f475368924827d06d4b416111c8bdb77"

PV="1.0.2u"

DEPENDS = "hostperl-runtime-native"
DEPENDS_append_class-target = " openssl-native"

SRC_URI = "http://www.openssl.org/source/openssl-${PV}.tar.gz \
           file://run-ptest \
           file://openssl-c_rehash.sh \
           file://configure-targets.patch \
           file://shared-libs.patch \
           file://oe-ldflags.patch \
           file://engines-install-in-libdir-ssl.patch \
           file://debian1.0.2/block_diginotar.patch \
           file://debian1.0.2/block_digicert_malaysia.patch \
           file://debian/c_rehash-compat.patch \
           file://debian/debian-targets.patch \
           file://debian/man-dir.patch \
           file://debian/man-section.patch \
           file://debian/no-rpath.patch \
           file://debian/no-symbolic.patch \
           file://debian/pic.patch \
           file://debian1.0.2/version-script.patch \
           file://debian1.0.2/soname.patch \
           file://openssl_fix_for_x32.patch \
           file://openssl-fix-des.pod-error.patch \
           file://Makefiles-ptest.patch \
           file://ptest-deps.patch \
           file://ptest_makefile_deps.patch \
           file://configure-musl-target.patch \
           file://parallel.patch \
           file://Use-SHA256-not-MD5-as-default-digest.patch \
           file://0001-Fix-build-with-clang-using-external-assembler.patch \
           file://0001-openssl-force-soft-link-to-avoid-rare-race.patch \
           file://0001-allow-manpages-to-be-disabled.patch \
           file://0001-Fix-BN_LLONG-breakage.patch \
           file://0001-Fix-DES_LONG-breakage.patch \
           file://fix_openssl_100_version.patch \
           file://fix_openssl_100_version_jethro.patch \
           "

SRC_URI_append_class-target = " \
           file://reproducible-cflags.patch \
           file://reproducible-mkbuildinf.patch \
           "

SRC_URI_append_class-nativesdk = " \
           file://environment.d-openssl.sh \
           "

SRC_URI[md5sum] = "cdc2638f789ecc2db2c91488265686c1"
SRC_URI[sha256sum] = "ecd0c6ffb493dd06707d38b14bb4d8c2288bb7033735606569d8f90f89669d16"

S = "${WORKDIR}/openssl-${PV}"

UPSTREAM_CHECK_REGEX = "openssl-(?P<pver>1\.0.+)\.tar"

inherit pkgconfig siteinfo lib_package multilib_header ptest manpages

PACKAGECONFIG ?= "cryptodev-linux"
PACKAGECONFIG_class-native = ""
PACKAGECONFIG_class-nativesdk = ""

PACKAGECONFIG[disable-weak-ciphers] = "no-des no-ec no-ecdh no-ecdsa no-md2 no-mdc2,,,"
PACKAGECONFIG[cryptodev-linux] = "-DHAVE_CRYPTODEV -DUSE_CRYPTODEV_DIGESTS,,cryptodev-linux"
PACKAGECONFIG[manpages] = ",,,"
PACKAGECONFIG[perl] = ",,,"

# Remove this to enable SSLv3. SSLv3 is defaulted to disabled due to the POODLE
# vulnerability
EXTRA_OECONF = "no-ssl3"

EXTRA_OEMAKE = "${@bb.utils.contains('PACKAGECONFIG', 'manpages', '', 'OE_DISABLE_MANPAGES=1', d)}"

export OE_LDFLAGS = "${LDFLAGS}"

TERMIO ?= "-DTERMIO"
TERMIO_libc-musl = "-DTERMIOS"
EXTRA_OECONF_append_libc-musl_powerpc64 = " no-asm"

CFLAG = "${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', '-DL_ENDIAN', '-DB_ENDIAN', d)} \
         ${TERMIO} ${CFLAGS} -Wall"

# Avoid binaries being marked as requiring an executable stack since they don't
# (and it causes issues with SELinux)
CFLAG += "-Wa,--noexecstack"

CFLAG_append_class-native = " -fPIC"

do_configure () {
	# The crypto_use_bigint patch means that perl's bignum module needs to be
	# installed, but some distributions (for example Fedora 23) don't ship it by
	# default.  As the resulting error is very misleading check for bignum before
	# building.
	if ! perl -Mbigint -e true; then
		bbfatal "The perl module 'bignum' was not found but this is required to build openssl.  Please install this module (often packaged as perl-bignum) and re-run bitbake."
	fi

	ln -sf apps/openssl.pod crypto/crypto.pod ssl/ssl.pod doc/

	os=${HOST_OS}
	case $os in
	linux-gnueabi |\
	linux-gnuspe |\
	linux-musleabi |\
	linux-muslspe |\
	linux-musl )
		os=linux
		;;
	*)
		;;
	esac
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arm)
		target=linux-armv4
		;;
	linux-armeb)
		target=linux-elf-armeb
		;;
	linux-aarch64*)
		target=linux-aarch64
		;;
	linux-sh3)
		target=debian-sh3
		;;
	linux-sh4)
		target=debian-sh4
		;;
	linux-i486)
		target=debian-i386-i486
		;;
	linux-i586 | linux-viac3)
		target=debian-i386-i586
		;;
	linux-i686)
		target=debian-i386-i686/cmov
		;;
	linux-gnux32-x86_64 | linux-muslx32-x86_64 )
		target=linux-x32
		;;
	linux-gnu64-x86_64)
		target=linux-x86_64
		;;
	linux-gnun32-mips*el)
		target=debian-mipsn32el
		;;
	linux-gnun32-mips*)
		target=debian-mipsn32
		;;
	linux-mips*64*el)
		target=debian-mips64el
		;;
	linux-mips*64*)
		target=debian-mips64
		;;
	linux-mips*el)
		target=debian-mipsel
		;;
	linux-mips*)
		target=debian-mips
		;;
	linux-microblaze* | linux-nios2* | linux-gnu*ilp32** | linux-arc*)
		target=linux-generic32
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	linux-powerpc64)
		target=linux-ppc64
		;;
	linux-riscv32)
		target=linux-generic32
		;;
	linux-riscv64)
		target=linux-generic64
		;;
	linux-sparc | linux-supersparc)
		target=linux-sparcv8
		;;
	esac

	# inject machine-specific flags
	sed -i -e "s|^\(\"$target\",\s*\"[^:]\+\):\([^:]\+\)|\1:${CFLAG}|g" Configure

	useprefix=${prefix}
	if [ "x$useprefix" = "x" ]; then
		useprefix=/
	fi
	libdirleaf="$( echo "${libdir}" | sed "s:^$useprefix/*::" )"
	perl ./Configure ${EXTRA_OECONF} ${PACKAGECONFIG_CONFARGS} shared --prefix=$useprefix --openssldir=${libdir}/ssl --libdir=$libdirleaf $target
}

do_compile () {
	oe_runmake depend
	oe_runmake
}

do_compile_class-target () {
	sed -i 's/\((OPENSSL=\)".*"/\1"openssl"/' Makefile
	oe_runmake depend
	cc_sanitized=$(echo "${CC} ${CFLAG}" | sed -e 's,--sysroot=${STAGING_DIR_TARGET},,g' -e 's|${DEBUG_PREFIX_MAP}||g' -e 's/[ \t]\+/ /g')
	oe_runmake CC_INFO="$cc_sanitized"
}

do_compile_ptest () {
	oe_runmake buildtest
}

do_install () {
	# Create ${D}/${prefix} to fix parallel issues
	mkdir -p ${D}/${prefix}/

	oe_runmake INSTALL_PREFIX="${D}" MANDIR="${mandir}" install

	oe_libinstall -so libcrypto ${D}${libdir}
	oe_libinstall -so libssl ${D}${libdir}

	rm -rf ${D}${bindir}
	rm -rf ${D}${libdir}/ssl
	rm -rf ${D}${libdir}/engines

	install -d ${D}${libdir}
	ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.0.9.7
	ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.0.9.7
	ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.0.9.8
	ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.0.9.8
	ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.1.0.0
	ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.1.0.0
}

FILES_${PN} =+ "${libdir}/ssl/*"

BBCLASSEXTEND = "native nativesdk"
PACKAGE_PREPROCESS_FUNCS += "openssl_package_preprocess"

RPROVIDES_${PN} ="libcrypto-compat"

PROVIDES += "libcrypto-compat"

openssl_package_preprocess () {
        for file in `find ${PKGD} -name *.h -o -name *.pc -o -name *.so`; do
                rm $file
        done
}
