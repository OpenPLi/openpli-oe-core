VUPLUS_EXT_PLUGINS = " \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-streamtv \
	enigma2-plugin-systemplugins-wirelessaccesspoint \
	enigma2-plugin-systemplugins-zappingmodeselection \
	enigma2-plugin-systemplugins-manualfancontrol \
	enigma2-plugin-systemplugins-firmwareupgrade \
	"

VUPLUS_PLUGINS = " \
	${@base_contains('MACHINE_FEATURES', 'hbbtv', 'enigma2-plugin-extensions-hbbtv', '', d)} \
	${@base_contains('MACHINE_FEATURES', 'transcodingsetup', 'enigma2-plugin-systemplugins-transcodingsetup', '', d)} \
	${@base_contains('MACHINE_FEATURES', 'ctrlrc', 'enigma2-plugin-systemplugins-remotecontrolcode', '', d)} \
	${@base_contains('MACHINE_FEATURES', 'wol', 'enigma2-plugin-systemplugins-wolsetup', '', d)} \
	${@base_contains('MACHINE', 'vuduo2', '${VUPLUS_EXT_PLUGINS}', '', d)} \
	"

VUPLUS_INSTALL = " \
	${@base_contains('MACHINE', 'vuduo2', 'enigma2-plugin-extensions-lcd4linux enigma2-plugin-extensions-lcd4linuxsupport vuplus-checkvfd', '', d)} \
	"

ENIGMA2_PLUGINS += " ${VUPLUS_PLUGINS} "
IMAGE_INSTALL += " ${VUPLUS_INSTALL} "

