PACKAGECONFIG = "openssl pcre zlib \
                ${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
