#!/bin/sh
# Outputs list of valid MACHINE values. Run from
# repository root.
for c in meta-*/conf/machine/*.conf
do
	basename -s .conf $c
done
