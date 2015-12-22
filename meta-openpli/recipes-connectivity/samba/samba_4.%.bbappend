
# Remove acl, cups etc. support.
PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINITTYPE}', '', d)} \
                 ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                 ${@base_contains('DISTRO_FEATURES', 'zeroconf', 'zeroconf', '', d)} \
                 aio \
"

EXTRA_OECONF += "--without-cluster-support --without-profiling-data"

EXTRA_OECONF_remove = "--with-cluster-support --with-profiling-data"

# Fix typos
PACKAGECONFIG[acl] = "--with-acl-support,--without-acl-support,acl"
PACKAGECONFIG[aio] = "--with-aio-support,--without-aio-support,libaio"
