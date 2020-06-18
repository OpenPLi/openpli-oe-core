FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

EXTRA_OECONF += "\
	--with-unicode-backend=glib \
	"
