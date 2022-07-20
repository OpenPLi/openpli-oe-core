# -- DLNAServer --  enigma2-plugin-extensions-dlnaserver
#
# This source originate from git://code.vuplus.com/git/dvbapp.git
# and have been used in OpenPLi with patches.
# May 2019 a copy was made into OpenPLi repo and modified.
from __future__ import print_function
from Plugins.Plugin import PluginDescriptor
import socket
import os
from enigma import eTimer

from Screens.Screen import Screen
from Screens.MessageBox import MessageBox
from Screens.VirtualKeyBoard import VirtualKeyBoard

from Components.Button import Button
from Components.Label import Label
from Components.ConfigList import ConfigListScreen
from Components.Sources.StaticText import StaticText
from Components.ActionMap import NumberActionMap, ActionMap
from Components.config import config, ConfigSelection, getConfigListEntry, ConfigText, ConfigDirectory, ConfigYesNo, ConfigSubsection
from Components.FileList import FileList

config.plugins.dlnaserver = ConfigSubsection()
config.plugins.dlnaserver.autostart = ConfigYesNo(default=False)


def isRunning():
	ps_str = os.popen("ps | grep minidlnad | grep -v grep").read()
	if ps_str.strip() != "":
		return True
	return False


class SelectDirectoryWindow(Screen):
	skin = """
		<screen name="SelectDirectoryWindow" position="center,center" size="560,320" title="Select Directory">
			<ePixmap pixmap="skin_default/buttons/red.png" position="0,0" zPosition="0" size="140,40" transparent="1" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/green.png" position="140,0" zPosition="0" size="140,40" transparent="1" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/yellow.png" position="280,0" zPosition="0" size="140,40" transparent="1" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/blue.png" position="420,0" zPosition="0" size="140,40" transparent="1" alphatest="on" />
			<widget name="currentDir" position="10,60" size="530,22" valign="center" font="Regular;22" />
			<widget name="filelist" position="0,100" zPosition="1" size="560,220" scrollbarMode="showOnDemand"/>
			<widget render="Label" source="key_red" position="0,0" size="140,40" zPosition="5" valign="center" halign="center" backgroundColor="red" font="Regular;20" transparent="1" foregroundColor="white" shadowColor="black" shadowOffset="-1,-1" />
			<widget render="Label" source="key_green" position="140,0" size="140,40" zPosition="5" valign="center" halign="center" backgroundColor="red" font="Regular;20" transparent="1" foregroundColor="white" shadowColor="black" shadowOffset="-1,-1" />
		</screen>
		"""

	def __init__(self, session, currentDir):
		Screen.__init__(self, session)
		inhibitDirs = ["/bin", "/boot", "/dev", "/etc", "/lib", "/proc", "/sbin", "/sys", "/usr"]
		self["filelist"] = FileList(currentDir, showDirectories=True, showFiles=False, inhibitMounts=[], inhibitDirs=inhibitDirs)
		self["actions"] = ActionMap(["WizardActions", "DirectionActions", "ColorActions", "EPGSelectActions"], {
			"back": self.cancel,
			"left": self.left,
			"right": self.right,
			"up": self.up,
			"down": self.down,
			"ok": self.ok,
			"green": self.green,
			"red": self.cancel
		}, -1)

		self["currentDir"] = Label()
		self["key_green"] = StaticText(_("OK"))
		self["key_red"] = StaticText(_("Cancel"))

		self.onLayoutFinish.append(self.layoutFinished)

	def layoutFinished(self):
		self.updateCurrentDirectory()

	def cancel(self):
		self.close(None)

	def green(self):
		self.close(self["filelist"].getSelection()[0])

	def up(self):
		self["filelist"].up()
		self.updateCurrentDirectory()

	def down(self):
		self["filelist"].down()
		self.updateCurrentDirectory()

	def left(self):
		self["filelist"].pageUp()
		self.updateCurrentDirectory()

	def right(self):
		self["filelist"].pageDown()
		self.updateCurrentDirectory()

	def ok(self):
		if self["filelist"].canDescent():
			self["filelist"].descent()
			self.updateCurrentDirectory()

	def updateCurrentDirectory(self):
		currentDir = self["filelist"].getSelection()[0]
		if currentDir is None or currentDir.strip() == "":
			currentDir = "Invalid Location"
		self["currentDir"].setText(currentDir)


