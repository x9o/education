from string import ascii_lowercase
from string import ascii_uppercase

numbers = [i for i in range(26)]  # Zahlen in einer Liste von 0-25
decodeUpperDict = dict(zip(ascii_uppercase, numbers))  # Woerterbuch mit Grossbuchstaben als Keys
decodeLowerDict = dict(zip(ascii_lowercase, numbers))  # Woerterbuch mit Kleinbuchstaben als Keys
encodeLowerDict = dict(zip(numbers, ascii_lowercase))  # Woerterbuch mit Zahlen als Keys
lowercaseToUppercase = dict(zip(ascii_lowercase, ascii_uppercase))  # Woerterbuch zum Grossschreiben der Buchstaben

# Aufgabe 3:

key_table = {
    1: 1,
    3: 9,
    5: 21,
    7: 15,
    9: 3,
    11: 19,
    15: 7,
    17: 23,
    19: 11,
    21: 5,
    23: 17,
    25: 25
}  # Alle erlaubten Werte fuer Teilschluessel a mit zugehoerigen inversen Elementen


# Aufgabe 1:


def decode(text):
    clean_input = text  # Speichern des Texts als Liste
    decoded_input = list()  # Variable fuer die verschluesselte Liste

    for element in clean_input:  # Einmal ueber die ganze Liste iterieren
        if element in ascii_uppercase:  # Falls Grossbuchstabe
            decoded_input.append(
                decodeUpperDict.get(element))  # Eintragen des verschluesselten Werts in neue Liste
        elif element in ascii_lowercase:  # Falls Kleinbuchstabe
            decoded_input.append(
                decodeLowerDict.get(element))  # Eintragen des verschluesselten Werts in neue Liste

    return decoded_input  # Rueckgabe des verschluesselten Strings


# Aufgabe 2:


def encode(char_list):
    encoded_input = list()  # Variable fuer den entschluesselten String als Liste

    for element in char_list:  # Einmal ueber die gante Liste iterieren
        if 0 <= element < 26:  # Falls Zahl in Z26
            encoded_input.append(
                encodeLowerDict.get(element))  # Eintragen des entschluesselten Buchstabens in neue Liste

    tmp_str = ""  # Temporaerer String fuer Rueckgabe
    return tmp_str.join(encoded_input)  # Rueckgabe der entschluesselten Liste als String


def broken_key(a, b):  # Hilfsfunktion zum Testen der Schluessel
    if a not in key_table.keys() or b not in range(26):
        print("--- SCHLUESSEL FEHLERHAFT : Schluessel a = ", a, ", Schluessel b = ", b,
              " ---")  # Fehlermeldung fuer falschen Schluessel
        return True
    return False


# Aufgabe 4:


def acEncrypt(a, b, plain_text):
    orig_input = decode(list(plain_text))  # Eingabe als Zahlen in Liste formatieren
    encrypted_input = list()    # Leere Liste fuer verschluesselten String

    if broken_key(a, b):   # Testen der Schluessel: bei True Schluessel fehlerhaft
        return ""  # Rueckgabe eines leeren Strings falls Schluessel fehlerhaft

    for element in orig_input:   # Einmal fuer jedes Element die Verschluesselung durchfuehren
        x: int = element
        y_nr = ((a * x) + b) % 26   # Eigentliche Verschluesselung
        y = list()
        y.append(y_nr)
        encrypted_input.append(lowercaseToUppercase.get(encode(y)))  # Grossschreiben der einzelnen Buchstaben

    return "".join(encrypted_input)    # Rueckgabe der verschluesselten Liste als String


# Aufgabe 5:


def acDecrypt(a, b, cipher_text):
    orig_input = decode(list(cipher_text))  # Eingabe als Zahlen in Liste formatieren
    decrypted_input = list()    # Leere Liste fuer entschluesselten String

    if broken_key(a, b):  # Testen der Schluessel: bei True Schluessel fehlerhaft
        return ""  # Rueckgabe eines leeren Strings falls Schluessel fehlerhaft

    a_inv = key_table.get(a)    # Speichern des Inversen Elements zum Schluessel a

    for element in orig_input:   # Einmal fuer jedes Element die Entschluesselung durchfuehren
        y: int = element
        x = ((y - b) * a_inv % 26)   # Eigentliche Entschluesselung
        tmp = list()
        tmp.append(x)
        decrypted_input.append(encode(tmp))  # Zeichen als Buchstaben hinten an Liste hinzufuegen

    return "".join(decrypted_input)    # Rueckgabe der entschluesselten Liste als String


def main():

    # Aufgabe 6:

    print(acEncrypt(3, 1, "strenggeheim"))  # Verschluesseln mit dem Schluessel db - Entspricht 3, 1
    print(acDecrypt(15, 8, "IFFYVQMJYFFDQ"))   # Entschluesseln mit dem Schluessel pi - Entspricht 15, 8


# main()  # Zum Ausfuehren der Aufgabe 6 auskommentieren
