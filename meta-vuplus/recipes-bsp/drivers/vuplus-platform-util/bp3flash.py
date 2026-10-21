#!/usr/bin/python3
# Adapted for the current mmc-utils RPMB stdin/stdout interface.
# Original BP3 partition and recovery flow retained.

import glob
import os
import struct
import subprocess
import sys

RPMB_KEY = b"VUPLUSDUO4KVUPLUSDUO4KVUPLUSDUO4"
RPMB_DATA_SIZE = 256
RPMB_START_OFFSET = 2
RPMB_MAGIC_NUMBER = 20181128

DEBUG_ENABLE = False


def bp3Log(s):
    if DEBUG_ENABLE:
        print(s)


class rpmbUpdate:
    def __init__(self):
        pass

    def _run_mmc(self, cmd, stdin_data):
        """Run mmc-utils; return (stdout, error). Keep binary stdin/stdout."""
        bp3Log("[mmc] cmd: %s" % " ".join(cmd))
        try:
            p = subprocess.run(
                cmd,
                input=stdin_data,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                check=False,
            )
        except OSError as exc:
            return b"", str(exc).encode("utf-8", "replace")

        bp3Log("[mmc] rc=%d stdout=%d stderr=%d" %
               (p.returncode, len(p.stdout), len(p.stderr)))
        if p.returncode != 0:
            # mmc-utils versions may print diagnostics to stdout or stderr.
            detail = p.stderr or p.stdout or (
                "mmc exited with status %d" % p.returncode
            ).encode()
            return p.stdout, detail
        return p.stdout, b""

    def readSubprocess(self, cmd):
        # read-block writes block data to stdout and reads the key from stdin.
        return self._run_mmc(cmd, RPMB_KEY)

    def writeSubprocess(self, cmd, data=None):
        # write-block consumes exactly 256 data bytes, then 32 key bytes.
        # write-key consumes only the 32-byte key.
        payload = (data or b"") + RPMB_KEY
        return self._run_mmc(cmd, payload)

    def getDataFromFile(self, aFile, size):
        dlist = []
        total_size = 0
        with open(aFile, "rb") as fd:
            while True:
                data = fd.read(size)
                if not data:
                    break
                dlist.append(data)
                total_size += len(data)
        return dlist, total_size

    def registerRpmbKey(self):
        arg = ["mmc", "rpmb", "write-key", "/dev/mmcblk0rpmb", "-"]
        out, err = self.writeSubprocess(arg)
        if err:
            bp3Log("[registerRpmbKey] failed: %s" % err.decode("utf-8", "replace"))
            return False
        return True

    def zeroPadRpmbBlock(self, inData):
        if len(inData) > RPMB_DATA_SIZE:
            raise ValueError("RPMB block data exceeds 256 bytes")
        return inData + b"\x00" * (RPMB_DATA_SIZE - len(inData))

    def writeRpmb(self, fn):
        if not os.path.exists(fn):
            bp3Log("file does not exist: %s" % fn)
            return False
        if os.stat(fn).st_size > 0x7FFFFFFF:
            bp3Log("Program does not support files larger than 2 GiB.")
            return False

        fn_data, total_size = self.getDataFromFile(fn, RPMB_DATA_SIZE)
        bp3Log("[writeRpmb] %s: total %d bytes" % (fn, total_size))

        # RPMB addresses are block addresses, not byte offsets.
        cur_addr = RPMB_START_OFFSET
        metadata = struct.pack("<ii", RPMB_MAGIC_NUMBER, total_size)
        out, err = self.writeSubprocess(
            ["mmc", "rpmb", "write-block", "/dev/mmcblk0rpmb",
             hex(cur_addr), "-", "-"],
            self.zeroPadRpmbBlock(metadata),
        )
        if err:
            bp3Log("[writeRpmb] metadata write failed: %s" %
                   err.decode("utf-8", "replace"))
            return False

        cur_addr += 1
        for block in fn_data:
            out, err = self.writeSubprocess(
                ["mmc", "rpmb", "write-block", "/dev/mmcblk0rpmb",
                 hex(cur_addr), "-", "-"],
                self.zeroPadRpmbBlock(block),
            )
            if err:
                bp3Log("[writeRpmb] block %s write failed: %s" %
                       (hex(cur_addr), err.decode("utf-8", "replace")))
                return False
            cur_addr += 1
        return True

    def readRpmb(self, fn):
        cur_addr = RPMB_START_OFFSET
        cmd = ["mmc", "rpmb", "read-block", "/dev/mmcblk0rpmb",
               hex(cur_addr), "1", "-", "-"]
        data, err = self.readSubprocess(cmd)
        if err:
            bp3Log("[readRpmb] metadata read failed: %s" %
                   err.decode("utf-8", "replace"))
            return False
        if len(data) != RPMB_DATA_SIZE:
            bp3Log("[readRpmb] expected 256 metadata bytes, got %d" % len(data))
            return False

        magic_number, data_size = struct.unpack("<ii", data[:8])
        bp3Log("[readRpmb] magic=%d size=%d" % (magic_number, data_size))
        if magic_number != RPMB_MAGIC_NUMBER or data_size < 0 or data_size > 0x7FFFFFFF:
            bp3Log("[readRpmb] invalid RPMB metadata")
            return False

        remaining = data_size
        cur_addr += 1
        try:
            with open(fn, "wb") as outfd:
                while remaining > 0:
                    cmd = ["mmc", "rpmb", "read-block", "/dev/mmcblk0rpmb",
                           hex(cur_addr), "1", "-", "-"]
                    block, err = self.readSubprocess(cmd)
                    if err:
                        bp3Log("[readRpmb] block %s read failed: %s" %
                               (hex(cur_addr), err.decode("utf-8", "replace")))
                        return False
                    if len(block) != RPMB_DATA_SIZE:
                        bp3Log("[readRpmb] expected 256 bytes at %s, got %d" %
                               (hex(cur_addr), len(block)))
                        return False
                    take = min(remaining, RPMB_DATA_SIZE)
                    outfd.write(block[:take])
                    remaining -= take
                    cur_addr += 1
        except OSError as exc:
            bp3Log("[readRpmb] output file error: %s" % exc)
            return False
        return True

    def checkMagicNumber(self):
        cmd = ["mmc", "rpmb", "read-block", "/dev/mmcblk0rpmb",
               hex(RPMB_START_OFFSET), "1", "-", "-"]
        data, err = self.readSubprocess(cmd)
        if err:
            bp3Log("[checkMagicNumber] read failed: %s" %
                   err.decode("utf-8", "replace"))
            return False
        if len(data) != RPMB_DATA_SIZE:
            bp3Log("[checkMagicNumber] expected 256 bytes, got %d" % len(data))
            return False
        magic_number, = struct.unpack("<i", data[:4])
        bp3Log("[checkMagicNumber] magic=%d" % magic_number)
        return magic_number == RPMB_MAGIC_NUMBER


