# fuse claims to depend on util-linux-mount, but that appears to be untrue.
RDEPENDS:${PN}:remove = "util-linux-mount"
