#! /bin/sh
# This script is run once when your box boots for the first time. You can run
# it again later, but that may destroy settings that you did.
# Restore files from backup dir with the most recent timestamp

BACKUPDIR=/media/hdd
MACADDR=`cat /sys/class/net/eth0/address | cut -b 1,2,4,5,7,8,10,11,13,14,16,17`
SAMBACONF=/etc/samba/smb.conf

if [ "$1x" == "startx" ] || [ -z "$1" ]
then

# Make a safety backup of the smb.conf, we may need that later
cp ${SAMBACONF} ${SAMBACONF}.tmp

# Best candidate:
#  If a MAC Address dependent backup was found, use that
#  Always use the latest version
#  Prefer an older MAC address dependent backup to a newer one without it 
for candidate in `cut -d ' ' -f 2 /proc/mounts | grep '^/media'`
do
    if [ -d ${candidate}/backup ]
    then
        if [ ! -f ${BACKUPDIR}/backup/.timestamp ]
        then
            BACKUPDIR=${candidate}
        elif [ -f ${candidate}/backup/PLi-AutoBackup${MACADDR}.tar.gz ]
        then
            if [ ! -f ${BACKUPDIR}/backup/PLi-AutoBackup${MACADDR}.tar.gz ]
            then
                BACKUPDIR=${candidate}
            elif [ ${candidate}/backup/PLi-AutoBackup${MACADDR}.tar.gz -nt ${BACKUPDIR}/backup/PLi-AutoBackup${MACADDR}.tar.gz ]
            then
                BACKUPDIR=${candidate}
            fi
        elif [ ${candidate}/backup/.timestamp -nt ${BACKUPDIR}/backup/.timestamp ]
        then
            if [ ! -f ${BACKUPDIR}/backup/PLi-AutoBackup${MACADDR}.tar.gz ]
            then
                BACKUPDIR=${candidate}
            fi
        fi
   fi
done

if  [ ! -f ${BACKUPDIR}/backup/.timestamp ]
then
	echo "No valid backup location, aborting auto-restore"
	exit 0
fi

else
	# if first arg isn't 'start', its a directory name
	BACKUPDIR=$1
fi

if [ -f ${BACKUPDIR}/backup/PLi-AutoBackup${MACADDR}.tar.gz ]
then
	echo "Restoring from: ${BACKUPDIR}/backup/ for ${MACADDR}"
	tar -xzf ${BACKUPDIR}/backup/PLi-AutoBackup${MACADDR}.tar.gz -C /
elif [ -f ${BACKUPDIR}/backup/PLi-AutoBackup.tar.gz ]
then
	echo "Restoring from: ${BACKUPDIR}/backup/"
	tar -xzf ${BACKUPDIR}/backup/PLi-AutoBackup.tar.gz -C /
else
	echo "PLi-AutoBackup.tar.gz not found, attempting old backup"
	exec /etc/init.d/settings-restore.old.sh ${BACKUPDIR}
	exit 1
fi

echo ${BACKUPDIR} > /tmp/backupdir

if [ -s /tmp/fstab ]
then
	awk '!a[$0]++' /tmp/fstab /etc/fstab >/tmp/fstab.merged
	mv /tmp/fstab.merged /etc/fstab
	grep '/media/' /tmp/fstab | while read entry
	do
		# echo splits entry on whitespace, cut to get the second entry
		path=`echo $entry | cut -d ' ' -f 2`
		if [ ! -d $path ]
		then
			echo 'Creating:' $path
			mkdir -p $path
		fi
	done
fi
[ -s /tmp/crontab ] && crontab /tmp/crontab

mergerootpwd()
{
	grep "^root:" /tmp/passwd
	grep -v "^root:" /etc/passwd
}

if [ -f /tmp/passwd ]
then
	mergerootpwd > /tmp/passwds
	# QA check - we don't want a passwd file without root entry
	if grep -q "^root:" /tmp/passwds
	then
		cp /tmp/passwds /etc/passwd
	fi
	rm -f /tmp/passwds
fi

# When we restore a smb.conf from from Samba 3.x
if grep -q "netbios name" ${SAMBACONF}
then
	mv ${SAMBACONF} ${SAMBACONF}.old
	mv ${SAMBACONF}.tmp ${SAMBACONF}
else
	rm ${SAMBACONF}.tmp
fi

rm -f /tmp/crontab /tmp/passwd /tmp/fstab
