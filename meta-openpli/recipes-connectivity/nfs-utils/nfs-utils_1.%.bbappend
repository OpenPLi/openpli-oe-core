# Commit 664ae3dc52fd7fc8c6f64e6cf5e70f97dedd332d in OE-core force-feeds
# bash into our system, which we definitely don't want to happen. This
# bbappend basically reverses that commit.
#
RDEPENDS_${PN}-client = "rpcbind bash"
RDEPENDS_${PN} = "${PN}-client"

# The startup script does a check that doesn't work, replace it. It's
# also overly complex, so simplified it too.
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