class DLNAServer(ConfigListScreen, Screen):
	skin = """
		<screen position="center,center" size="600,400" title="DLNA Server">
			<ePixmap pixmap="skin_default/buttons/red.png" position="5,0" size="140,40" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/green.png" position="155,0" size="140,40" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/yellow.png" position="305,0" size="140,40" alphatest="on" />
			<ePixmap pixmap="skin_default/buttons/blue.png" position="455,0" size="140,40" alphatest="on" />

			<widget source="key_red" render="Label" position="5,0" zPosition="1" size="140,40" font="Regular;20" halign="center" valign="center" backgroundColor="#9f1313" foregroundColor="#ffffff" transparent="1" />
			<widget source="key_green" render="Label" position="155,0" zPosition="1" size="140,40" font="Regular;20" halign="center" valign="center" backgroundColor="#1f771f" foregroundColor="#ffffff" transparent="1" />
			<widget source="key_yellow" render="Label" position="305,0" zPosition="1" size="140,40" font="Regular;20" halign="center" valign="center" backgroundColor="#a08500" foregroundColor="#ffffff" transparent="1" />
			<widget source="key_blue" render="Label" position="455,0" zPosition="1" size="140,40" font="Regular;20" halign="center" valign="center" backgroundColor="#18188b" foregroundColor="#ffffff" transparent="1" />

			<widget name="config" position="0,50" size="600,250" scrollbarMode="showOnDemand" />
			<widget name="information" position="0,300" size="600,100" valign="center" font="Regular;20" />
		</screen>
		"""

	def __init__(self, session):
		self.session = session
		Screen.__init__(self, session)

		self.oldConfig = {}
		self.menulist = []
		ConfigListScreen.__init__(self, self.menulist)

		self.configFileName = "/etc/minidlna.conf"
		self["actions"] = ActionMap(["OkCancelActions", "ShortcutActions", "WizardActions", "ColorActions", "SetupActions", ], {
			"red": self.keyExit,
			"green": self.keyGreen,
			"blue": self.keyBlue,
			"yellow": self.keyYellow,
			"cancel": self.keyExit,
			"ok": self.keyOK
                }, -2)
		self["key_red"] = StaticText(_("Exit"))
		self["key_green"] = StaticText(_("Start"))
		self["key_yellow"] = StaticText(_("Save"))
		self["key_blue"] = StaticText(_("Reset"))
		self["information"] = Label()

		self.makeMenuEntry()
		self.onLayoutFinish.append(self.layoutFinished)

		self.updateGreenTimer = eTimer()
		self.updateGreenTimer.timeout.get().append(self.cbGreenTimer)

	def layoutFinished(self):
		green_btm_str = "Start"
		if isRunning():
			green_btm_str = "Stop"
		self["key_green"].setText(green_btm_str)

	def cbGreenTimer(self):
		self.updateGreenTimer.stop()
		self["information"].setText("")
		self.layoutFinished()

	def keyExit(self):
		config.plugins.dlnaserver.autostart.save()
		self.close()

	def keyOK(self):
		self["information"].setText("")
		currentItem = self.getCurrentDirItem()
		if currentItem is not None:
			self.session.openWithCallback(self.cbChangeDirectory, SelectDirectoryWindow, currentItem.value)
			return
		currentItem = self.getCurrentTextItem()
		if currentItem is not None:
			self.session.openWithCallback(self.cbChangeText, VirtualKeyBoard, title=_("DLNA Server Name"), text=currentItem.value)

	def keyGreen(self):     # Start/Stop
		args = "stop"
		if self["key_green"].getText().strip() == "Start":
			args = "start"
			self.saveConfigFile()
		rc = os.popen("/etc/init.d/minidlna.sh %s" % (args)).read()
		self["information"].setText(rc)
		self.updateGreenTimer.start(3000)

	def keyYellow(self):    # Save
		self.saveConfigFile()
		self["information"].setText("Finished saving")

	def keyBlue(self):      # Reset, to reasonable default values
		self.menuItemServerName.value = self.oldConfig.get("friendly_name")
		self.menuItemOneMediaDir.value = True
		self.menuItemMediaDir.value = "/media/hdd/movie/" if os.path.exists("/media/hdd/movie") else "/media/"
		self.menuItemVideoDir.value = "/media/"
		self.menuItemAudioDir.value = "/media/"
		self.menuItemPictureDir.value = "/media/"
		self.menuItemRootContainer.value = True
		self.menuItemEnableLog.value = False
		self.menuItemLogLevel.value = "off"
		self.menuItemLogDir.value = "/tmp/"
		config.plugins.dlnaserver.autostart.value = True
		self.resetMenuList()

	def keyRed(self):       # Exit
		self.keyExit()

	def keyLeft(self):
		ConfigListScreen.keyLeft(self)
		self.resetMenuList()

	def keyRight(self):
		ConfigListScreen.keyRight(self)
		self.resetMenuList()

	def saveConfigFile(self):
		serverName = self.menuItemServerName.value
		mediaDir = self.menuItemMediaDir.value
		videoDir = self.menuItemVideoDir.value
		audioDir = self.menuItemAudioDir.value
		pictureDir = self.menuItemPictureDir.value
		rootContainer = "B" if self.menuItemRootContainer.value else "."
		logDir = self.menuItemLogDir.value
		logLevel = self.menuItemLogLevel.value
		if not self.menuItemEnableLog.value:
			logLevel = "off"
		if self.menuItemOneMediaDir.value:
			self.writeConfigFile(serverName=serverName, mediaDir=mediaDir, rootContainer=rootContainer, logDir=logDir, logLevel=logLevel)
		else:
			self.writeConfigFile(serverName=serverName, videoDir=videoDir, audioDir=audioDir, pictureDir=pictureDir, rootContainer=rootContainer, logDir=logDir, logLevel=logLevel)

	def getCurrentDirItem(self):
		currentEntry = self["config"].getCurrent()
		if currentEntry == self.menuEntryMediaDir:
			return self.menuItemMediaDir
		elif currentEntry == self.menuEntryVideoDir:
			return self.menuItemVideoDir
		elif currentEntry == self.menuEntryAudioDir:
			return self.menuItemAudioDir
		elif currentEntry == self.menuEntryPictureDir:
			return self.menuItemPictureDir
		elif currentEntry == self.menuEntryLogDir:
			return self.menuItemLogDir
		return None

	def cbChangeDirectory(self, pathStr):
		if pathStr is None or pathStr.strip() == "":
			return
		currentItem = self.getCurrentDirItem()
		if currentItem is not None:
			currentItem.value = pathStr

	def cbChangeText(self, newStr):
		if newStr is None or newStr.strip() == "":
			return
		currentItem = self.getCurrentTextItem()
		if currentItem is not None:
			currentItem.value = newStr

	def getCurrentTextItem(self):
		currentEntry = self["config"].getCurrent()
		if currentEntry == self.menuEntryServerName:
			return self.menuItemServerName
		return None

	def makeMenuEntry(self):	# Make all menu entry, including invisible
		self.readConfigFile()
		self.menuItemServerName = ConfigText(default=self.oldConfig.get("friendly_name"))
		self.menuItemOneMediaDir = ConfigYesNo(default=True if self.oldConfig.get("VAPmediadirExists") != "True" else False)
		self.menuItemMediaDir = ConfigDirectory(default=self.oldConfig.get("media_dir"))
		self.menuItemVideoDir = ConfigDirectory(default=self.oldConfig.get("media_dirV"))
		self.menuItemAudioDir = ConfigDirectory(default=self.oldConfig.get("media_dirA"))
		self.menuItemPictureDir = ConfigDirectory(default=self.oldConfig.get("media_dirP"))
		self.menuItemRootContainer = ConfigYesNo(default=True if self.oldConfig.get("root_container") == "B" else False)

		log_level_list = self.oldConfig.get("log_level").split("=")
		enable_log = False
		log_level = log_level_list[1]
		if log_level not in ("off", "fatal", "error", "warn", "info", "debug"):
			log_level = "off"
		if log_level != "off":
			enable_log = True
		self.menuItemEnableLog = ConfigYesNo(default=enable_log)
		self.menuItemLogLevel = ConfigSelection(default=log_level if enable_log else "warn", choices=[("fatal", _("fatal")), ("error", _("error")), ("warn", _("warn")), ("info", _("info")), ("debug", _("debug"))])
		self.menuItemLogDir = ConfigDirectory(default=self.oldConfig.get("log_dir"))

		self.menuEntryServerName = getConfigListEntry(_("Server Name"), self.menuItemServerName)
		self.menuEntryOneMediaDir = getConfigListEntry(_("Only one media directory"), self.menuItemOneMediaDir)
		self.menuEntryMediaDir = getConfigListEntry(_("Media Directory"), self.menuItemMediaDir)
		self.menuEntryVideoDir = getConfigListEntry(_("Video Directory"), self.menuItemVideoDir)
		self.menuEntryAudioDir = getConfigListEntry(_("Music Directory"), self.menuItemAudioDir)
		self.menuEntryPictureDir = getConfigListEntry(_("Picture Directory"), self.menuItemPictureDir)
		self.menuEntryRootContainer = getConfigListEntry(_("Use browser directory as root"), self.menuItemRootContainer)
		self.menuEntryEnableLog = getConfigListEntry(_("Enable Logging"), self.menuItemEnableLog)
		self.menuEntryLogLevel = getConfigListEntry(_("    - Log Level"), self.menuItemLogLevel)
		self.menuEntryLogDir = getConfigListEntry(_("    - Log Directory"), self.menuItemLogDir)
		self.menuEntryAutoStart = getConfigListEntry(_("Enable Autostart for DLNA Server"), config.plugins.dlnaserver.autostart)
		self.resetMenuList()

	def resetMenuList(self):	# Update the visible contents of the menu
		self.menulist = []
		self.menulist.append(self.menuEntryServerName)
		self.menulist.append(self.menuEntryOneMediaDir)
		if self.menuItemOneMediaDir.value:
			self.menulist.append(self.menuEntryMediaDir)
		else:
			self.menulist.append(self.menuEntryVideoDir)
			self.menulist.append(self.menuEntryAudioDir)
			self.menulist.append(self.menuEntryPictureDir)
		self.menulist.append(self.menuEntryRootContainer)
		self.menulist.append(self.menuEntryEnableLog)
		if self.menuItemEnableLog.value:
			self.menulist.append(self.menuEntryLogLevel)
			self.menulist.append(self.menuEntryLogDir)
		self.menulist.append(self.menuEntryAutoStart)
		self["config"].list = self.menulist
		self["config"].l.setList(self.menulist)
		self["information"].setText("")

	def writeConfigFile(self, serverName=None, mediaDir=None, videoDir=None, audioDir=None, pictureDir=None, rootContainer=None, logDir=None, logLevel=None):
		configString = ""

		def configDataAppend(origin, key, value):
			if key.strip() != "" and value.strip() != "":
				origin += "%s=%s\n" % (key, value)
			return origin
		configString = configDataAppend(configString, "friendly_name", serverName)
		if mediaDir is not None and mediaDir.strip() != "":
			configString = configDataAppend(configString, "media_dir", "%s" % (mediaDir))
		if videoDir is not None and videoDir.strip() != "":
			configString = configDataAppend(configString, "media_dir", "V,%s" % (videoDir))
		if audioDir is not None and audioDir.strip() != "":
			configString = configDataAppend(configString, "media_dir", "A,%s" % (audioDir))
		if pictureDir is not None and pictureDir.strip() != "":
			configString = configDataAppend(configString, "media_dir", "P,%s" % (pictureDir))
		if rootContainer is not None and rootContainer.strip() != "":
			configString = configDataAppend(configString, "root_container", rootContainer)
		if logDir is not None and logDir.strip() != "":
			configString = configDataAppend(configString, "log_dir", logDir)
		if logLevel is not None and logLevel.strip() != "":
			configString = configDataAppend(configString, "log_level", "general,artwork,database,inotify,scanner,metadata,http,ssdp,tivo=%s" % (logLevel))
		configString = configDataAppend(configString, "port", self.oldConfig.get("port"))
		configString = configDataAppend(configString, "db_dir", self.oldConfig.get("db_dir"))
		configString = configDataAppend(configString, "album_art_names", self.oldConfig.get("album_art_names"))
		configString = configDataAppend(configString, "inotify", self.oldConfig.get("inotify"))
		configString = configDataAppend(configString, "enable_tivo", self.oldConfig.get("enable_tivo"))
		configString = configDataAppend(configString, "strict_dlna", self.oldConfig.get("strict_dlna"))
		configString = configDataAppend(configString, "notify_interval", self.oldConfig.get("notify_interval"))
		configString = configDataAppend(configString, "serial", self.oldConfig.get("serial"))
		configString = configDataAppend(configString, "model_number", self.oldConfig.get("model_number"))
		print(configString)
		confFile = open(self.configFileName, "w")
		confFile.write(configString)
		confFile.close()

	def readConfigFile(self):
		self.oldConfig = {}
		if os.path.exists(self.configFileName):
			listDirConfig = ("media_dir", "media_dirV", "media_dirA", "media_dirP", "log_dir", "db_dir")
			for line in open(self.configFileName).readlines():
				line = line.strip()
				if line == "" or line[0] == '#':
					continue
				try:
					i = line.find('=')
					k, v = line[:i], line[i + 1:]
					k, v = k.strip(), v.strip()
					# Special handling if 3 media directories
					if k == "media_dir" and v[1] == ',' and (v[0] in "VAP"):
						k += v[0]
						v = v[2:]
						self.oldConfig["VAPmediadirExists"] = "True"
					# Directories needs to end with /, for the FileList component to work properly
					if k in listDirConfig:
						if v != None and v != "" and v[-1] != '/':
							v = v + "/"
					self.oldConfig[k] = v
				except:
					pass

		def setDefault(key, default):	# If value not in config file, create it and set a default value
			try:
				value = self.oldConfig.get(key)
				if value == None or value.strip() == "":
					self.oldConfig[key] = default
			except:
				self.oldConfig[key] = default

		setDefault("friendly_name", "%s" % (socket.gethostname()))
		setDefault("VAPmediadirExists", "False")		# Flag for this plugin code, this is not a configuration value in the config file.
		setDefault("media_dir", "/media/")
		setDefault("media_dirV", "/media/")
		setDefault("media_dirA", "/media/")
		setDefault("media_dirP", "/media/")
		setDefault("root_container", "B")
		setDefault("log_dir", "/tmp/")
		setDefault("log_level", "general,artwork,database,inotify,scanner,metadata,http,ssdp,tivo=off")
		setDefault("port", "8200")
		setDefault("db_dir", "/var/lib/minidlna/")
		setDefault("album_art_names", "Cover.jpg/cover.jpg/AlbumArtSmall.jpg/albumartsmall.jpg/AlbumArt.jpg/albumart.jpg/Album.jpg/album.jpg/Folder.jpg/folder.jpg/Thumb.jpg/thumb.jpg")
		setDefault("inotify", "yes")
		setDefault("enable_tivo", "no")
		setDefault("strict_dlna", "no")
		setDefault("notify_interval", "900")
		setDefault("serial", "12345678")
		setDefault("model_number", "1")
		print("Current Config : %s" % self.oldConfig)


def main(session, **kwargs):
	session.open(DLNAServer)


def autostart(reason, **kwargs):
	if reason == 0:
		if isRunning():
			args = "stop"
			is_running = True
		else:
			args = "start"
			is_running = False
		cmd = "/etc/init.d/minidlna.sh " + args

		if config.plugins.dlnaserver.autostart.value:
			if is_running:
				print("[DLNAServer] already started")
			else:
				print("[DLNAServer] starting ...")
				os.system(cmd)
		elif config.plugins.dlnaserver.autostart.value == False and is_running == True:
				print("[DLNAServer] stopping ...")
				os.system(cmd)


def Plugins(**kwargs):
	return [PluginDescriptor(name="DLNA Server", description="DLNA server using MiniDLNA", where=PluginDescriptor.WHERE_PLUGINMENU, needsRestart=False, fnc=main),
		PluginDescriptor(where=[PluginDescriptor.WHERE_AUTOSTART], fnc=autostart)]
