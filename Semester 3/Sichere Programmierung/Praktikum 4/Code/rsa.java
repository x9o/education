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

    // Klartext, der verschl�sselt werden soll
    private static final String plainText = "Kryptographie macht immer noch Spass!!!";


    /**
     * Konstruktor f�r Erzeugung des Schl�sselpaars
     */
    public rsa() throws NoSuchAlgorithmException {

        // Empfohlene Schl�ssell�nge des BSI f�r RSA-Verschl�sselung
        int keysize = 2000;

        // Instanziierung des Schl�sselpaar-Generators f�r das RSA-Verfahren
        KeyPairGenerator keyPairGen;
        keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Initialisierung mit oben angegebener Schl�ssell�nge
        keyPairGen.initialize(keysize);

        // Erstellen des RSA-Schl�sselpaars
        KeyPair rsaKeyPair = keyPairGen.generateKeyPair();

        // Variablen privateKey und publicKey aus dem Key-Pair auslesen und setzen
        this.publicKey = rsaKeyPair.getPublic();
        this.privateKey = rsaKeyPair.getPrivate();

    }


    /**
     * Verschl�sseln des �bergebenen Strings mit vorher erzeugtem pubKey
     * @param plainText String, der verschl�sselt werden soll
     * @return Verschl�sselter und Base64 kodierter String
     */
    public String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher rsa;

        // Setzen des CipherObjekts / Verschl�sselungsmodus auf RSA
        rsa = Cipher.getInstance("RSA");

        // Initialisieren des RSA-Ciphers mit vorher erzeugtem pubKey und dem Modus ENCRYPT_MODE
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);

        // Leeres Array f�r return-Value der eigentlichen Verschl�sselung erstellen
        byte[] cipherText;

        // Eigentliche Verschl�sselung durchf�hren (doFinal mit dem plainText aufrufen und returnValue in cipherText speichern)
        cipherText = rsa.doFinal(plainText.getBytes());

        // Erstellen eines Base64 Encoders (siehe VorlesungsPDF)
        Base64.Encoder encoder = Base64.getEncoder();

        // Zur�ckgeben des Verschl�sselten und Kodierten PlainTexts
        return encoder.encodeToString(cipherText);
    }

    /**
     * Methode um verschl�sselten Text wieder zu entschl�sseln
     * @param encryptedText RSA-verschl�sselter Text
     * @return Entschl�sselter PlainText
     */
    private String decrypt(String encryptedText) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher rsa;

        // Setzen des CipherObjekts / Verschl�sselungsmodus auf RSA
        rsa = Cipher.getInstance("RSA");

        // Erstellen eines Base64 Decoders, da der String ja kodiert ist
        Base64.Decoder decoder = Base64.getDecoder();

        // Dekodierten String in byte-Array speichern um Entschl�sselung damit durchzuf�hren
        byte[] cipherText = decoder.decode(encryptedText);

        // Initialisieren des RSA-Ciphers mit vorher erzeugtem privateKey und dem Modus DECRYPT_MODE
        rsa.init(Cipher.DECRYPT_MODE, privateKey);

        // Eigentliche Entschl�sselung durchf�hren (doFinal mit cipherText aufrufen und returnValue wieder in cipherText speichern)
        cipherText = rsa.doFinal(cipherText);

        // Entschl�sselten Text als String zur�ckgeben
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

        // Verschl�sseln und Ergebnis ausgeben
        System.out.println("Verschl�sselt: " + rsa.encrypt(plainText));

        // Text verschl�sseln und danach wieder entschl�sseln und ausgeben
        System.out.println("Entschl�sselt: " + rsa.decrypt(rsa.encrypt(plainText)));

        // Test, ob Text nach Ver- und Entschl�sseln immer noch gleich ist
        System.out.print("plainText und verschl�sselter -> entschl�sselter Text gleich : ");
        System.out.println(plainText.equals(rsa.decrypt(rsa.encrypt(plainText))));

    }

}
