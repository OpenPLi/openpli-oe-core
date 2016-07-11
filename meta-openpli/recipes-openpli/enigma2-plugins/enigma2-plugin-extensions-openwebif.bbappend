PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

python do_package_prepend () {
    boxtypes = [
        ('formuler1', 'formuler1.jpg', 'formuler1.png'),
        ('formuler3', 'formuler3.jpg', 'formuler1.png'),
        ('dm500hd', 'dm500hd.jpg', 'dm_normal.png'),
        ('dm7020hd', 'dm7020hd.jpg', 'dmm2.png'),
        ('dm8000', 'dm8000.jpg', 'dmm1.png'),
        ('dm800se', 'dm800se.jpg', 'dm_normal.png'),
        ('et5x00', 'et5x00.jpg', 'et_rc5_normal.png'),
        ('et6x00', 'et5x00.jpg', 'et_rc5_normal.png'),
        ('et9x00', 'et9x00.jpg', 'et_rc7_normal.png'),
        ('et4x00', 'et4x00.jpg', 'et_rc13_normal.png'),
        ('et7x00', 'et7000.jpg', 'et7x00.png'),
        ('et8000', 'et8000.jpg', 'et8000.png'),
        ('et8500', 'et8500.jpg', 'et8000.png'),
        ('et10000', 'et10000.jpg', 'et8000.png'),
        ('xp1000', 'xp1000.jpg', 'xp_rc14_normal.png'),
        ('osmini', 'osmini.jpg', 'osmini.png'),
        ('osminiplus', 'osminiplus.jpg', 'osmini.png'),
        ('vuduo', 'duo.jpg', 'vu_normal.png'),
        ('vusolo', 'solo.jpg', 'vu_normal.png'),
        ('vusolo2', 'solo2.jpg', 'vu_normal.png'),
        ('vusolo4k', 'solo4k.jpg', 'vu_normal.png'),
        ('vuduo2', 'duo2.jpg', 'vu_duo2.png'),
        ('vuultimo', 'ultimo.jpg', 'vu_ultimo.png'),
        ('vuuno', 'uno.jpg', 'vu_normal.png'),
        ('vuzero', 'zero.jpg', 'vu_normal.png'),
        ('hd1100', 'hd1100.jpg', 'hd1x00.png'),
        ('hd1200', 'hd1200.jpg', 'hd1x00.png'),
        ('hd2400', 'hd2400.jpg', 'hd2400.png'),
        ('fusionhd', 'fusionhd.jpg', 'fusionhd.png'),
        ('fusionhdse', 'fusionhdse.jpg', 'fusionhdse.png'),
        ('spycat', 'spycat.jpg', 'spycat.png'),
        ('spycatmini', 'spycat.jpg', 'spycat.png'),
        ('wetekplay', 'wetekplay.jpg', 'wetekplay.png'),
        ('xpeedc', 'xpeedlx.jpg', 'xpeedlx.png'),
        ('mbtwinplus', 'mbtwinplus.jpg', 'miraclebox.png'),
        ('mbmicro', 'mbmicro.jpg', 'miraclebox2.png'),
	('et7000mini', 'et7000mini.jpg', 'et7000mini.png'),
        
    ]
    import os
    top = '${D}${PLUGINPATH}/public/images/'
    target_box = 'unknown.jpg'
    target_remote = 'ow_remote.png'
    exception = ''
    for x in boxtypes:
        if x[0] == '${MACHINE}':
            target_box = x[1]
            target_remote = x[2]
            if x[0] == 'et6x00':
                exception = 'et6500'
            elif x[0] == 'et7x00':
                exception = 'et7500'
            elif x[0] == 'xpeedc':
                exception = 'xpeedlx'
            elif x[0] == 'dm8000':
                dir = '${D}${PLUGINPATH}/public/static/remotes'
                os.system('cp %s/dmm1.html %s/dmm.html' % (dir, dir))
            break
    for root, dirs, files in os.walk(top + 'boxes', topdown=False):
        for name in files:
            if target_box != name and name != 'unknown.jpg' and (exception + '.jpg' != name):
                os.remove(os.path.join(root, name))
    for root, dirs, files in os.walk(top + 'remotes', topdown=False):
        for name in files:
            if target_remote != name and name != 'ow_remote.png' and (exception + '.png' != name):
                os.remove(os.path.join(root, name))
}
