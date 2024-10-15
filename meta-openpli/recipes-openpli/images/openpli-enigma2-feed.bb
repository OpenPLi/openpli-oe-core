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

MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""

OPTIONAL_PACKAGES ?= ""
OPTIONAL_BSP_PACKAGES ?= ""
OPTIONAL_BSP_ENIGMA2_PACKAGES ?= ""

# Get the kernel version for this image, we need it to build conditionally on kernel version
# NB: this only works in the feed, as the kernel needs to be build before the headers are available
export KERNEL_VERSION = "${@oe.utils.read_file('${PKGDATA_DIR}/kernel-depmod/kernel-abiversion')}"

# Out-of-tree wifi drivers, build conditionally based on kernel version
OPTIONAL_WIFI_PACKAGES = "\
	${@ 'kernel-module-rt5572sta' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.10') < 0) else '' } \
	${@ 'kernel-module-r8188eu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.12') < 0) else '' } \
	${@ 'kernel-module-rt3573sta' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.12') < 0) else '' } \
	${@ 'kernel-module-mt7601usta' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.2') < 0) else '' } \
	${@ 'kernel-module-8723a' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.5') < 0) else '' } \
	${@ 'kernel-module-8723bu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.6') < 0) else '' } \
	${@ 'kernel-module-8192eu' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.7') < 0) else '' } \
	${@ 'kernel-module-mt7610u' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.19') < 0) else '' } \
	\
	${@bb.utils.contains('MACHINE_ESSENTIAL_EXTRA_RDEPENDS', 'rtl8723bs', '', bb.utils.contains('MACHINE_ESSENTIAL_EXTRA_RDEPENDS', 'spycat-rtl8723bs', '', 'kernel-module-r8723bs' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '4.12') < 0) else '', d), d)} \
	\
	kernel-module-8812au \
	kernel-module-8814au \
	kernel-module-88x2bu \
	kernel-module-8189es \
	firmware-rtl8723bu \
	firmware-rtl8188eu  \
	firmware-rtl8192eu \
	firmware-mt7601u \
	"

#	** TODO **
#	rtl8723bt

OPTIONAL_PACKAGES += " \
	${@ 'wireguard-tools' if ("${KERNEL_VERSION}" and bb.utils.vercmp_string("${KERNEL_VERSION}", '3.14') >= 0) else '' } \
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
	dvb-apps \
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
	libsdl2 \
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
	p7zip \
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
	python-pyexecjs \
	python-beautifulsoup4 \
	python-futures \
	python-pycrypto \
	python-singledispatch \
	python-websocket \
	python-isodate \
	python-iso3166 \
	python-iso639 \
	python-youtube-dl \
	picocom \
	ppp \
	${@bb.utils.contains('TARGET_FPU', 'soft', '', 'rclone', d)} \
	rsync \
	rtorrent \
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
	unzip \
	usb-modeswitch \
	usb-modeswitch-data \
	v4l-utils \
	vim \
	wget \
	wscan \
	yafc \
	zeroconf \
	zerotier \
	zip \
	zsh \
	${OPTIONAL_BSP_PACKAGES} \
	${OPTIONAL_WIFI_PACKAGES} \
	"

OPTIONAL_ENIGMA2_PACKAGES = " \
	channelsettings-enigma2-meta \
	enigma2-pliplugins \
	enigma2-plugin-extensions-automatic-fullbackup \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-blurayplayer \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-fontinfo \
	enigma2-plugin-extensions-e2iplayer-deps \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-openmultiboot \
	enigma2-plugin-extensions-modifyplifullhd \
	enigma2-plugin-extensions-refreshbouquet \
	enigma2-plugin-extensions-sdgradio \
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
	enigma2-plugin-systemplugins-radiotimesxmltvemulator \
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
	enigma2-plugin-skins-glamouraurafhd \
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
	${@bb.utils.contains("MACHINE_FEATURES", "webkithbbtv", "enigma2-plugin-extensions-webkithbbtv", "", d)} \
	libcrypto-compat \
	dvb-usb-drivers-meta \
	cdtextinfo \
	meta-enigma2-dvdburn \
	${OPTIONAL_BSP_ENIGMA2_PACKAGES} \
	"

DEPENDS += "${OPTIONAL_PACKAGES} ${OPTIONAL_ENIGMA2_PACKAGES}"
