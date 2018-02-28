#!/bin/sh

smbd=/usr/sbin/smbd
test -x "$smbd" || exit 0
nmbd=/usr/sbin/nmbd
test -x "$nmbd" || exit 0

case "$1" in
  start)
    echo -n "Starting Samba: smbd"
    start-stop-daemon --start --quiet --exec $smbd
    echo -n " nmbd"
    start-stop-daemon --start --quiet --exec $nmbd
    echo "."
    ;;
  stop)
    echo -n "Stopping Samba: smbd"
    start-stop-daemon --stop --quiet --pidfile /run/smbd.pid
    echo -n " nmbd"
    start-stop-daemon --stop --quiet --pidfile /run/nmbd.pid
    echo "."
    ;;
  reload|force-reload)
    start-stop-daemon --stop --quiet --signal 1 --exec $smbd
    start-stop-daemon --stop --quiet --signal 1 --exec $nmbd
    ;;
  restart)
    $0 stop
    echo -n "Waiting for samba processes to die off"
    for i in 1 2 3 ;
    do
        sleep 1
        echo -n "."
    done
    echo ""
    $0 start
    ;;
  *)
    echo "Usage: /etc/init.d/samba {start|stop|reload|restart|force-reload}"
    exit 1
esac

