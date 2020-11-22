#!/usr/bin/env python3
###############################################################################
## vim: et ai ts=4
##
## Bitte erst ab der Stelle im Code, die mit 'Hier beginnt Ihr Code' markiert
## ist, eigenen Code einfuegen.

###############################################################################

Aufgabe = 4                    # Diesen Eintrag nicht veraendern,
                               # anderenfalls wird die Aufgabe nicht gewertet!!

Studenten = []                 # Initalisierung der Studentenliste

###############################################################################
## Bitte tragen Sie in die folgenden Variablen Ihre Gruppennummer und die
## Mitglieder Ihrer Gruppe ein. Bitte verwenden Sie KEINE Umlaute!

Gruppennummer = 99
# Syntax fuer die Angabe der Namen und Matrikelnummern der einzelen
# Gruppenmitglieder:
#
# Studenten.append({'matnr':12345, 'nachname':"NACHNAME1", 'vorname':"VORNAME1"})
# Studenten.append({'matnr':12346, 'nachname':"NACHNAME2", 'vorname':"VORNAME2"})
# Studenten.append({'matnr':12347, 'nachname':"NACHNAME3", 'vorname':"VORNAME3"})
# Studenten.append({'matnr':12348, 'nachname':"NACHNAME4", 'vorname':"VORNAME4"})
# Studenten.append({'matnr':12349, 'nachname':"NACHNAME5", 'vorname':"VORNAME5"})
Studenten.append({'matnr':99999, 'nachname':"Stigler", 'vorname':"Sebastian"})

###############################################################################
## Code fuer Aufgabe

###############################################################################
## Hier beginnt die Loesung
def steuer(bruttogehalt, verheiratet, kinder):
    assert isinstance(bruttogehalt, float)
    assert isinstance(verheiratet, int)
    assert isinstance(kinder, int)

    if (bruttogehalt < 0) or \
       (verheiratet not in [0, 1]) or \
       (kinder < 0):
        return (0.0, 1)

    steuersatz = 0.12
    if bruttogehalt > 12000:
        steuersatz += 0.03
    if bruttogehalt > 20000:
        steuersatz += 0.05
    if bruttogehalt > 30000:
        steuersatz += 0.05

    nachlass = min(1, float(verheiratet) * 0.2 + float(kinder) * 0.1)

    steuerlast = bruttogehalt * steuersatz * (1 - nachlass)

    return (steuerlast, 0)

###############################################################################
## Bitte erst innerhalb des folgenden if Blocks Funktionen aufrufen.
## Werden ausserhalb dieses Blocks Funktionen aufgerufen, so wird die Aufgabe
## nicht gewertet.

if __name__ == '__main__':  # pragma no cover

    ## Der folgende Funktionsaufruf prueft die Eintraege der Variablen
    ## Studenten, Gruppennummer und Aufgabe und gibt die Werte tabelarisch
    ## auf dem Bildschirm aus oder loest einen Fehler aus, falls die Form
    ## der Eintraege nicht korrekt ist.
    from Grading.Grading import *  # pragma no cover
    Grading.CheckStudents(Studenten, Gruppennummer, Aufgabe)  # pragma no cover
    
    ## Ab Hier kommen Ihre Aufrufe hin. 
    ## Schreiben Sie in jede der folgenden Zeilen den Kommentar 
    ## # pragma no cover
    ## am Ende mit dazu da Sie sonst nicht 100% Branch Coverage erreichen
    ## koennen. 
    ## In der Funktion steuer darf dieser Kommentar nicht verwendet werden!

    print(steuer(50000.0, 1, 1))  # pragma no cover 
