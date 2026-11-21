DESCRIPTION = "Timer planner for selected services"
MAINTAINER = "ims(ims21)"

require conf/license/license-gplv2.inc

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/ims21/TimerPlanner.git;protocol=https;branch=main"

S="${WORKDIR}/git"

inherit setuptools3-openplugins python3-compileall
