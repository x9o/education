package verlangsartikel;

public class Lehrbuch extends GedrucktesBuch{
	
	public int ausgabejahr = 2000;
	
	public Lehrbuch(String Autor, String Titel, String Sprache, int seitenzahl, boolean istTaschenbuch, int ausgabejahr) {
		super(Autor, Titel, Sprache, seitenzahl, istTaschenbuch);
		this.ausgabejahr = ausgabejahr;
	}
	public void kopieren() {
		
	}
}