require conf/license/openpli-gplv2.inc

inherit image

IMAGE_INSTALL = " \
	${ROOTFS_PKGMANAGE} \
	3rd-party-feed-configs \
	avahi-daemon \
	cifs \
	distro-feed-configs \
	dropbear \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	fakelocale \
	kernel-params \
	modutils-loadscript \
	nfs-utils-client \
	openpli-bootlogo \
	opkg \
	packagegroup-base \
	packagegroup-core-boot \
	sambaserver \
	sdparm \
	tuxbox-common \
	tuxbox-links \
	tzdata \
	util-linux-sfdisk \
	volatile-media \
	vsftpd \
	"

OPTIONAL_PACKAGES_BROKEN = "samba"
OPTIONAL_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	autofs \
	autossh \
	ctorrent \
	cups \
	djmount \
	dvbsnoop \
	dvdfs \
	evtest \
	gdb \
	hddtemp \
	hdparm \
	inadyn-mt \
	iperf \
	joe \
	mc \
	mpd \
	mtd-utils \
	nano \
	ntfs-3g \
	ntp \
	ofgwrite \
	openresolv \
	openssh \
	openvpn \
	parted \
	procps \
	pyload \
	python-requests \
	python-mechanize \
	rsync \
	rtorrent \
	sabnzbd \
	smartmontools \
	smbnetfs \
	strace \
	tcpdump \
	tmux \
	transmission \
	vim \
	xfsprogs \
	zeroconf \
	zram \
	"

export IMAGE_BASENAME = "openpli"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"

# Some features in image.bbclass we do NOT want, so override them
# to be empty. We want to log in as root, but NOT via SSH. So we want
# to live without debug-tweaks...
zap_root_password () {
	true
}
ssh_allow_empty_password () {
	true
}
