# This configures transcoding
#
# *** NOTE *** *** NOTE *** *** NOTE ***
#
# Changing any other entry (see /proc/stb/encoder/?) than listed here
# will ultimatily crash the encoder and/or the complete receiver.
# Simply don't do it.
#
# Using any other value than listed here
# will ultimatily crash the encoder and/or the complete receiver.
# Simply don't do it.
#
# This leaves only a very few settings to be configured, not worth
# a GUI plugin, so change them here.
#
# This file is marked as "config file" so it won't get overwritten
# on a package update.

# h264 profile, possible options: baseline/main/high, default=baseline
PROFILE="baseline"
# h264 level, possible options: 3.1/3.2/4.0, default=3.1
LEVEL="3.1"
# display size, possible options: 480p/576p/720p, default=480p
SIZE="480p"
# bitrate, default=2 Mbps (default), if unsure use this or -1 for high quality
BITRATE="2000000"
# max amount of b frames in a GOP, possible options: 0/1/2, default=0
BFRAMES="0"

cd /proc/stb/encoder
echo -n "enable" > enable

for encoder in 0 1
do
	if [ -d $encoder ]
	then
		cd $encoder
		echo -n "$PROFILE" > profile
		echo -n "$LEVEL" > level
		echo -n "$SIZE" > display_format
		echo -n "$BITRATE" > bitrate
		echo -n "$BFRAMES" > gop_frameb
		cd ..
	fi
done
