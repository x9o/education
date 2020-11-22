# Aufgabe 8:


import aclib
import sys


def broken_input(arguments):

    if len(arguments) != 4:  # Korrekte Anzahl der Argumente ueberpruefen (4, da Programmname auch als Argument zaehlt)
        print("Es muessen exakt 3 Argumente uebergeben werden, nicht ", len(arguments), ".")
        return True

    mode = arguments[1]
    key = arguments[2]

    if mode not in ['e', 'd']:  # Angabe des Modus richtig ?
        print("Modus muss 'e' wie Encrypt oder 'd' wie Decrypt sein.")
        return True
    elif len(key) != 2:  # Schluessellaenge richtig ?
        print("Schluessel muss ein String der Laenge 2 sein.")
        return True
    key_decoded = aclib.decode(key)  # Schluessel von String nach Zahlen umwandeln
    if aclib.broken_key(key_decoded[0], key_decoded[1]):  # Vorgegebenen Wertebereich des Schluessels eingehalten ?
        print("Schluessel liegt nicht im vorgegebenen Wertebereich")
        return True
    return False


def main():

    if broken_input(sys.argv):   # Kommandozeileninput korrekt nach Vorgabe ? Falls nein -> Programm beenden
        return

    mode = sys.argv[1]  # Variable für den Modus - e zum Verschluesseln - d zum Entschluesseln
    a = aclib.decode(sys.argv[2])[0]    # Erster Teil des Schluessels (a)
    b = aclib.decode(sys.argv[2])[1]    # Zweiter Teil des Schluessels (b)
    path = sys.argv[3]  # Pfad zu der zu behandelnden Datei

    lines = open(path).read().splitlines()   # Oeffnen der Datei und speichern von deren Inhalt in 'lines'

    if mode == 'e':  # Verschluesseln
        for element in lines:  # Ueber jede Zeile iterieren
            print(aclib.acEncrypt(a, b, element))  # Zeile mit angegebenem Schluessel verschluesseln
    elif mode == 'd':  # Entschluesseln
        for element in lines:  # Ueber jede Zeile iterieren
            print(aclib.acDecrypt(a, b, element))  # Zeile mit angegebenem Schluessel entschluesseln


main()
