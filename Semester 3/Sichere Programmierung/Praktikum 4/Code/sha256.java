import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha256 {
	
	MessageDigest md;
	//die verwendete Nachricht
	static String message = "Kryptographie macht Spass!!!";
	static String checksum = "";
	
	//öffentlicher Konstruktor
	public sha256() {
		//initialisieren des Objektes mit null
		this.md = null;
		
		//try catch block für sha256
		try {
			//Instanz von MessageDigest erstellen
			this.md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e){
			System.out.println("Algorithmus SHA-256 nicht vorhanden!");
		}
	}
	
    public static void main(String[] args){
    	//Instanz der Klasse erstellen
    	sha256 sha256 = new sha256();
    	//aufruf von hilfsfunc um die Prüfsumme zu berechnen
        sha256.hilfsfunc(message);
      //aufruf von checksum um die Prüfsumme zu verifizieren
        sha256.checksum(message, checksum);
    }
	
	public byte[] hilfsfunc(String message) {
		//berechne die bytes (Prüfsumme) des übergebenen Strings
		byte[] digest = this.md.digest(message.getBytes());
		//Aufruf von toHexString mit dem berechneten digest und einem offset von 2
		System.out.println("Digest.: " + toHexString(digest, 2));
		//Ausgabe des Hexcodes (Prüfsumme) 
		return digest;
	}

	//toHexString Methode vom Skript
	public String toHexString(byte[] data , int offset) {
		if (offset < 0) {
			offset = 0;
		}
		
		StringBuilder sb = new StringBuilder ();
		
		for (int i=0; i<data.length; i++) {
			sb.append(String.format("%02X", data[i]));
			if ((offset >0) && (i+1<data.length) && ((i+1) % offset == 0)) {
				sb.append(" ");
			}
		 }
		
		sha256.checksum = sb.toString();
		//Ausgabe der Hexadezimalen Prüfsumme
		return sb.toString();
	}
	
	//Verifizierung der Prüfsumme
	public boolean checksum(String text, String b_checksum) {
		//Prüfsumme des Textes berechnen
		byte[] digest = this.md.digest(text.getBytes());
		//Prüfsumme abspeichern als String
		String a_checksum = toHexString(digest, 2);
		
		//check ob beide Prüfsummen gleich sind
		if(a_checksum.equals(b_checksum)) {
			System.out.println("Wort entspricht der Übergebenen Prüfsumme: True");
			return true;
		}
		System.out.println("Wort entspricht nicht der Übergebenen Prüfsumme: False");
		return false;
	}
}