# Just a comment line to avoid PAK archive (application/x-pak)
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://github.com/E2OpenPlugins/e2openplugin-openwebif.git;protocol=https"

SRC_URI_append_dm8000 = " file://get-rid-of-orgdream-check.patch"
SRC_URI_append = " file://0001-revert-workaround-for-non-pli-streamproxy.patch"

python do_cleanup () {
    # contains: MACHINE, box image, remote image, remote map
    boxtypes = [
        ('dm500hd', 'dm500hd.png', 'dm_normal.png', 'dmm.html'),
        ('dm7020hd', 'dm7020hd.png', 'dmm2.png', 'dmm2.html'),
        ('dm8000', 'dm8000.png', 'dmm1.png', 'dmm1.html'),
        ('dm800se', 'dm800se.png', 'dm_normal.png', 'dmm.html'),
        ('osmega', 'osmega.png', 'osmini.png', 'osmini.html'),
        ('osmini4k', 'osmini4k.png', 'edision4.png', 'edision4.html'),
        ('osmini', 'osmini.png', 'osmini.png', 'osmini.html'),
        ('osminiplus', 'osminiplus.png', 'osmini.png', 'osmini.html'),
        ('osmio4k', 'osmio4k.png', 'edision4.png', 'edision4.html'),
        ('osmio4kplus', 'osmio4kplus.png', 'edision4.png', 'edision4.html'),
        ('osnino', 'osnino.png', 'edision1.png', 'edision1.html'),
        ('osninoplus', 'osninoplus.png', 'edision2.png', 'edision2.html'),
        ('osninopro', 'osninopro.png', 'edision3.png', 'edision3.html'),
        ('formuler1', 'formuler1.png', 'formuler1.png', 'formuler1.html'),
        ('formuler3', 'formuler3.png', 'formuler1.png', 'formuler1.html'),
        ('formuler4', 'formuler4.png', 'formuler1.png', 'formuler1.html'),
        ('formuler4turbo', 'formuler4turbo.png', 'formuler1.png', 'formuler1.html'),
        ('gbquad4k', 'gbquad4k.png', 'gb7252.png', 'gb7252.html'),
        ('gbue4k', 'gbue4k.png', 'gb7252.png', 'gb7252.html'),
        ('gbtrio4k', 'gbtrio4k.png', 'gb7252.png', 'gb7252.html'),
        ('hd11', 'hd11.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd1100', 'hd1100.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd1200', 'hd1200.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd1265', 'hd1265.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd1500', 'hd1500.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd2400', 'hd2400.png', 'hd2400.png', 'hd2400.html'),
        ('hd500c', 'hd500c.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd51', 'hd51.png', 'hd1x00.png', 'hd1x00.html'),
        ('hd60', 'hd60.png', 'hd60.png', 'hd60.html'),
        ('hd66se', 'hd66se.png', 'hd66se.png', 'hd66se.html'),
        ('hd530c', 'hd530c.png', 'hd1x00.png', 'hd1x00.html'),
        ('multibox', 'multibox.png', 'multibox.png', 'multibox.html'),
        ('multiboxse', 'multiboxse.jpg', 'multibox.png', 'multibox.html'),
        ('vs1000', 'vs1000.png', 'vs1x00.png', 'vs1x00.html'),
        ('vs1500', 'vs1500.png', 'vs1x00.png', 'vs1x00.html'),
        ('et1x000', 'et11000.png', 'et7000mini.png', 'et7000mini.html'),
        ('et7000mini', 'et7000mini.png', 'et7000mini.png', 'et7000mini.html'),
        ('mbmicro', 'mbmicro.png', 'miraclebox2.png', 'miraclebox2.html'),
        ('mbmicrov2', 'mbmicrov2.png', 'miraclebox2.png', 'miraclebox2.html'),
        ('mbtwinplus', 'mbtwinplus.png', 'miraclebox2.png', 'miraclebox2.html'),
        ('alphatriplehd', 'alphatriplehd.png', 'alphatriplehd.png', 'alphatriplehd.html'),
        ('spycat', 'spycat.png', 'spycat.png', 'spycat.html'),
        ('spycat4kmini', 'spycat4kmini.png', 'spycat.png', 'spycat.html'),
        ('spycatmini', 'spycatmini.png', 'spycat.png', 'spycat.html'),
        ('spycatminiplus', 'spycatminiplus.png', 'spycat.png', 'spycat.html'),
        ('vuduo', 'vuduo.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuduo2', 'vuduo2.png', 'vu_duo2.png', 'vu_duo2.html'),
        ('vusolo', 'vusolo.png', 'vu_normal.png', 'vu_normal.html'),
        ('vusolo2', 'vusolo2.png', 'vu_normal.png', 'vu_normal.html'),
        ('vusolo4k', 'vusolo4k.png', 'vu_normal.png', 'vu_normal.html'),
        ('vusolose', 'vusolose.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuultimo', 'vuultimo.png', 'vu_ultimo.png', 'vu_ultimo.html'),
        ('vuultimo4k', 'vuultimo4k.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuuno', 'vuuno.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuuno4k', 'vuuno4k.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuzero', 'vuzero.png', 'vu_normal.png', 'vu_normal.html'),
        ('vuuno4kse', 'vuuno4kse.png', 'vu_normal_02.png', 'vu_normal_02.html'),
        ('vuzero4k', 'vuzero4k.png', 'vu_normal_02.png', 'vu_normal_02.html'),
        ('vuduo4k', 'vuduo4k.png', 'vu_normal_02.png', 'vu_normal_02.html'),
        ('vuduo4kse', 'vuduo4kse.png', 'vu_normal_02.png', 'vu_normal_02.html'),
        ('wetekplay', 'wetekplay.png', 'wetekplay.png', 'wetekplay.html'),
        ('xp1000', 'xp1000.png', 'xp_rc14_normal.png', 'xp1000.html'),
        ('xpeedc', 'xpeedlx.png', 'xpeedlx.png', 'xpeedlx.html'),
        ('fusionhd', 'fusionhd.png', 'fusionhd.png', 'fusionhd.html'),
        ('fusionhdse', 'fusionhdse.png', 'fusionhdse.png', 'fusionhdse.html'),
        ('galaxy4k', 'galaxy4k.png', 'galaxy4k.png', 'galaxy4k.html'),
        ('purehd', 'purehd.png', 'purehd.png', 'purehd.html'),
        ('purehdse', 'purehdse.png', 'purehd.png', 'purehd.html'),
        ('revo4k', 'revo4k.png', 'revo4k.png', 'revo4k.html'),
        ('et10000', 'et10000.png', 'et8000.png', 'et8000.html'),
        ('et4x00', 'et4x00.png', 'et_rc13_normal.png', 'et4x00.html'),
        ('et5x00', 'et5x00.png', 'et_rc5_normal.png', 'et5x00.html'),
        ('et6x00', 'et6x00.png', 'et_rc5_normal.png', 'et5x00.html'),
        ('et7x00', 'et7x00.png', 'et7x00.png', 'et7x00.html'),
        ('et9x00', 'et9x00.png', 'et_rc7_normal.png', 'et9x00.html'),
        ('et6500', 'et6500.png', 'et6500.png', 'et6500.html'),
        ('et7000', 'et7000.png', 'et7x00.png', 'et7x00.html'),
        ('et7500', 'et7000.png', 'et7x00.png', 'et7x00.html'),
        ('et8000', 'et8000.png', 'et8000.png', 'et8000.html'),
        ('et8500', 'et8500.png', 'et8000.png', 'et8000.html'),
        ('h3', 'h3.png', 'h3.png', 'h3.html'),
        ('h4', 'h4.png', 'h3.png', 'h3.html'),
        ('h5', 'h5.png', 'h3.png', 'h3.html'),
        ('h6', 'h5.png', 'h3.png', 'h3.html'),
        ('h7', 'h7.png', 'h3.png', 'h3.html'),
        ('h8', 'h8.png', 'h3.png', 'h3.html'),
        ('h9', 'h9.png', 'h3.png', 'h3.html'),
        ('h10', 'h10.png', 'h3.png', 'h3.html'),
        ('h11', 'h11.png', 'h3.png', 'h3.html'),
        ('i55', 'i55.png', 'i55.png', 'i55.html'),
        ('i55plus', 'i55plus.png', 'h3.png', 'h3.html'),
        ('h9combo', 'h9combo.png', 'h3.png', 'h3.html'),
        ('h9se', 'h9se.s.png', 'h3.png', 'h3.html'),
        ('h9combose', 'h9combose.png', 'h3.png', 'h3.html'),
        ('lc', 'lc.png', 'sh1.png', 'sh1.html'),
        ('sh1', 'sh1.png', 'sh1.png', 'sh1.html'),
        ('vipercombo', 'vipercombo.png', 'amiko.png', 'amiko.html'),
        ('vipercombohdd', 'vipercombohdd.png', 'amiko1.png', 'amiko1.html'),
        ('vipert2c', 'vipert2c.png', 'amiko.png', 'amiko.html'),
        ('viperslim', 'viperslim.png', 'viperslim.png', 'viperslim.html'),
        ('lunix3-4k', 'lunix3-4k.png', 'qviart.png', 'qviart.html'),
        ('lunix', 'lunix.png', 'qviart.png', 'qviart.html'),
        ('lunix4k', 'lunix4k.png', 'lunix4k.png', 'lunix4k.html'),
        ('dual', 'dual.png', 'dual.png', 'dual.html'),
        ('e4hd', 'e4hd.png', 'e4hd.png', 'e4hd.html'),
        ('sf8008', 'sf8008.png', 'octagon.png', 'octagon.html'),
        ('sf8008m', 'sf8008m.png', 'octagon.png', 'octagon.html'),
        ('ustym4kpro', 'ustym4kpro.png', 'uclan.png', 'uclan.html'),
        ('pulse4k', 'pulse4k.png', 'pulse4k.png', 'pulse4k.html'),
        ('pulse4kmini', 'pulse4kmini.png', 'pulse4k.png', 'pulse4k.html'),
    ]

    import os

    pluginpath = "%s%s" % (d.getVar('D', True), d.getVar('PLUGINPATH', True))
    images = "%s/public/images/" % pluginpath
    keymaps = "%s/public/static/" % pluginpath

    target_box = 'unknown.png'
    target_remote = 'ow_remote.png'
    target_keymap = ''
    exception = []

    for x in boxtypes:
        if x[0] == d.getVar('MACHINE', True):
            target_box = x[1]
            target_remote = x[2]
            target_keymap = x[3]
            if x[0] == 'et6x00':
                exception = ['et6500.png']
            elif x[0] == 'et7x00':
                exception = ['et7500.png']
            elif x[0] == 'xpeedc':
                exception = ['xpeedlx.png']
            elif x[0] == 'h8':
                exception = ['h8.png', 'h8.2s.png', 'h8.2h.png']
            elif x[0] == 'h9':
                exception = ['h9.png', 'h9.2s.png', 'h9.2h.png', 'h9.s.png', 'h9.t.png']
            elif x[0] == 'h10':
                exception = ['h10.t.png', 'h10.2s.png', 'h10.2h.png']
            elif x[0] == 'h11':
                exception = ['h11.png', 'h11.s.png']
            elif x[0] == 'h9combo':
                exception = ['h9combo.png', 'h9twin.png']
            elif x[0] == 'h9se':
                exception = ['h9se.png', 'h9se.s.png', 'h9se.22.png', 'h9se.2h.png']
            elif x[0] == 'h9combose':
                exception = ['h9combose.png', 'h9twinse.png']
            elif x[0] == 'sf8008':
                exception = ['sf8008s.png', 'sf8008t.png', 'sf8008c.png']
            break

    for root, dirs, files in os.walk(images + 'boxes', topdown=False):
        for name in files:
            if target_box != name and name != 'unknown.png' and name not in exception:
                os.remove(os.path.join(root, name))

    for root, dirs, files in os.walk(images + 'remotes', topdown=False):
        for name in files:
            if target_remote != name and name != 'ow_remote.png' and name not in exception:
                os.remove(os.path.join(root, name))

    for root, dirs, files in os.walk(keymaps + 'remotes', topdown=False):
        for name in files:
            if target_keymap != name:
                os.remove(os.path.join(root, name))
}

addtask do_cleanup after do_populate_sysroot before do_package

PACKAGES =+ "${PN}-vxg"
DESCRIPTION_${PN}-vxg = "Adds Google Chrome support to OpenWebif's WebTV"
FILES_${PN}-vxg = "${libdir}/enigma2/python/Plugins/Extensions/OpenWebif/public/vxg"
RDEPENDS_${PN}-vxg =+ "${PN}"