BP3_PART_MOUNTPOINT = "/tmp/bp3/"
BP3_BIN_NAME = "bp3.bin"
BP3_INST_PRE_PATH = "/tmp/bp3/"
OLD_BP3_INST_PRE_PATH = "/usr/local/bp3/"
BP3_INST_PATH = BP3_INST_PRE_PATH + BP3_BIN_NAME
BP3_LINK_PATH = "/" + BP3_BIN_NAME


def runCmd(cmd):
    bp3Log("CMD: %s" % cmd)
    return os.system(cmd)


def runPopen(cmd):
    bp3Log("CMD: %s" % cmd)
    return os.popen(cmd).read()


def isExist(f):
    return os.access(f, os.F_OK)


def isLink(f):
    return os.path.islink(f)


class bp3Partition:
    def __init__(self, part_name=None):
        self.partName = part_name
        self.bp3 = None
        self.part = None
        self.prepare()

    def info(self):
        bp3Log("[%s] bp3: %s, part: %s, mounted: %s, bp3bin: %s" %
               (self.partName, self.bp3, self.part, self.isMount(), self.hasBp3Bin()))

    def prepare(self):
        self.findBp3()
        self.doMount()

    def hasBp3Bin(self):
        return self.isMount() and os.access(
            "%s/%s" % (self.getMountPoint(), BP3_BIN_NAME), os.F_OK)

    def getMountPoint(self):
        return BP3_PART_MOUNTPOINT + self.partName

    def findBp3(self):
        self.bp3 = None
        self.part = None
        devs = glob.glob("/dev/mmcblk?")
        for dev in devs:
            data = runPopen("sgdisk -p %s" % dev)
            for line in data.splitlines():
                if self.partName in line:
                    self.bp3 = line.split()[0].strip()
                    self.part = runPopen("ls /dev/mmcblk*p%s" % self.bp3).strip()
                    return

    def bp3Mkfs(self):
        runCmd("mke2fs %s > /dev/null 2>&1" % self.part)
        runCmd("tune2fs -m 0 %s > /dev/null 2>&1" % self.part)
        self.doMount()

    def isMount(self):
        return os.path.ismount(self.getMountPoint())

    def doUmount(self):
        if self.isMount():
            runCmd("umount %s" % self.getMountPoint())
            return not self.isMount()
        return True

    def doMount(self):
        if not self.isMount() and self.part:
            runCmd("mkdir -p %s" % self.getMountPoint())
            runCmd("mount %s %s > /dev/null 2>&1" %
                   (self.part, self.getMountPoint()))
        return self.isMount()

    def bp3Fsck(self):
        runCmd("sync")
        return runCmd("e2fsck -y %s > /dev/null 2>&1" % self.part) == 0

    def updateBp3Bin(self, bp3bin):
        if not self.isMount():
            self.bp3Mkfs()
            self.doMount()
        if self.isMount():
            runCmd("cp %s %s" % (bp3bin, self.getMountPoint()))


