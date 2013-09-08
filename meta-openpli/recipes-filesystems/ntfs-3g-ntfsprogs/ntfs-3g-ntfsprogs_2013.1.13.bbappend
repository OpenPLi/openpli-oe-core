# Remove unneeded util-linux-mount from RDEPENDS

RDEPENDS_ntfs-3g := "${@bb.data.getVar('RDEPENDS_ntfs-3g',d,1).replace('util-linux-mount', '')}"
