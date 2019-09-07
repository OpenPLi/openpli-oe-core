FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://001_gdk_window_ensure_native_patch.diff;apply=yes;striplevel=1 \
	file://002_remove_legacy_log_patch.diff;apply=yes;striplevel=1 \
	file://003_disable_demo_build_patch.diff;apply=yes;striplevel=1 \
	file://004_gdk_input_for_hbbtv.patch.diff;apply=yes;striplevel=1 \
	"

PACKAGECONFIG = "directfb"
