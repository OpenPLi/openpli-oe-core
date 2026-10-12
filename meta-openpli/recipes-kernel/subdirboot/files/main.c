/* ----------------------------------------------------------------------- *
 *
 *   Based on klibc run-init:
 *   Copyright 2004 H. Peter Anvin - All Rights Reserved
 *
 *   Permission is hereby granted, free of charge, to any person
 *   obtaining a copy of this software and associated documentation
 *   files (the "Software"), to deal in the Software without
 *   restriction, including without limitation the rights to use,
 *   copy, modify, merge, publish, distribute, sublicense, and/or
 *   sell copies of the Software, and to permit persons to whom
 *   the Software is furnished to do so, subject to the following
 *   conditions:
 *   
 *   The above copyright notice and this permission notice shall
 *   be included in all copies or substantial portions of the Software.
 *   
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *   OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *   HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *   WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *   FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *   OTHER DEALINGS IN THE SOFTWARE.
 *
 * ----------------------------------------------------------------------- */

#include <assert.h>
#include <dirent.h>
#include <errno.h>
#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/mount.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/vfs.h>
#include <sys/sysmacros.h>
#include <linux/magic.h>

static const char *program = NULL;

static void __attribute__((noreturn)) die(const char *msg)
{
	fprintf(stderr, "%s: %s: %s\n", program, msg, strerror(errno));
	exit(1);
}

static int nuke(const char *what);

static int nuke_dirent(int len, const char *dir, const char *name)
{
	int bytes = len+strlen(name)+2;
	char path[bytes];
	int xlen;

	/* These directories contain mounts created before switching root. */
	if (!strcmp(dir, "/") &&
	    (!strcmp(name, "dev") || !strcmp(name, "proc") ||
	     !strcmp(name, "mnt") || !strcmp(name, "root")))
		return 0;

	xlen = snprintf(path, bytes, "%s/%s", dir, name);
	assert(xlen < bytes);
	return nuke(path);
}

/* Wipe the contents of a directory, but not the directory itself */
static int nuke_dir(const char *what)
{
	int len = strlen(what);
	DIR *dir;
	struct dirent *d;
	int err = 0;
	if (!(dir = opendir(what))) {
		/* EACCES means we can't read it.  Might be empty and removable;
			 if not, the rmdir() in nuke() will trigger an error. */
		return (errno == EACCES) ? 0 : errno;
	}

	while ((d = readdir(dir))) {
		/* Skip . and .. */
		if (d->d_name[0] == '.' &&
	 (d->d_name[1] == '\0' ||
		(d->d_name[1] == '.' && d->d_name[2] == '\0')))
			continue;

		err = nuke_dirent(len, what, d->d_name);
		if (err) {
			closedir(dir);
			return err;
		}
	}

	closedir(dir);
	return 0;
}

static int nuke(const char *what)
{
	int rv;
	int err = 0;
	rv = unlink(what);
	if (rv < 0) {
		if (errno == EISDIR) {
			/* It's a directory. */
			err = nuke_dir(what);
			if (!err) err = rmdir(what) ? errno : err;
		} else {
			err = errno;
		}
	}
	if (err) {
		errno = err;
		die(what);
	} else {
		return 0;
	}
}

