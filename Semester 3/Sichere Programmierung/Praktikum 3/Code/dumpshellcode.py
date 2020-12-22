#! /usr/bin/env python2

import sys

if len(sys.argv)!=2:
	print "Usage:", sys.argv[0], "<filename>"
	sys.exit(1)

f=open(sys.argv[1], "r")
shellcode=bytearray(f.read())
f.close()

s=""
for b in shellcode:
	s += "\\x{0:02x}".format(b) 	
print s
print "\nShellcode length:", len(shellcode), "Byte"
