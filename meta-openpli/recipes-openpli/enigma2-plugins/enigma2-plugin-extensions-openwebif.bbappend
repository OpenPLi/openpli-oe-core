PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

python do_cleanup () {
    boxtypes = [
        ('dm500hd', 'dm500hd.png', 'dm_normal.png'),
        ('dm7020hd', 'dm7020hd.png', 'dmm2.png'),
        ('dm8000', 'dm8000.png', 'dmm1.png'),
        ('dm800se', 'dm800se.png', 'dm_normal.png'),
        ('osmega', 'osmega.png', 'osmini.png'),
        ('osmini', 'osmini.png', 'osmini.png'),
        ('osminiplus', 'osminiplus.png', 'osmini.png'),
        ('formuler1', 'formuler1.png', 'formuler1.png'),
        ('formuler3', 'formuler3.png', 'formuler1.png'),
        ('formuler4', 'formuler4.png', 'formuler1.png'),
        ('formuler4turbo', 'formuler4turbo.png', 'formuler1.png'),
        ('hd11', 'hd11.png', 'hd1x00.png'),
        ('hd1100', 'hd1100.png', 'hd1x00.png'),
        ('hd1200', 'hd1200.png', 'hd1x00.png'),
        ('hd1265', 'hd1265.png', 'hd1x00.png'),
        ('hd1500', 'hd1500.png', 'hd1x00.png'),
        ('hd2400', 'hd2400.png', 'hd2400.png'),
        ('hd500c', 'hd500c.png', 'hd1x00.png'),
        ('hd51', 'hd51.png', 'hd1x00.png'),
        ('hd530c', 'hd530c.png', 'hd1x00.png'),
        ('vs1000', 'vs1000.png', 'vs1x00.png'),
        ('vs1500', 'vs1500.png', 'vs1x00.png'),
        ('et1x000', 'et11000.png', 'et7000mini.png'),
        ('et7000mini', 'et7000mini.png', 'et7000mini.png'),
        ('mbmicro', 'mbmicro.png', 'miraclebox2.png'),
        ('mbmicrov2', 'mbmicrov2.png', 'miraclebox2.png'),
        ('mbtwinplus', 'mbtwinplus.png', 'miraclebox2.png'),
        ('alphatriplehd', 'alphatriplehd.png', 'alphatriplehd.png'),
        ('spycat', 'spycat.png', 'spycat.png'),
        ('spycat4kmini', 'spycat4kmini.png', 'spycat.png'),
        ('spycatmini', 'spycatmini.png', 'spycat.png'),
        ('spycatminiplus', 'spycatminiplus.png', 'spycat.png'),
        ('vuduo', 'duo.png', 'vu_normal.png'),
        ('vuduo2', 'duo2.png', 'vu_duo2.png'),
        ('vusolo', 'solo.png', 'vu_normal.png'),
        ('vusolo2', 'solo2.png', 'vu_normal.png'),
        ('vusolo4k', 'solo4k.png', 'vu_normal.png'),
        ('vusolose', 'solose.png', 'vu_normal.png'),
        ('vuultimo', 'ultimo.png', 'vu_ultimo.png'),
        ('vuultimo4k', 'ultimo4k.png', 'vu_normal.png'),
        ('vuuno', 'uno.png', 'vu_normal.png'),
        ('vuuno4k', 'uno4k.png', 'vu_normal.png'),
        ('vuzero', 'zero.png', 'vu_normal.png'),
        ('wetekplay', 'wetekplay.png', 'wetekplay.png'),
        ('xp1000', 'xp1000.png', 'xp_rc14_normal.png'),
        ('xpeedc', 'xpeedlx.png', 'xpeedlx.png'),
        ('fusionhd', 'fusionhd.png', 'fusionhd.png'),
        ('fusionhdse', 'fusionhdse.png', 'fusionhdse.png'),
        ('galaxy4k', 'galaxy4k.png', 'galaxy4k.png'),
        ('purehd', 'purehd.png', 'purehd.png'),
        ('revo4k', 'revo4k.png', 'revo4k.png'),
        ('et10000', 'et10000.png', 'et8000.png'),
        ('et4x00', 'et4x00.png', 'et_rc13_normal.png'),
        ('et5x00', 'et5x00.png', 'et_rc5_normal.png'),
        ('et6x00', 'et6x00.png', 'et_rc5_normal.png'),
        ('et7x00', 'et7x00.png', 'et7x00.png'),
        ('et9x00', 'et9x00.png', 'et_rc7_normal.png'),
        ('et6500', 'et6500.png', 'et_rc5_normal.png'),
        ('et7000', 'et7000.png', 'et7x00.png'),
        ('et7500', 'et7000.png', 'et7x00.png'),
        ('et8000', 'et8000.png', 'et8000.png'),
        ('et8500', 'et8500.png', 'et8000.png'),
        ('h3', 'h3.png', 'h3.png'),
        ('h5', 'h5.png', 'h5.png'),
        ('h7', 'h7.png', 'h5.png'),
        ('i55', 'i55.png', 'h5.png'),
        ('lc', 'lc.png', 'h3.png'),
        ('sh1', 'sh1.png', 'h3.png'),
    ]

    import os

    pluginpath = "%s%s" % (d.getVar('D', True), d.getVar('PLUGINPATH', True))
    top = "%s/public/images/" % pluginpath
    target_box = 'unknown.png'
    target_remote = 'ow_remote.png'
    exception = ''
    for x in boxtypes:
        if x[0] == d.getVar('MACHINE', True):
            target_box = x[1]
            target_remote = x[2]
            if x[0] == 'et6x00':
                exception = 'et6500'
            elif x[0] == 'et7x00':
                exception = 'et7500'
            elif x[0] == 'xpeedc':
                exception = 'xpeedlx'
            elif x[0] == 'dm8000':
                dir = '%s/public/static/remotes' % pluginpath
                os.system('cp %s/dmm1.html %s/dmm.html' % (dir, dir))
            break
    for root, dirs, files in os.walk(top + 'boxes', topdown=False):
        for name in files:
            if target_box != name and name != 'unknown.png' and (exception + '.png' != name):
                os.remove(os.path.join(root, name))
    for root, dirs, files in os.walk(top + 'remotes', topdown=False):
        for name in files:
            if target_remote != name and name != 'ow_remote.png' and (exception + '.png' != name):
                os.remove(os.path.join(root, name))
}

addtask do_cleanup after do_install before do_package

PACKAGES =+ "${PN}-vxg"
DESCRIPTION_${PN}-vxg = "Adds Google Chrome support to OpenWebif's WebTV"
FILES_${PN}-vxg = "/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/vxg"
RDEPENDS_${PN}-vxg =+ "${PN}"
