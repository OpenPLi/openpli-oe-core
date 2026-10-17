# Paths relative to zgemma-partitions-${MACHINE} written by old build tasks.
ZGEMMA_LEGACY_DEPLOY_FILES ?= ""

# Old builds wrote these files directly to DEPLOY_DIR_IMAGE without recording
# sstate ownership. Remove only this recipe's unowned legacy outputs before
# normal task execution or cache restoration. Keep files owned by any manifest so
# genuine provider conflicts are still reported by sstate.
python zgemma_cleanup_legacy_deploy() {
    import glob
    import os

    directory = os.path.normpath(os.path.join(d.getVar('DEPLOY_DIR_IMAGE'),
                                            'zgemma-partitions-' + d.getVar('MACHINE')))
    candidates = set()
    for relative in (d.getVar('ZGEMMA_LEGACY_DEPLOY_FILES') or '').split():
        path = os.path.normpath(os.path.join(directory, relative))
        if os.path.commonpath([directory, path]) != directory:
            bb.fatal('Legacy deployment path escapes the partition directory: ' + relative)
        if os.path.isfile(path) and not os.path.islink(path):
            candidates.add(path)
    if not candidates:
        return

    for manifest in glob.glob(os.path.join(d.getVar('SSTATE_MANIFESTS'), 'manifest-*')):
        if not os.path.isfile(manifest):
            continue
        try:
            with open(manifest) as stream:
                candidates.difference_update(line.rstrip('\n') for line in stream)
        except FileNotFoundError:
            # Another task may have cleaned its manifest during this scan.
            continue
        if not candidates:
            return

    for path in sorted(candidates):
        bb.note('Removing unowned legacy Zgemma deployment file: ' + path)
        bb.utils.remove(path)
}
