DESCRIPTION = "E2OpenPlugins Task"
PR = "r0"

inherit packagegroup

DEPENDS = " \
	enigma2-plugin-extensions-addstreamurl \
	enigma2-plugin-extensions-antilogo \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-autobouquets \
	enigma2-plugin-extensions-bitrate \
	enigma2-plugin-extensions-blackoutblind \
	enigma2-plugin-extensions-changerootpassword \
	enigma2-plugin-extensions-filecommander \
	enigma2-plugin-extensions-foreca \
	enigma2-plugin-extensions-hetweer \
	enigma2-plugin-extensions-magentamusik360 \
	enigma2-plugin-extensions-newsreader \
	enigma2-plugin-extensions-nfsserver \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-oroscopoitalia \
	enigma2-plugin-extensions-permanentvfdclock \
	enigma2-plugin-extensions-ppanel \
	enigma2-plugin-extensions-remotecontrolchannel \
	enigma2-plugin-extensions-screenposition \
	enigma2-plugin-extensions-shootyourscreen \
	enigma2-plugin-extensions-streaminterface \
	enigma2-plugin-extensions-wakeonlan \
	enigma2-plugin-systemplugins-autoshutdown \
	enigma2-plugin-extensions-setpicon \
	enigma2-plugin-extensions-xpower \
	enigma2-plugin-extensions-meteoviewer \
	enigma2-plugin-extensions-snmpagent \
	enigma2-plugin-extensions-systemtools \
	enigma2-plugin-extensions-cacheflush \
	enigma2-plugin-extensions-analogclock \
	enigma2-plugin-systemplugins-autobouquetsmaker \
	enigma2-plugin-extensions-telekomsport \
	enigma2-plugin-extensions-oscamstatus \
"

require conf/license/license-gplv2.inc
