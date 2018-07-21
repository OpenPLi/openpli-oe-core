#!/bin/sh
echo ""
echo "Welcome to Persian Prince's OE cleanup script!"
echo "After using this script the size of the build folder will be reduced."
echo "Enter the machine name you want to cleanup:"
echo ""
read MACHINE
echo ""
echo "Removing $MACHINE build folders, please wait ..."
rm -rf build/tmp/buildstats/*
echo ""
echo "buildstats cleaned!"
rm -rf build/tmp/cache/default-glibc/$MACHINE
echo ""
echo "cache/default-glibc cleaned!"
rm -rf build/tmp/deploy/ipk/$MACHINE
echo ""
echo "deploy/ipk cleaned!"
rm -rf build/tmp/deploy/images/$MACHINE
echo ""
echo "deploy/images cleaned!"
rm -rf build/tmp/log/cooker/$MACHINE
echo ""
echo "log/cooker cleaned!"
rm -rf build/tmp/pkgdata/$MACHINE
echo ""
echo "pkgdata cleaned!"
rm -rf build/tmp/sstate-control/*$MACHINE*
echo ""
echo "sstate-control cleaned!"
rm -rf build/tmp/stamps/$MACHINE-oe-linux
echo ""
echo "stamps cleaned!"
rm -rf build/tmp/sysroots-components/$MACHINE
echo ""
echo "sysroots-components cleaned!"
rm -rf build/tmp/work/$MACHINE-oe-linux
echo ""
echo "work cleaned!"
rm -rf build/tmp/work-shared/$MACHINE
echo ""
echo "work-shared cleaned!"
echo "Done."
