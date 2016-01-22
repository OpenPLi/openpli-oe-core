# Creates the "feed", packages not required for the image
# but that should be built for the feed so that other
# components may use them and install on demand.

# Trick: We want to create the package index, and we don't actually
# package anything, so we "inherit" the package indexer recipe.
require recipes-core/meta/package-index.bb

# We have a GPLv2 license for this recipe...
require conf/license/openpli-gplv2.inc

# Depend on the image, so that it gets build
DEPENDS = "openpli-enigma2-image"

OPTIONAL_PACKAGES_BROKEN = "samba"
OPTIONAL_PACKAGES ?= ""
OPTIONAL_BSP_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	autofs \
	autossh \
	ctorrent \
	cups \
	djmount \
	dvbsnoop \
	dvdfs \
	evtest \
	exfat-utils \
	fuse-exfat \
	gdb \
	hddtemp \
	hdparm \
	inadyn-mt \
	iperf \
	joe \
	mc \
	minisatip \
	mtd-utils \
	nano \
	net-tools \
	ntfs-3g \
	ntp \
	ofgwrite \
	openresolv \
	openssh \
	openvpn \
	parted \
	procps \
	pyload \
	python-ntplib \
	python-youtube-dl \
	python-requests \
	python-mechanize \
	rsync \
	rtorrent \
	sabnzbd \
	satipclient \
	sshpass \
	smartmontools \
	smbnetfs \
	strace \
	tcpdump \
	transmission \
	udpxy \
	vim \
	xfsprogs \
	zeroconf \
	${OPTIONAL_BSP_PACKAGES} \
	"

OPTIONAL_BSP_ENIGMA2_PACKAGES ?= ""
ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	enigma2-pliplugins \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-xmltvimport \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-modifyplifullhd \
	enigma2-plugin-security-firewall \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-hd-fullnight \
	enigma2-plugins \
	enigma2-skins \
	picons-enigma2-meta \
	softcams-enigma2-meta \
	packagegroup-openplugins \
	${@base_contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-satscan" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "dreambox", "", "enigma2-plugin-extensions-backupsuite", d)} \
	dvb-usb-drivers-meta \
	cdtextinfo \
	meta-enigma2-dvdburn \
	${OPTIONAL_BSP_ENIGMA2_PACKAGES} \
	"

DEPENDS += "${OPTIONAL_PACKAGES} ${ENIGMA2_OPTIONAL}"
