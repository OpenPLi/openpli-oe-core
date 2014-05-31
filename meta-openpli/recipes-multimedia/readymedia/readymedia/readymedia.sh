### BEGIN INIT INFO
# Provides:				readymedia
# Required-Start:		$networking
# Required-Stop:
# Default-Start:
# Default-Stop:			0 1 2 3 4 5 6
# Short-Description:	dlna server, formerly known as minidlna
### END INIT INFO

PATH=/sbin:/bin:/usr/sbin:/usr/bin
DAEMON=/usr/sbin/minidlnad
NAME=readymedia
DESC=readymedia

test -x "$DAEMON" || exit 0

case "$1" in

	start)
		echo -n "Starting ${DESC}: "
		start-stop-daemon -S -x "$DAEMON"
		echo "${NAME}."
		;;

	stop)
		echo -n "Stopping ${DESC}: "
		start-stop-daemon -K -x "$DAEMON"
		echo "${NAME}."
		;;

	restart)
		echo -n "Restarting ${DESC}: "
		start-stop-daemon -K -x "$DAEMON"
		sleep 1
		start-stop-daemon -S -x "$DAEMON"
		echo "${NAME}."
		;;

	reload)
		echo -n "Reloading ${DESC}: "
		start-stop-daemon -K -x "$DAEMON"
		sleep 1
		start-stop-daemon -S -x "$DAEMON" -- -R
		echo "${NAME}."
		;;

	*)
		echo "usage: /etc/init.d/${NAME} {start|stop|restart|reload}" >&2
		exit 1
		;;
esac

exit 0
