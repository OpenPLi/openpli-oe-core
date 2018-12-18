FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_remove = "file://0023-eglibc-Install-PIC-archives.patch"

SRC_URI_append = "file://0026-fix__locale_t-redefinition-on-newer-host-glibc.patch"

