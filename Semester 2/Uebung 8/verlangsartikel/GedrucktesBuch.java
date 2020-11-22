package verlangsartikel;

abstract class GedrucktesBuch extends Buch{

	private int seitenzahl;
	private boolean istTaschenbuch;
	
	public GedrucktesBuch(String Autor, String Titel, String Sprache, int seitenzahl, boolean istTaschenbuch) {
		super(Autor, Titel, Sprache);
		this.seitenzahl = seitenzahl;
		this.istTaschenbuch = istTaschenbuch;
	}
	
	public abstract void kopieren();
}