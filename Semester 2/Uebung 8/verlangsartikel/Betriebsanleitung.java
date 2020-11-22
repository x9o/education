package verlangsartikel;

public class Betriebsanleitung extends EBook{

	public int veroeffentlichung;

	public Betriebsanleitung(String Autor, String Titel, String Sprache, int dateigroesse, String dateiformat, int veroeffentlichung) {
		super(Autor, Titel, Sprache, dateigroesse, dateiformat);
		this.veroeffentlichung = veroeffentlichung;
	}

	@Override
	public void speichern() {
		
	}
}