# Creates the "feed", packages not required for the image
# but that should be built for the feed so that other
# components may use them and install on demand.

# Trick: We want to create the package index, and we don't actually
# package anything, so we "inherit" the package indexer recipe.
require recipes-core/meta/package-index.bb

# We have a GPLv2 license for this recipe...
require conf/license/openpli-gplv2.inc

MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""

# Depend on the image, so that it gets build
DEPENDS = "openpli-enigma2-image"

OPTIONAL_PACKAGES_BROKEN = "samba"
OPTIONAL_PACKAGES ?= ""
OPTIONAL_BSP_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	astra-sm \
	autofs \
	autossh \
	ccid \
	ctorrent \
	cups \
	davfs2 \
	diffutils \
	djmount \
	dosfstools \
	dvblast \
	dvbsnoop \
	dvdfs \
	edid-decode \
	evtest \
	exfat-utils \
	exteplayer3 \
	gdb \
	grep \
	gstplayer \
	hddtemp \
	hdparm \
	inadyn-mt \
	inetutils \
	iperf3 \
	iproute2 \
	iputils \
	joe \
	less \
	libbluray \
	libudfread \
	mc \
	mediainfo \
	pv \
	minisatip \
	mtd-utils \
	mtools \
	nano \
	net-tools \
	${@bb.utils.contains('TARGET_FPU', 'soft', '', 'nodejs', d)} \
	ntfs-3g \
	ntp \
	ofgwrite \
	openresolv \
	openssh \
	openvpn \
	openmultiboot \
	parted \
	procps \
	python-ipaddress \
	python-ntplib \
	python-pip \
	python-requests \
	python-mechanize \
	python-lxml \
	python-js2py \
	python-pyexecjs \
	python-beautifulsoup4 \
	picocom \
	ppp \
	rsync \
	rtorrent \
	rtl8723a \
	${@bb.utils.contains('MACHINE_ESSENTIAL_EXTRA_RDEPENDS', 'spycat-rtl8723bs', '', 'rtl8723bs', d)} \
	${@bb.utils.contains('MACHINE', 'dm8000', '', 'rtl8812au', d)} \
	rtl8814au \
	rtl8822bu \
	${@bb.utils.contains_any('MACHINE', 'dm8000 et5x00 et6x00 et9x00 vuduo vusolo vuuno vuultimo osmio4k', '', 'rtl8189es', d)} \
	${@bb.utils.contains('MACHINE', 'osmio4k', '', 'rtl8192eu', d)} \
	sabnzbd \
	satipclient \
	screen \
	sed \
	sshpass \
	smartmontools \
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
	yafc \
	zeroconf \
	zip \
	zsh \
	${OPTIONAL_BSP_PACKAGES} \
	"

# dvb-apps was skipped: Recipe is blacklisted: Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130603/
# smbnetfs was skipped: Recipe is blacklisted: Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/132827/

OPTIONAL_BSP_ENIGMA2_PACKAGES ?= ""
ENIGMA2_OPTIONAL = " \
	channelsettings-enigma2-meta \
	enigma2-pliplugins \
	enigma2-plugin-extensions-automatic-fullbackup \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-blurayplayer \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-fontinfo \
	enigma2-plugin-extensions-iptvplayer-deps \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-openmultiboot \
	enigma2-plugin-extensions-modifyplifullhd \
	enigma2-plugin-extensions-refreshbouquet \
	enigma2-plugin-extensions-managerautofs \
	enigma2-plugin-extensions-hdmitest \
	enigma2-plugin-extensions-moviemanager \
	enigma2-plugin-systemplugins-crossepg \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-signalfinder \
	enigma2-plugin-systemplugins-extnumberzap \
	enigma2-plugin-systemplugins-serviceapp \
	enigma2-plugin-systemplugins-hrtunerproxy \
	enigma2-plugin-systemplugins-quadpip \
	enigma2-plugin-systemplugins-extrafancontrol \
	enigma2-plugin-extensions-historyzapselector \
	enigma2-plugin-extensions-lcd4linux \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-xmodem \
	enigma2-plugin-extensions-vcs \
	enigma2-plugin-security-firewall \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-hd-fullnight \
	enigma2-plugin-skins-simple-gray-hd \
	enigma2-plugin-skins-pd1loi-hd-night \
	enigma2-plugins \
	enigma2-skins \
	softcams-enigma2-meta \
	packagegroup-openplugins \
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-satscan" , "", d)} \
	enigma2-plugin-extensions-backupsuite \
	${@bb.utils.contains('EXTRA_IMAGEDEPENDS', 'vuplus-tuner-turbo', 'enigma2-plugin-drivers-dvb-usb-turbo', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'kodi', 'enigma2-plugin-extensions-kodi', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'kodi', 'enigma2-plugin-extensions-kodi', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'qtplugins', 'enigma2-plugin-extensions-qthbbtv enigma2-plugin-extensions-qtstalker', '', d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "transcoding", "streamproxy", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "chromium", "enigma2-plugin-extensions-chromium libcrypto-compat", "", d)} \
	dvb-usb-drivers-meta \
	cdtextinfo \
	meta-enigma2-dvdburn \
	${OPTIONAL_BSP_ENIGMA2_PACKAGES} \
	"

DEPENDS += "${OPTIONAL_PACKAGES} ${ENIGMA2_OPTIONAL}"
