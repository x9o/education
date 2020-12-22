#! /usr/bin/env python2

import sys

default_size=200

if len(sys.argv)==2:
	size=int(sys.argv[1])
	if (size<0):
		size=default_size
else:
	size=default_size

print "A"*size
