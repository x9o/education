import aclib
import ablib
import sys


def main():
    if len(sys.argv) != 2:
        print("Bitte gib genau einen Pfad an.")
        return

    lines = open(sys.argv[1]).read().splitlines()  # Oeffnen der Datei und speichern von deren Inhalt in 'lines'
    cipher_text = "".join(lines)  # Speichern der kompletten Datei in einem String

    freq_table = ablib.computeFrequencyTable(aclib.decode(cipher_text))  # Haeufigkeit der einzelnen Buchstaben zaehlen
    freq_chars = ablib.computeMostFrequentChars(freq_table, 6)  # Die 6 haeufigsten Zeichen raussuchen ..
    char_pairs = ablib.computeKeyPairs(freq_chars)  # .. und alle moeglichen Schluesselpaare daraus bilden

    ablib.analyzeCipherText(cipher_text[:50], char_pairs)  # Alle herausgefundenen Schluesselpaare dann ausprobieren


main()
