FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "1.24.5"

SRC_URI[sha256sum] = "2bdef209252bf146351843134b797db6b6e7adb4c00d82e83bd5abe608253a7b"

SRC_URI:append = " file://001-revert-use-new-gst-adapter-get-buffer.patch"
