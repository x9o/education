#!/usr/bin/env python3
###############################################################################
## vim: et ai ts=4
##
## Bitte erst ab der Stelle im Code, die mit 'Hier beginnt Ihr Code' markiert
## ist, eigenen Code einfuegen.
## Aufgabe, Studenten und Gruppennummer müssen Sie nicht angeben.


###############################################################################
## Code fuer Aufgabe
import unittest
try:
    import loesung04 as uebung04
    print("Teste loesung04.py")
except:
    import uebung04
    print("Teste uebung04.py")

###############################################################################
## Hier beginnt Ihr Code


class MyUnitTests(unittest.TestCase):

    # Ein Beispiel Geruest fuer die TestCase Klasse
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_beispiel_aus_der_aufgabenstellung(self):
        # 50000 Euro Jahresgehalt, verheiratet, ein Kind
        (steuer, error) = uebung04.steuer(50000.0, 1, 1)

        # Teste Wert fuer steuer:
        # Sollwert 50000 * 0.25 * (1 - (0.2 + 0.1)) = 8750
        self.assertAlmostEqual(steuer, 8750.0)
        # steuer ist vom Typ float. assertAlmostEqual rundet die zu
        # vergleichenden Werte zunaechst auf 7 Nachkommastellen und
        # vergleicht dann die gerundeten Werte auf Gleichheit

        # Teste Wert fuer error:
        # Sollwert kein Fehler also 0
        self.assertEqual(error, 0)

    def test_bigamie_ist_nicht_erlaubt(self):
        # 150000 Euro Jahresgehalt, 2 * verheiratet, drei Kinder
        (steuer, error) = uebung04.steuer(150000.0, 2, 3)

        # Teste Wert fuer steuer:
        # Sollwert 0.0, da ungueltige Eingabe
        self.assertAlmostEqual(steuer, 0.0)

        # Teste Wert fuer error:
        # Sollwert ein Fehler also 1
        self.assertEqual(error, 1)


class SmokeTests(unittest.TestCase):

    def test_st1(self):
        (steuer, error) = uebung04.steuer(0.0, 0, 0)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)

    def test_st2(self):
        (steuer, error) = uebung04.steuer(1.0, 1, 1)
        self.assertAlmostEqual(steuer, 0.084)
        self.assertEqual(error, 0)

    def test_st3(self):
        (steuer, error) = uebung04.steuer(10000.0, 0, 0)
        self.assertAlmostEqual(steuer, 1200.0)
        self.assertEqual(error, 0)


class FirstTaxJump(unittest.TestCase):

    def test_ftj_before(self):
        (steuer, error) = uebung04.steuer(11999.999, 0, 0)
        self.assertAlmostEqual(steuer, 1439.9998800)
        self.assertEqual(error, 0)

    def test_ftj_at(self):
        (steuer, error) = uebung04.steuer(12000.0, 0, 0)
        self.assertAlmostEqual(steuer, 1440.0)
        self.assertEqual(error, 0)

    def test_ftj_after(self):
        (steuer, error) = uebung04.steuer(12000.001, 0, 0)
        self.assertAlmostEqual(steuer, 1800.00015)
        self.assertEqual(error, 0)


class SecondTaxJump(unittest.TestCase):

    def test_stj_before(self):
        (steuer, error) = uebung04.steuer(19999.999, 0, 0)
        self.assertAlmostEqual(steuer, 2999.9998500)
        self.assertEqual(error, 0)

    def test_stj_at(self):
        (steuer, error) = uebung04.steuer(20000.00, 0, 0)
        self.assertAlmostEqual(steuer, 3000.0)
        self.assertEqual(error, 0)

    def test_stj_after(self):
        (steuer, error) = uebung04.steuer(20000.001, 0, 0)
        self.assertAlmostEqual(steuer, 4000.0002000)
        self.assertEqual(error, 0)


class ThirdTaxJump(unittest.TestCase):

    def test_ttj_before(self):
        (steuer, error) = uebung04.steuer(29999.999, 0, 0)
        self.assertAlmostEqual(steuer, 5999.9998000)
        self.assertEqual(error, 0)

    def test_ttj_at(self):
        (steuer, error) = uebung04.steuer(30000.0, 0, 0)
        self.assertAlmostEqual(steuer, 6000.0)
        self.assertEqual(error, 0)

    def test_ttj_after(self):
        (steuer, error) = uebung04.steuer(30000.01, 0, 0)
        self.assertAlmostEqual(steuer, 7500.0025)
        self.assertEqual(error, 0)


class HighestTaxTier(unittest.TestCase):

    def test_htt_single_no_child(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, 0)
        self.assertAlmostEqual(steuer, 12500.0)
        self.assertEqual(error, 0)

    def test_htt_married_no_child(self):
        (steuer, error) = uebung04.steuer(50000.0, 1, 0)
        self.assertAlmostEqual(steuer, 10000.0)
        self.assertEqual(error, 0)

    def test_htt_single_child(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, 1)
        self.assertAlmostEqual(steuer, 11250.0)
        self.assertEqual(error, 0)

# already testet in MyUnitTest.
#    def test_htt_married_child(self):
#        (steuer, error) = uebung04.steuer(50000.0, 1, 1)
#        self.assertAlmostEqual(steuer, 0.0)
#        self.assertEqual(error, 0)


class TaxReductionToZeroMarriedCase(unittest.TestCase):

    def test_trtzmc_90_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 1, 7)
        self.assertAlmostEqual(steuer, 1250.0)
        self.assertEqual(error, 0)

    def test_trtzmc_100_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 1, 8)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)

    def test_trtzmc_110_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 1, 9)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)


class TaxReductionToZeroUnmarriedCase(unittest.TestCase):

    def test_trtzuc_90_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, 9)
        self.assertAlmostEqual(steuer, 1250.0)
        self.assertEqual(error, 0)

    def test_trtzuc_100_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, 10)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)

    def test_trtzuc_110_percent_reduction(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, 11)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)


class InvalidArguments(unittest.TestCase):

    def test_ia_bigamie(self):
        (steuer, error) = uebung04.steuer(50000.0, 2, 0)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 1)

    def test_ia_negativ_marriage(self):
        (steuer, error) = uebung04.steuer(50000.0, -2, 0)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 1)

    def test_ia_negativ_child(self):
        (steuer, error) = uebung04.steuer(50000.0, 0, -1)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 1)

    def test_ia_negativ_income(self):
        (steuer, error) = uebung04.steuer(-1.0, 0, 0)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 1)


###############################################################################

if __name__ == '__main__':  # Pragma No Cover

    unittest.main()
