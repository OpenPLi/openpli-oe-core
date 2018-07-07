FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

do_install_append() {
	# umountnfs should run before network stops (which is at K40)
	ln -sf		../init.d/umountnfs.sh	${D}${sysconfdir}/rc6.d/K31umountnfs.sh
	ln -sf		../init.d/umountnfs.sh	${D}${sysconfdir}/rc0.d/K31umountnfs.sh
	ln -sf		../init.d/rcS.local	${D}${sysconfdir}/rcS.d/S95local
	echo "d root root 0755 /var/volatile/cache none" >> ${D}${sysconfdir}/default/volatiles/00_core
	echo "l root root 0755 /var/cache /var/volatile/cache" >> ${D}${sysconfdir}/default/volatiles/00_core
}
