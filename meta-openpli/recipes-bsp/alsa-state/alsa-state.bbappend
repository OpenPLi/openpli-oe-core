FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGE_ARCH := "${MACHINE_ARCH}"

EXTRA_ALSA ?= "empty"
EXTRA_ALSA_vuduo4k = "vuplus"
EXTRA_ALSA_vuduo4kse = "vuplus"
EXTRA_ALSA_vusolo4k = "vuplus"
EXTRA_ALSA_vuultimo4k = "vuplus"
EXTRA_ALSA_vuuno4k = "vuplus"
EXTRA_ALSA_vuuno4kse = "vuplus"
EXTRA_ALSA_vuzero4k = "vuplus"
EXTRA_ALSA_vuduo2 = "vuplus"
EXTRA_ALSA_vusolo2 = "vuplus"
EXTRA_ALSA_vusolose = "vuplus"

require alsa-state-${EXTRA_ALSA}.inc
