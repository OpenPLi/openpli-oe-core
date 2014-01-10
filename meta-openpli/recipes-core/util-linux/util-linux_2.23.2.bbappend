PRINC = "1"

# Lower the priorities of util-linux-(u)mount, so that if they happen to
# become installed, they won't replace the working busybox commands.

ALTERNATIVE_PRIORITY[mount] = "10"
ALTERNATIVE_PRIORITY[umount] = "10"

PACKAGES =+ "util-linux-fstrim"
FILES_util-linux-fstrim = "${base_sbindir}/fstrim"
