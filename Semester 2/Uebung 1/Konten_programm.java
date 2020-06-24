
public class Konten_programm {

	
	public static void main(String[] args){
	     Konto konto1 = new Konto("Hugo");
	     Konto konto2 = new Konto("Hugeline");
	     Girokonto konto3 = new Girokonto("Test", 500);
	     
	     konto1.einzahlen(1000.0);
	     konto2.einzahlen(2000.0);
	     konto2.ueberweisen(1000.0, konto1);
	     //konto2.ueberweisen(2000.0, konto1);
	     //konto2.ueberweisen(2000.0, konto2)

	     //konto1.ausgabe();
	     //System.out.println();
	     //konto2.ausgabe();
	     
	     //konto3.einzahlen(200);
	     konto3.auszahlen(1001); //Dispo überziehen
	     konto3.gebuehr();
	     konto3.anzahlBuchungen();
	     konto3.ausgabe();
	    }
}