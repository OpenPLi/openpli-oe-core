FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRCREV_localedef = "dfb4afe551c6c6e94f9cc85417bd1f582168c843"

SRC_URI_remove = "file://0023-eglibc-Install-PIC-archives.patch"

SRC_URI_append = "file://0026-add-getopt_core-for-glibc2.25.patch"

