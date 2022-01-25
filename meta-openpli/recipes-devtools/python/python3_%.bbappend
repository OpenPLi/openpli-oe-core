FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://importlib.patch"

ALTERNATIVE_${PN}-core = "python python_config"
ALTERNATIVE_LINK_NAME[python] = "${bindir}/python"
ALTERNATIVE_LINK_NAME[python_config] = "${bindir}/python-config"
ALTERNATIVE_TARGET[python] = "${bindir}/python3"
ALTERNATIVE_TARGET[python_config] = "${bindir}/python3-config"

# add dummy __pycache__ files to make happy rm later...
do_install_prepend() {
    mkdir -p ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__
    touch ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__/test_range.cpython
    touch ${D}${libdir}/python${PYTHON_MAJMIN}/test/__pycache__/test_xml_etree.cpython
}

PY3MANIFEST := "${THISDIR}/${PN}/python3-custom-manifest.json"

python(){
    import collections, json

    filename = d.getVar('PY3MANIFEST')
    # This python changes the datastore based on the contents of a file, so mark
    # that dependency.
    bb.parse.mark_dependency(d, filename)

    with open(filename) as manifest_file:
        manifest_str =  manifest_file.read()
        json_start = manifest_str.find('# EOC') + 6
        manifest_file.seek(json_start)
        manifest_str = manifest_file.read()
        python_manifest = json.loads(manifest_str, object_pairs_hook=collections.OrderedDict)

    # First set RPROVIDES for -native case
    # Hardcoded since it cant be python3-native-foo, should be python3-foo-native
    pn = 'python3'
    rprovides = (d.getVar('RPROVIDES') or "").split()

    # ${PN}-misc-native is not in the manifest
    rprovides.append(pn + '-misc-native')

    for key in python_manifest:
        pypackage = pn + '-' + key + '-native'
        if pypackage not in rprovides:
              rprovides.append(pypackage)

    d.setVar('RPROVIDES_class-native', ' '.join(rprovides))

    packages = d.getVar('PACKAGES').split()
    pn = d.getVar('PN')

    newpackages=[]
    for key in python_manifest:
        pypackage = pn + '-' + key
        pysrc = pypackage + '-src'

        if pysrc not in packages:
            # prepend the -src package first to get .py files
            newpackages.append(pysrc)

        if pypackage not in packages:
            # We need to prepend, otherwise python-misc gets everything
            # so we use a new variable
            newpackages.append(pypackage)

        # "Build" python's manifest FILES, RDEPENDS and SUMMARY
        d.setVar('FILES_' + pysrc, '')
        d.setVar('FILES_' + pypackage, '')
        for value in python_manifest[key]['files']:
            if value.endswith('.py'):
                d.appendVar('FILES_' + pysrc, ' ' + value)
                d.appendVar('FILES_' + pypackage, ' ' + value + 'c')
            elif value.endswith('.whl'):
                d.appendVar('FILES_' + pysrc, ' ' + value)
            else:
                d.appendVar('FILES_' + pypackage, ' ' + value)

        for value in python_manifest[key]['rdepends']:
            # Make it work with or without $PN
            if '${PN}' in value:
                value=value.split('-', 1)[1]
            d.appendVar('RDEPENDS_' + pypackage, ' ' + pn + '-' + value)

        for value in python_manifest[key].get('rrecommends', ()):
            if '${PN}' in value:
                value=value.split('-', 1)[1]
            d.appendVar('RRECOMMENDS_' + pypackage, ' ' + pn + '-' + value)

        d.setVar('RDEPENDS_' + pysrc, pypackage)
        d.setVar('SUMMARY_' + pysrc, pypackage + ' (source)')
        d.setVar('SUMMARY_' + pypackage, python_manifest[key]['summary'])

    # Prepending so to avoid python-misc getting everything
    packages = newpackages + packages
    d.setVar('PACKAGES', ' '.join(packages))
    d.setVar('ALLOW_EMPTY_${PN}-modules', '1')
    d.setVar('ALLOW_EMPTY_${PN}-pkgutil', '1')
}
