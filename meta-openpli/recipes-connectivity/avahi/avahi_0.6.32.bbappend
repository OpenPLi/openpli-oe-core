# move the delete from postrm to prerm

pkg_postrm_avahi-daemon () {
	true
}

pkg_prerm_avahi-daemon () {
	deluser avahi || true
	delgroup avahi || true
}