def bp3Main(newBp3Path=None):
    rpmb = rpmbUpdate()
    rpmb_checkok = rpmb.checkMagicNumber()
    bp30 = bp3Partition("bp30")
    bp31 = bp3Partition("bp31")
    bp30.info()
    bp31.info()
    bp30_mp = bp30.getMountPoint()
    bp31_mp = bp31.getMountPoint()

    if bp30.bp3 is None:
        return

    if newBp3Path and isExist(newBp3Path) and not isLink(newBp3Path):
        bp30.updateBp3Bin(newBp3Path)
        bp31.updateBp3Bin(newBp3Path)
        runCmd("mv %s %s" % (newBp3Path, BP3_INST_PATH))
    else:
        if not bp30.hasBp3Bin() and bp31.hasBp3Bin():
            bp30.updateBp3Bin("%s/%s" % (bp31_mp, BP3_BIN_NAME))
        elif bp30.hasBp3Bin() and not bp31.hasBp3Bin():
            bp31.updateBp3Bin("%s/%s" % (bp30_mp, BP3_BIN_NAME))
        elif not bp30.hasBp3Bin() and not bp31.hasBp3Bin():
            if isExist(BP3_INST_PATH):
                bp30.updateBp3Bin(BP3_INST_PATH)
                bp31.updateBp3Bin(BP3_INST_PATH)

    if not isExist(BP3_INST_PRE_PATH):
        runCmd("mkdir -p %s" % BP3_INST_PRE_PATH)

    if not isExist(BP3_INST_PATH):
        if bp30.hasBp3Bin():
            runCmd("cp %s/%s %s" % (bp30_mp, BP3_BIN_NAME, BP3_INST_PATH))
        elif bp31.hasBp3Bin():
            runCmd("cp %s/%s %s" % (bp31_mp, BP3_BIN_NAME, BP3_INST_PATH))

    if isExist(BP3_LINK_PATH):
        if not isLink(BP3_LINK_PATH):
            if not isExist(BP3_INST_PATH):
                bp30.updateBp3Bin(BP3_LINK_PATH)
                bp31.updateBp3Bin(BP3_LINK_PATH)
                runCmd("mv %s %s" % (BP3_LINK_PATH, BP3_INST_PATH))
            else:
                runCmd("rm %s" % BP3_LINK_PATH)
        elif os.path.realpath(BP3_LINK_PATH) != os.path.realpath(BP3_INST_PATH):
            runCmd("rm %s" % BP3_LINK_PATH)

    if not bp30.hasBp3Bin() and not bp31.hasBp3Bin():
        if rpmb_checkok:
            print("get data from rpmb..")
            if rpmb.readRpmb(BP3_INST_PATH):
                bp30.updateBp3Bin(BP3_INST_PATH)
                bp31.updateBp3Bin(BP3_INST_PATH)
        else:
            print("Warning!!!!!!!!! we lost b3data!")

    if not isExist(BP3_LINK_PATH) and isExist(BP3_INST_PATH):
        runCmd("ln -sf %s %s" % (BP3_INST_PATH, BP3_LINK_PATH))

    if not rpmb_checkok and isExist(BP3_INST_PATH):
        print("backup %s to rpmb." % BP3_INST_PATH)
        # This is a one-time-programmable RPMB operation if no key is registered.
        # Preserve original behavior, but do not attempt registration unless key is 32 bytes.
        if len(RPMB_KEY) != 32:
            print("ERROR: RPMB key must be exactly 32 bytes; refusing key registration.")
        elif rpmb.registerRpmbKey():
            rpmb.writeRpmb(BP3_INST_PATH)

    if isExist(OLD_BP3_INST_PRE_PATH):
        runCmd("rm -rf %s" % OLD_BP3_INST_PRE_PATH)

    bp30.doUmount()
    bp31.doUmount()
    runCmd("sync")


if __name__ == "__main__":
    newBp3Bin = None
    if len(sys.argv) == 2 and isExist(sys.argv[1]):
        newBp3Bin = sys.argv[1]
    bp3Main(newBp3Bin)
