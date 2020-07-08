LIC_FILES_CHKSUM = "file://LICENSE;md5=c679c9d6b02bc2757b3eaf8f53c43fba"

PV = "2020a"

DEFAULT_TIMEZONE = "Europe/Amsterdam"

SRC_URI ="http://www.iana.org/time-zones/repository/releases/tzdata${PV}.tar.gz;name=tzdata"

SRC_URI[tzdata.md5sum] = "96a985bb8eeab535fb8aa2132296763a"
SRC_URI[tzdata.sha256sum] = "547161eca24d344e0b5f96aff6a76b454da295dc14ed4ca50c2355043fb899a2"
