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
	astra-sm \
	autofs \
	autossh \
	ctorrent \
	cups \
	diffutils \
	djmount \
	dosfstools \
	dvb-apps \
	dvblast \
	dvbsnoop \
	dvdfs \
	evtest \
	exfat-utils \
	exteplayer3 \
	fuse-exfat \
	gdb \
	gstplayer \
	hddtemp \
	hdparm \
	inadyn-mt \
	inetutils \
	iperf \
	iproute2 \
	iputils \
	joe \
	less \
	libbluray \
	libudfread \
	mc \
	minisatip \
	mtd-utils \
	mtools \
	nano \
	net-tools \
	ntfs-3g \
	ntp \
	ofgwrite \
	openresolv \
	openssh \
	openvpn \
	openmultiboot \
	parted \
	procps \
	pyload \
	python-ipaddress \
	python-ntplib \
	python-pip \
	python-requests \
	python-mechanize \
	python-lxml \
	python-js2py \
	picocom \
	ppp \
	rsync \
	rtorrent \
	sabnzbd \
	satipclient \
	screen \
	sshpass \
	smartmontools \
	smbnetfs \
	strace \
	tcpdump \
	tmux \
	transmission \
	udpxy \
	usb-modeswitch \
	usb-modeswitch-data \
	v4l-utils \
	vim \
	wget \
	wscan \
	zeroconf \
	zip \
	zsh \
	${OPTIONAL_BSP_PACKAGES} \
	"

OPTIONAL_BSP_ENIGMA2_PACKAGES ?= ""
ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	enigma2-pliplugins \
	enigma2-plugin-extensions-automatic-fullbackup \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-blurayplayer \
	enigma2-plugin-extensions-xmltvimport \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-openmultiboot \
	enigma2-plugin-extensions-modifyplifullhd \
	enigma2-plugin-extensions-refreshbouquet \
	enigma2-plugin-extensions-fallbackreceiver \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-signalfinder \
	enigma2-plugin-systemplugins-extnumberzap \
	enigma2-plugin-systemplugins-serviceapp \
	enigma2-plugin-systemplugins-plexdvrapi \
	enigma2-plugin-extensions-historyzapselector \
	enigma2-plugin-extensions-lcd4linux \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-xmodem \
	enigma2-plugin-extensions-vcs \
	enigma2-plugin-security-firewall \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-hd-fullnight \
	enigma2-plugins \
	enigma2-skins \
	picons-enigma2-meta \
	softcams-enigma2-meta \
	packagegroup-openplugins \
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-satscan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "", "enigma2-plugin-extensions-backupsuite", d)} \
	${@bb.utils.contains('EXTRA_IMAGEDEPENDS', 'vuplus-tuner-turbo', 'enigma2-plugin-drivers-dvb-usb-turbo', '', d)} \
	dvb-usb-drivers-meta \
	cdtextinfo \
	meta-enigma2-dvdburn \
	${OPTIONAL_BSP_ENIGMA2_PACKAGES} \
	"

DEPENDS += "${OPTIONAL_PACKAGES} ${ENIGMA2_OPTIONAL}"
