FILESEXTRAPATHS_prepend := "${THISDIR}/opkg:"

SRC_URI += " \
	file://0001-reuse-the-installed_files-list-when-possible.patch \
	file://0002-opkg-symlink-to-directory-workarounds.patch \
	file://0003-wget-retry.patch \
	file://0004-backport-double-free.patch \
	"
