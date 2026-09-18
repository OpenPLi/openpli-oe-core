require conf/license/openpli-gplv2.inc

inherit image

WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-rtl8192cu \
	firmware-rtl8188eu \
	firmware-zd1211 \
	\
	kernel-module-8192eu \
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-rtl8192cu \
	kernel-module-zd1211rw \
	"

WIFI_BSP_DRIVERS ?= " "

ENIGMA2_PLUGINS += " \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-pictureplayer \
	\
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-osdpositionsetup \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "wlan", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'tempfancontrol', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'inibt', 'enigma2-plugin-extensions-btdevicesmanager', '', d)} \
	"

DEPENDS += " \
	enigma2 \
	enigma-info \
	package-index \
	zip-native \
	"

IMAGE_INSTALL += " \
	3rd-party-feed-configs \
	aio-grab \
	avahi-daemon \
	boximages-owif \
	busybox-cron \
	ca-certificates \
	chrony \
	chronyc \
	cifs-utils \
	distro-feed-configs \
	dropbear \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	enigma2 \
	enigma2-plugin-skins-e2darkos \
	fakelocale \
	fuse-exfat \
	hdparm \
	kernel-params \
	libavahi-client \
	modutils-loadscript \
	nfs-utils \
	nfs-utils-client \
	${@bb.utils.contains("MACHINE", "dm8000", "", "ofgwrite", d)} \
	openpli-bootlogo \
	enigma2-boxlogos \
	openresolv \
	openssh-sftp-server \
	opkg \
	packagegroup-base \
	packagegroup-core-boot \
	parted \
	perl \
	python3-ipaddress  \
	python3-netifaces \
	python3-pysmb \
	python3-requests \
	sdparm \
	settings-autorestore \
	tuxbox-common \
	tzdata \
	util-linux-mount \
	volatile-media \
	vsftpd \
	\
	${ROOTFS_PKGMANAGE} \
	\
	${ENIGMA2_PLUGINS} \
	\
	${WIFI_DRIVERS} \
	${WIFI_BSP_DRIVERS} \
	\
	${@bb.utils.contains_any('MACHINE', 'vuuno vuduo vuultimo vusolo vusolo2 vuduo2 vusolose vuzero vuuno4k vuuno4kse vuzero4k vuultimo4k vusolo4k vuduo4k vuduo4kse vuduo4klite', 'vuplus-tuner-turbo', '', d)} \
	${@bb.utils.contains_any('MACHINE', 'vusolo2 vuduo2 vusolose vuzero vuuno4k vuuno4kse vuzero4k vuultimo4k vusolo4k vuduo4k vuduo4kse vuduo4klite', 'vuplus-tuner-turbo2', '', d)} \
	${@bb.utils.contains_any('MACHINE', 'vuuno4kse vuultimo4k vuduo4k vuduo4kse', 'vuplus-hdmi-in-helper', '', d)} \
	\
	${@bb.utils.contains_any('MACHINE', 'vuuno4k vuuno4kse vusolo4k vuultimo4k vuduo4k vuduo4kse', 'enigma2-plugin-systemplugins-fastchannelchange', '', d)} \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "chromium", "enigma2-plugin-extensions-chromium", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "transcoding", "enigma2-plugin-systemplugins-transcodingsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "streamproxy", "streamproxy", "", d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'ctrlrc', "enigma2-plugin-systemplugins-remotecontrolcode", "", d)} \
	\
	${@bb.utils.contains('MACHINE_FEATURES', 'dvd', 'cdtextinfo', '', d)} \
	"

IMAGE_INSTALL:append:libc-glibc = " glibc-binary-localedata-en-gb"

export IMAGE_BASENAME = "openpli"
IMAGE_LINGUAS = ""
IMAGE_FEATURES += "package-management"

# Remove the mysterious var/lib/opkg/lists that appears to be the result
# of the installer that populates the rootfs. I wanted to call this
# rootfs:remove_opkg_leftovers but that fails to parse.
removeopkgleftovers() {
	rm -r ${IMAGE_ROOTFS}/var/lib/opkg/lists
}

# Some features in image.bbclass we do NOT want, so override them
# to be empty. We want to log in as root, but NOT via SSH. So we want
# to live without debug-tweaks...
zap_root_password () {
	true
}

ssh_allow_empty_password () {
	true
}

license_create_manifest() {
}

ROOTFS_POSTPROCESS_COMMAND += "removeopkgleftovers; "

export IMAGE_BASENAME = "openpli-enigma2"
