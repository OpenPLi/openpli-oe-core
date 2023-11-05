# fuse claims to depend on util-linux-mount, but that appears to be untrue.
RDEPENDS_${PN}:remove = "util-linux-mount"
