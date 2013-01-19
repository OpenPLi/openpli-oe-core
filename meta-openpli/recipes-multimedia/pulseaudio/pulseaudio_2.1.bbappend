# pulseaudio tries to depend on gconf, which depends on x11, so fix the DEPENDS part
# and don't depend on gconf please

DEPENDS := "${@bb.data.getVar('DEPENDS',d,1).replace('gconf', '')}"
