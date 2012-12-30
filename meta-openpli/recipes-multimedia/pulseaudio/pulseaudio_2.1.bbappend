# pulseaudio tries to depend on gconf, which depends on x11, so fix the DEPENDS part
# and don't depend on gconf when x11 isn't in the distro
DEPENDS = "libatomics-ops liboil avahi libsamplerate0 libsndfile1 libtool \
           ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 libxtst libice libsm libxcb gtk+ gconf', '', d)}"
# optional
DEPENDS += "udev alsa-lib glib-2.0 dbus \
           ${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez4', '', d)}"
