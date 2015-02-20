PACKAGE_ARCH = "${MACHINE_ARCH}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

python do_package_prepend () {
    boxtypes = [
        ('formuler1', 'formuler1.jpg', 'formuler1.png'),
        ('dm500hd', 'dm500hd.jpg', 'dm_normal.png'),
        ('dm7020hd', 'dm7020hd.jpg', 'dm_normal.png'),
        ('dm8000', 'dm8000.jpg', 'dm_normal.png'),
        ('dm800se', 'dm800se.jpg', 'dm_normal.png'),
        ('et5x00', 'et5x00.jpg', 'et_rc5_normal.png'),
        ('et6x00', 'et5x00.jpg', 'et_rc5_normal.png'),
        ('et9x00', 'et9x00.jpg', 'et_rc7_normal.png'),
        ('et4x00', 'et4x00.jpg', 'et_rc13_normal.png'),
        ('et8000', 'et8000.jpg', 'et8000.png'),
        ('et10000', 'et10000.jpg', 'et8000.png'),
        ('xp1000', 'xp1000.jpg', 'xp_rc14_normal.png'),
        ('vuduo', 'duo.jpg', 'vu_normal.png'),
        ('vusolo', 'solo.jpg', 'vu_normal.png'),
        ('vusolo2', 'solo2.jpg', 'vu_normal.png'),
        ('vuduo2', 'duo2.jpg', 'vu_normal.png'),
        ('vuultimo', 'ultimo.jpg', 'vu_ultimo.png'),
        ('vuuno', 'uno.jpg', 'vu_normal.png'),
        (hd1100', 'hd1100.jpg', 'hd1100.png'),
        (hd2400', 'hd2400.jpg', 'hd2400.png'),
    ]
    import os
    top = '${D}${PLUGINPATH}/public/images/'
    target_box = 'unknown.jpg'
    target_remote = 'ow_remote.png'
    for x in boxtypes:
        if x[0] == '${MACHINE}':
            target_box = x[1]
            target_remote = x[2]
            break
    for root, dirs, files in os.walk(top + 'boxes', topdown=False):
        for name in files:
            if target_box != name and name != 'unknown.jpg':
                os.remove(os.path.join(root, name))
    for root, dirs, files in os.walk(top + 'remotes', topdown=False):
        for name in files:
            if target_remote != name and name != 'ow_remote.png':
                os.remove(os.path.join(root, name))
}
