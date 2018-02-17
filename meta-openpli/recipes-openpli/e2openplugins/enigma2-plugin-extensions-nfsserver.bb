MODULE = "NfsServer"
DESCRIPTION = "NFS server configuration"

RDEPENDS_${PN} = "nfs-utils"

require conf/license/license-gplv2.inc
require openplugins-replace-pli.inc
require openplugins-distutils.inc
