#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/slab.h>
#include <linux/string.h>
#include <linux/timer.h>
#include <linux/major.h>
#include <linux/fs.h>
#include <linux/err.h>
#include <linux/ioctl.h>
#include <linux/init.h>
#include <linux/proc_fs.h>
#include <linux/version.h>
#if LINUX_VERSION_CODE > KERNEL_VERSION(3,10,1)
#include <linux/seq_file.h>
#endif

static struct proc_dir_entry *proc_enigma2module;

DEFINE_MUTEX(enigma2module_table_mutex);

#if LINUX_VERSION_CODE < KERNEL_VERSION(3,10,1)
static int enigma2module_read_proc (char *page, char **start, off_t off, int count, int *eof, void *data_unused)
{
        int len;
        off_t   begin = 0;

        mutex_lock(&enigma2module_table_mutex);

        len = sprintf(page, "@MACHINE@\n");
        mutex_unlock(&enigma2module_table_mutex);
        if (off >= len+begin)
                return 0;
        *start = page + (off-begin);
        return ((count < begin+len-off) ? count : begin+len-off);
}

static int __init init_enigma2module(void)
{

		if ((proc_enigma2module = create_proc_entry( "stb/info/enigma2model", 0666, NULL )))
                proc_enigma2module->read_proc = enigma2module_read_proc;
        return 0;
}

static void __exit cleanup_enigma2module(void)
{
        remove_proc_entry( "stb/info/enigma2model", NULL);
}
#else
static int proc_enigma2module_show(struct seq_file *seq, void *v)
{
        seq_printf(seq, "@MACHINE@\n");
        return 0;
}

int proc_enigma2module_open(struct inode *inode, struct file *file)
{ 
	return single_open(file, proc_enigma2module_show, PDE_DATA(inode));
}

static const struct file_operations proc_fops = {
	.owner		= THIS_MODULE,
	.open		= proc_enigma2module_open,
	.read		= seq_read,
	.llseek		= seq_lseek,
	.release	= single_release,
};

static int __init init_enigma2module(void)
{
	printk(KERN_INFO "STB=@MACHINE@\n");
	proc_enigma2module = proc_create_data( "stb/info/enigma2model", 0666, NULL, &proc_fops, NULL );
        return 0;
}

static void __exit cleanup_enigma2module(void)
{
        remove_proc_entry( "stb/info/enigma2model", NULL);
}
#endif
module_init(init_enigma2module);
module_exit(cleanup_enigma2module);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Enigma2 developers");
MODULE_DESCRIPTION("Enigma2 model information");
