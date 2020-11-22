package verlangsartikel;
import java.util.ArrayList;


public abstract class Buch {
	
	protected String Titel;
	protected String Sprache;
	int anzahl_autoren = 0;
	
	ArrayList<String> autoren = new ArrayList<String>();
	ArrayList<String> kapitel = new ArrayList<String>();
	
	public Buch(String Autor, String Titel, String Sprache) {
		
		this.autoren.add(Autor);		
		this.Titel = Titel;
		this.Sprache = Sprache;
	}
	
	public void drucken() {
		System.out.println("Autor(en): " + this.autoren);
		System.out.println("Titel: " + this.Titel);
		System.out.println("Sprache: " + this.Sprache);
	}
}

class Buchkapitel implements Kapitel{

	int kapitelNummer, seiten;
	String titel;
	
	public Buchkapitel(int kapitelNummer, int seiten, String titel) {
		
		this.kapitelNummer = kapitelNummer;
		this.seiten = seiten;
		this.titel = titel;
	}

	@Override
	public int kapitelNummer() {
		
		return kapitelNummer;
	}

	@Override
	public int seiten() {
		
		return seiten;
	}
	
	@Override
	public String titel() {

		return titel;
	}
}