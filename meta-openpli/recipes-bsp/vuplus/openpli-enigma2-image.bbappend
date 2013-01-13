ENIGMA2_PLUGINS += " ${@base_contains('MACHINE_FEATURES', 'hbbtv', 'enigma2-plugin-extensions-hbbtv', '', d)} "
ENIGMA2_PLUGINS += " ${@base_contains('MACHINE_FEATURES', 'transcodingsetup', 'enigma2-plugin-systemplugins-transcodingsetup', '', d)} "
