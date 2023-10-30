from distutils.core import setup
import setup_translate


pkg='Extensions.serienrecorder'
setup(name='enigma2-plugin-extensions-serienrecorder',
		author='einfall, w22754, egn und MacDisein',
		author_email='',
		package_dir={pkg: 'src'},
		packages=[pkg],
		package_data={pkg: ['*.png', '*.xml', 'images/*.png', 'AdvancedHTMLParser/*', 'skins/*.xml', 'skins/*/*.xml', 'skins/*/images/*.png', 'locale/*/LC_MESSAGES/*.mo']},
		description='The SeriesRecorder plug-in makes it easier to record series by automatically creating timers for selected series.',
		cmdclass=setup_translate.cmdclass
	)
