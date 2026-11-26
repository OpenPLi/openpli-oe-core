require glib.inc

SRC_URI:append:class-target:kernel-4.4 = " file://0001-gmain-fall-back-to-poll-on-timeout-overflow.patch"
PR:class-target:kernel-4.4 = "r1"

