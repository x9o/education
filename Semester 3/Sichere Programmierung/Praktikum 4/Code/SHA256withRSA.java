import java.security.*;
import java.util.Base64;

public class SHA256withRSA extends rsa {

    // Speichern des Signaturobjekts als Instanzvariable
    Signature rsa;

    // Text, der signiert werden soll
    private static final String plainText = "Kryptographie macht immer noch Spass!!!";

    /**
     * Konstruktor für Erzeugung des Schlüsselpaars und setzen des Signaturmodus
     */
    public SHA256withRSA() throws NoSuchAlgorithmException {

        // Konstruktor der Superklasse aufrufen, um Schlüsselpaar zu generieren
        super();

        // Setzen des Signaturmodus auf SHA256withRSA
        rsa = Signature.getInstance("SHA256withRSA");

    }

    /**
     * Signieren eines übergebenen Texts
     * @param plainText String, der signiert werden soll
     * @return Base64 kodierte Signatur
     */
    public String sign(String plainText) throws SignatureException, InvalidKeyException {

        // Signature Objekt rsa sagen, dass es mit privateKey signieren soll
        rsa.initSign(super.privateKey);

        // Erstellen eines leeren Arrays für den Rückgabewert der sign() Methode
        byte[] signature;

        // Plaintext in Byte-Form an Signaturobjekt übergeben
        rsa.update(plainText.getBytes());

        // Eigentliche Signatur durchführen und Rückgabewert in signature speichern
        signature = rsa.sign();

        // String Base64 kodieren und an die main-Methode zurückliefern
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(signature);
    }

    /**
     * Methode, um Signatur über einen String zu überprüfen
     * @param plainText String, der signiert wurde
     * @param signature Signatur, die überprüft werden soll in Base64 Kodierung
     * @return true : Signatur verifiziert --- false : Signatur fehlerhaft
     */
    public boolean verify(String plainText, String signature)
            throws InvalidKeyException, SignatureException {

        // Signatur wieder dekodieren und in Byteform speichern
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decSignature = decoder.decode(signature);

        // Modus des Signaturobjekts rsa auf Verify setzen und den benötigten publicKey übergeben
        rsa.initVerify(super.publicKey);

        // Signaturobjekt mit dem Text, dessen Signatur überprüft werden soll in Byteform aktualisieren
        rsa.update(plainText.getBytes());

        // Und anschließend die Signatur damit überprüfen
        // Rückgabewert der verify-Methode bei erfolgreicher Verifikation ist true, sonst false
        return rsa.verify(decSignature);

    }

    /**
     * Main Funktion um Programm zu testen
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // Objekt der SHA256withRSA rsa erzeugen
        SHA256withRSA sha = new SHA256withRSA();

        // Ausgeben des plainTexts
        System.out.println("Plain Text:    " + plainText);

        // Signieren und Ergebnis ausgeben
        System.out.println("Signatur:      " + sha.sign(plainText));

        // Signatur verifizieren
        System.out.println("Verifiziert:   " + sha.verify(plainText, sha.sign(plainText)));

    }

}
