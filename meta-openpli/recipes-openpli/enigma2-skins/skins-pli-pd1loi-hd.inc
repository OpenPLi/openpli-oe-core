pkg_postinst_${PN}_append () {
echo "Installing converter and renderer"
cd /tmp/converter
for converter in *
do
        if [ -f /usr/lib/enigma2/python/Components/Converter/$converter ]
                then
                        echo "Converter: $converter already installed"
                else
                        cp -p $converter /usr/lib/enigma2/python/Components/Converter/
                        echo "Converter: $converter installed"
        fi
done

cd /tmp/renderer
for renderer in *
do
        if [ -f /usr/lib/enigma2/python/Components/Renderer/$renderer ]
                then
                        echo "Renderer: $renderer already installed"
                else
                        cp -p $renderer /usr/lib/enigma2/python/Components/Renderer/
                        echo "Renderer: $renderer installed"
        fi
done

cd /tmp
rm -rf /tmp/converter
rm -rf /tmp/renderer

echo " "
echo "###################################"
echo "### Skin successfully installed ###"
echo "###     PLEASE RESTART GUI      ###"
echo "###################################"
exit 0

}


