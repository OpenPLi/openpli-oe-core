# needs to be build for MACHINE arch, as it depends on libgles, which does

# bitbake doesn't like a hyphen in an ARCH name, but we have MACHINE names with them...
PACKAGE_ARCH = "${@'${MACHINE}'.replace('-', '_')}"
