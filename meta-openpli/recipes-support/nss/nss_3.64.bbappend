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
