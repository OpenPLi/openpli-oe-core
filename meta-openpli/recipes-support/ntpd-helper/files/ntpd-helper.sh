#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
DAEMON=/usr/sbin/ntpd
NAME=ntpd-helper
DESC="Busybox NTP Daemon"
source /etc/ntpd-helper.conf
ARGS="-q -p $PEER"

test -f $DAEMON || exit 0

set -e

case "$1" in
	start)
		echo -n "* starting $DESC: $NAME... "
		start-stop-daemon -S -x $DAEMON -- $ARGS &
		echo "done."
	;;
	stop)
		echo -n "* stopping $DESC: $NAME... "
		start-stop-daemon -K -x $DAEMON -- $ARGS
		echo "done."
	;;
	restart|reload)
		echo -n "* restarting $DESC: $NAME... "
		$0 stop
		$0 start
		echo "done."
	;;
	*)
		echo "Usage: $0 {start|stop|restart}"
		exit 1
	;;
esac

exit 0