int main(int argc, char *argv[])
{
	struct statfs initramfs, rootfs;
	int o;
	int confd;

	const char *console = "/dev/console";
	const char *init = "/sbin/init";
	const char *rootdevicemountpoint = "/mnt";
	const char *rootmountpoint = "/root";
	const char *rootdevice = NULL;
	const char *rootfstype = "ext4";
	const char *rootflags = "";
	int rootdelay = 0;
	char rootpath[1024] = {0};
	FILE *cmdline;

	program = strdup(argv[0]);

	mkdir("/dev", 0777);
	mount("none", "/dev", "devtmpfs", 0, NULL);

	mkdir("/proc", 0777);
	mount("proc", "/proc", "proc", 0, NULL);

	confd = open(console, O_RDWR);
	ioctl(confd, TIOCSCTTY, 0);
	dup2(confd, 0);
	dup2(confd, 1);
	dup2(confd, 2);
	close(confd);

	cmdline = fopen("/proc/cmdline", "r");
	if (cmdline)
	{
		char *pch;
		char line[4096];
		fread(line, 1, sizeof(line), cmdline);
		pch = strtok(line," ");
		while (pch != NULL)
		{
			if (!strncmp(pch, "root=", 5))
			{
				rootdevice = strdup(pch + 5);
			}
			else if (!strncmp(pch, "rootfstype=", 11))
			{
				rootfstype = strdup(pch + 11);
			}
			else if (!strncmp(pch, "rootflags=", 10))
			{
				rootflags = strdup(pch + 10);
			}
			else if (!strncmp(pch, "rootdelay=", 10))
			{
				rootdelay = atoi(pch + 10);
			}
			else if (!strncmp(pch, "rootwait", 8))
			{
				rootdelay = -1;
			}
			else if (!strncmp(pch, "rootsubdir=", 11))
			{
				snprintf(rootpath, sizeof(rootpath), "%s/%s", rootdevicemountpoint, pch + 11);
			}
			else if (!strncmp(pch, "init=", 5))
			{
				init = strdup(pch + 5);
			}
			pch = strtok(NULL, " ");
		}
		fclose(cmdline);
	}

	argv[0] = (char*)init;

	if (rootdelay > 0)
	{
		sleep(rootdelay);
	}
	else if (rootdelay < 0 && rootdevice)
	{
		/* rootwait: wait till rootdevice exists */
		while (access(rootdevice, R_OK) < 0)
		{
			usleep(10000);
		}
	}

	if (rootpath[0] == 0)
	{
		/* no subdir, mount rootdevice directly on rootmountpoint */
		mkdir(rootmountpoint, 0777);
		mount(rootdevice, rootmountpoint, rootfstype, 0, NULL);
	}
	else
	{
		/* subdir, mount rootdevice on rootdevicemountpoint, then bind-mount subdir on rootmountpoint */
		mkdir(rootdevicemountpoint, 0777);
		mount(rootdevice, rootdevicemountpoint, rootfstype, 0, NULL);

		mkdir(rootmountpoint, 0777);
		mount(rootpath, rootmountpoint, NULL, MS_BIND, NULL);
	}

	/* First, change to the new root directory */
	if (chdir(rootmountpoint))
		die("chdir");
	/* This is a potentially highly destructive program.  Take some
		 extra precautions. */
	/* Make sure the current directory is not on the same filesystem
		 as the root directory */
	/* The initramfs should have /init */
	if (access("/init", X_OK))
		die("no /init");
	/* Make sure we're on a ramfs */
	if (statfs("/", &initramfs))
		die("statfs /");
	if (initramfs.f_type != RAMFS_MAGIC && initramfs.f_type != TMPFS_MAGIC)
		die("not an initramfs");
	if (statfs(".", &rootfs))
		die("statfs root");
	if (initramfs.f_type == rootfs.f_type &&
	    initramfs.f_fsid.val[0] == rootfs.f_fsid.val[0] &&
	    initramfs.f_fsid.val[1] == rootfs.f_fsid.val[1])
		die("still in root");
	/* Okay, I think we should be safe... */
	/* Delete rootfs contents */
	if (nuke_dir("/"))
		die("delete");
	/* Overmount the root */
	if (mount(".", "/", NULL, MS_BIND, NULL))
		die("mount");

	/* chroot, chdir */
	if (chroot(".") || chdir("/"))
		die("chroot");

	mknod(console, S_IFCHR | S_IRUSR | S_IWUSR, makedev(5, 1));

	/* Open /dev/console */
	if ((confd = open(console, O_RDWR)) < 0)
		die("console");
	dup2(confd, 0);
	dup2(confd, 1);
	dup2(confd, 2);
	close(confd);
	/* Spawn init */
	execv(init, argv);
	die(init); /* Failed to spawn init */
}
