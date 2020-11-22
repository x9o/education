import aclib


# Aufgabe 11:


def computeFrequencyTable(char_list):
    tmp_frequency_table = dict()  # Leeres Dictionary zum Werte speichern

    for element in char_list:  # Ueber komplette char_list iterieren
        if element in tmp_frequency_table:  # Wenn Buchstabe schonmal gezaehlt ..
            tmp_frequency_table[element] = tmp_frequency_table[element] + 1  # .. dann + 1 an dieser Stelle
        else:  # Wenn nicht ..
            tmp_frequency_table[element] = 1  # .. Key neu anlegen mit Startwert 1 (da erstes Mal gezaehlt)

    frequency_table = dict(zip(sorted(tmp_frequency_table), tmp_frequency_table.values()))  # Neues dict mit sorted Keys

    for element in tmp_frequency_table:  # Werte aus dem alten Dictionary in das neue uebertragen
        frequency_table[element] = tmp_frequency_table[element]

    return frequency_table  # Haeufigkeitstabelle zurueckgeben


# Aufgabe 12:


def printFrequencyTable(freq_table):
    for element in freq_table:  # Ueber gesamte Liste iterieren
        tmp = list()
        tmp.append(element)  # Aus Compiler-Gruenden ('int' object is not iterable) vorher Umwandlung in Liste
        print(aclib.encode(tmp), " : ", freq_table[element])  # Umwandlung der "linken" Seite zu Buchstaben


# Aufgabe 13:


def computeMostFrequentChars(freq_table, n):
    tmp = reversed(sorted(freq_table, key=freq_table.__getitem__))  # Nach Werten umsortierte Liste erstellen
    freq_list = list()  # Neue Liste zum Hinzufuegen der Daten erstellen

    for element in tmp:
        freq_list.append(element)  # Werte jeweils der neuen Liste hinzufuegen, darf aus Compiler-Gruenden kein
        # 'reversed' bleiben

    return freq_list[:n]  # Rueckgabe der ersten n Werte


# Aufgabe 14:


def computeKeyPairs(char_list):
    if len(char_list) < 2:  # Check, ob min. 2 Zahlen fuer 1 Paar vorhanden sind
        print("Kein Zahlenpaar moeglich, da zu wenig Input: ", char_list)
        return  # Wenn nicht -> Stopp des Programms

    pair_list = list()  # Liste zum Speichern der Paare

    for element1 in char_list:  # Erster Schleifendurchlauf - "Linke" Seite der Tupel
        for element2 in char_list:  # Erster Schleifendurchlauf - "Rechte" Seite der Tupel
            if element1 != element2:  # Check, ob beide Zahlen gleich sind
                pair_list.append((element1, element2))  # Falls nicht -> Hinzufuegen zur Paarliste

    return pair_list  # Rueckgabe der Paarliste


# Aufgabe 15:


def analyzeCipherText(cipher_text, char_pairs):
    for element in char_pairs:  # Jedes moegliche Schluesselpaar einmal probieren
        key_a = 3 * (element[1] - element[0]) % 26
        key_b = element[0] - (4 * key_a) % 26
        if not aclib.broken_key(key_a, key_b):  # Wenn Schluesselkombination moeglich ..
            print(aclib.acDecrypt(key_a, key_b, cipher_text[:50]), " --- Schluessel: a = ", key_a, ", b = ", key_b)
            # .. dann Geheimtext entschluesseln bis zu 50 Zeichen
