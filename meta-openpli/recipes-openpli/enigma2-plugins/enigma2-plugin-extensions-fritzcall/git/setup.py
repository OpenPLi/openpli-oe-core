from distutils.core import setup
import setup_translate


pkg='Extensions.FritzCall'
setup(name='enigma2-plugin-extensions-fritzcall',
		author='DrMichael',
		author_email='drmichael@freenet.de',
		package_dir={pkg: 'src'},
		packages=[pkg],
		package_data={pkg: ['*.png', '*/*.png', '*/*/*.png', '*.dat', '*.info', '*.xml', 'LICENSE', 'locale/*/LC_MESSAGES/*.mo']},
		description='Display FRITZ!box-Fon calls on screen',
		cmdclass=setup_translate.cmdclass
	)
