import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

public class rsa {

    // Variablen um private sowie public Key nach Erzeugung abzuspeichern
    protected PrivateKey privateKey;
    protected PublicKey publicKey;

    // Klartext, der verschlüsselt werden soll
    private static final String plainText = "Kryptographie macht immer noch Spass!!!";


    /**
     * Konstruktor für Erzeugung des Schlüsselpaars
     */
    public rsa() throws NoSuchAlgorithmException {

        // Empfohlene Schlüssellänge des BSI für RSA-Verschlüsselung
        int keysize = 2000;

        // Instanziierung des Schlüsselpaar-Generators für das RSA-Verfahren
        KeyPairGenerator keyPairGen;
        keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Initialisierung mit oben angegebener Schlüssellänge
        keyPairGen.initialize(keysize);

        // Erstellen des RSA-Schlüsselpaars
        KeyPair rsaKeyPair = keyPairGen.generateKeyPair();

        // Variablen privateKey und publicKey aus dem Key-Pair auslesen und setzen
        this.publicKey = rsaKeyPair.getPublic();
        this.privateKey = rsaKeyPair.getPrivate();

    }


    /**
     * Verschlüsseln des Übergebenen Strings mit vorher erzeugtem pubKey
     * @param plainText String, der verschlüsselt werden soll
     * @return Verschlüsselter und Base64 kodierter String
     */
    public String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher rsa;

        // Setzen des CipherObjekts / Verschlüsselungsmodus auf RSA
        rsa = Cipher.getInstance("RSA");

        // Initialisieren des RSA-Ciphers mit vorher erzeugtem pubKey und dem Modus ENCRYPT_MODE
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);

        // Leeres Array für return-Value der eigentlichen Verschlüsselung erstellen
        byte[] cipherText;

        // Eigentliche Verschlüsselung durchführen (doFinal mit dem plainText aufrufen und returnValue in cipherText speichern)
        cipherText = rsa.doFinal(plainText.getBytes());

        // Erstellen eines Base64 Encoders (siehe VorlesungsPDF)
        Base64.Encoder encoder = Base64.getEncoder();

        // Zurückgeben des Verschlüsselten und Kodierten PlainTexts
        return encoder.encodeToString(cipherText);
    }

    /**
     * Methode um verschlüsselten Text wieder zu entschlüsseln
     * @param encryptedText RSA-verschlüsselter Text
     * @return Entschlüsselter PlainText
     */
    private String decrypt(String encryptedText) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher rsa;

        // Setzen des CipherObjekts / Verschlüsselungsmodus auf RSA
        rsa = Cipher.getInstance("RSA");

        // Erstellen eines Base64 Decoders, da der String ja kodiert ist
        Base64.Decoder decoder = Base64.getDecoder();

        // Dekodierten String in byte-Array speichern um Entschlüsselung damit durchzuführen
        byte[] cipherText = decoder.decode(encryptedText);

        // Initialisieren des RSA-Ciphers mit vorher erzeugtem privateKey und dem Modus DECRYPT_MODE
        rsa.init(Cipher.DECRYPT_MODE, privateKey);

        // Eigentliche Entschlüsselung durchführen (doFinal mit cipherText aufrufen und returnValue wieder in cipherText speichern)
        cipherText = rsa.doFinal(cipherText);

        // Entschlüsselten Text als String zurückgeben
        return new String(cipherText);

    }

    /**
     * Main Funktion um Programm zu testen
     */
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, SignatureException {

        // Objekt der Klasse rsa erzeugen
        rsa rsa = new rsa();

        // Ausgeben des plainTexts
        System.out.println("Plain Text:    " + plainText);

        // Verschlüsseln und Ergebnis ausgeben
        System.out.println("Verschlüsselt: " + rsa.encrypt(plainText));

        // Text verschlüsseln und danach wieder entschlüsseln und ausgeben
        System.out.println("Entschlüsselt: " + rsa.decrypt(rsa.encrypt(plainText)));

        // Test, ob Text nach Ver- und Entschlüsseln immer noch gleich ist
        System.out.print("plainText und verschlüsselter -> entschlüsselter Text gleich : ");
        System.out.println(plainText.equals(rsa.decrypt(rsa.encrypt(plainText))));

    }

}
