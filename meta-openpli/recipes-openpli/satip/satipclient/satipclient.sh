#!/bin/sh

if ! [ -x /usr/sbin/satip-client ]; then
	exit 0
fi


case "$1" in
	start)
		start-stop-daemon -S -b -x /usr/sbin/satip-client
		;;
	stop)
		start-stop-daemon -K -x /usr/sbin/satip-client
		;;
	restart|reload)
		$0 stop
		sleep 3
		$0 start
		;;
	*)
		echo "Usage: $0 {start|stop|restart}"
		exit 1
		;;
esac

exit 0
