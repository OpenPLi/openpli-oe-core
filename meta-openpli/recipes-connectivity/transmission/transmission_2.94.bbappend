SRC_URI += "\
	file://configure-kill-intl-check.patch \
	file://configure-allow-local-network.patch \
	file://configuration-default-download-dir.patch \
	file://0001-transmission-build-against-openssl-1.1.0.patch \
	"

OE_EXTRACONF = "\
	--disable-gtk --without-gtk \
	--disable-nls --without-nls \
	--disable-cli \
	--disable-mac \
	--disable-wx \
	--disable-beos \
	--enable-lightweight \
	--enable-daemon \
	CPPFLAGS=-DTR_EMBEDDED \
	"
