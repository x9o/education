import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class aes {
	
	private static String plainText = "Dies ist eine streng geheime Nachricht!!!";

	public static void main(String[] args){
	
		//Instanz der Klasse erstellen	
		aes aes = new aes();	
		
		//random Key
		String random_key = aes.generate_Key();
		//verschlüsseln
		String encryptedString = aes.encrypt(plainText, random_key);
		//entschlüsseln
		aes.decrypt(encryptedString, random_key);
	}

	public String encrypt(String p_text, String key){
		try{
			//Initalisierungsvektor für CBC-Mode (Cipher Block Chaining)
			//Zufallszahlen
			SecureRandom random = new SecureRandom ();
			//Schlüssellänge
			byte[] iv = new byte[16];
			random.nextBytes(iv);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);

			//Schlüsselspezifikation
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes (),"AES"); 

			//Zuweisung der Instanz (CBC = "AES/CBC/PKCS5Padding")
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//Initalisierung (Encrypt) der Chiffre
			cipher.init(Cipher.ENCRYPT_MODE , keySpec , ivSpec); 
			
			//Klartext in Bytes umwandeln und in ein Byte-Array speichern
			byte[] cipherText = cipher.doFinal(p_text.getBytes());
			
			//Initialvektor & Geheimtext in einem Array speichern
			ByteArrayOutputStream os = new ByteArrayOutputStream ();
			os.write(iv);
			os.write(cipherText);
			//Initalisieren des Base64 encoder
			Base64.Encoder encoder = Base64.getEncoder ();
			//Codieren des Byte-Arrays zu Base64 String
			String message = encoder.encodeToString(os.toByteArray ());
			
			//Ausgaben
			System.out.println("Plain text.: " + p_text);
			System.out.println("Message....: " + message);
			return message;

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			System.out.println("Fehler beim verschlüsseln!");
			return null;
		}
	}
	
	public String decrypt(String encrypted, String key) {	
		try {
			//Schlüsselspezifikation
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),"AES");
			//Initalisieren des Base64 decoder
			Base64.Decoder decoder = Base64.getDecoder();
			//Byte-Array für Initalisierungsvektor
			byte[] iv = new byte[16];
			//Decode der Prüfsumme
			byte[] dec = decoder.decode(encrypted);
			//16 Zeichen der Chiffre in das Array iv speichern
			for(int i=0; i < 16; i++) {
				iv[i] = dec[i];
			}
			
			//Initalisierungsvektor
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			//Zuweisung der Instanz (CBC = "AES/CBC/PKCS5Padding")
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//Initalisieren der Chiffre im Entschlüsselungsmodus
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			//Prüfsumme in Bytes umwandeln
			byte[] plain = cipher.doFinal(dec);
			//Umwandeln der Bytes in einen String
			String org_text = new String(plain).substring(16);
			//Ausgabe
			System.out.println("Original Text: " + org_text);
			return org_text;
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Fehler!");
			return null;
		}
	}
	
	public String generate_Key() {
		//Zufallszahlen generieren
		SecureRandom random = new SecureRandom();
		//16 Byte Array
		byte[] bytes = new byte[16];
		//Zufallszahlen (random) in das Array schreiben
		random.nextBytes(bytes);
		//Konvertieren des Arrays in einen String
		String new_key = new String(bytes);
		//Rückgabe des Strings
		return new_key;
	}
}