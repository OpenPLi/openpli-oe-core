FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-fix-ssl3_ClientSendSessionTicketXtn-type.patch"
SRC_URI += "file://0002-fix-ssl_GenerateSelfEncryptKeys-type.patch"
SRC_URI += "file://0003-workaround-genericThread-dangling-pointer-error.patch"

pkg_postinst_${PN} () {
if [ -n "$D" ]; then
    for I in $D${libdir}/lib*.chk; do
        DN=`dirname $I`
        BN=`basename $I .chk`
        FN=$DN/$BN.so
        chktest $FN
        shlibsign -i $FN
        if [ $? -ne 0 ]; then
            echo "shlibsign -i $FN failed"
        fi
    done
fi
}
