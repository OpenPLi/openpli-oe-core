inherit deploy zgemma-legacy-deploy

do_deploy[prefuncs] += "zgemma_cleanup_legacy_deploy"
do_deploy_setscene[prefuncs] += "zgemma_cleanup_legacy_deploy"
