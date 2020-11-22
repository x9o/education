package verlangsartikel;

public abstract class EBook extends Buch {

	private int dateigroesse;
	private String dateiformat;
	
	public EBook(String Autor, String Titel, String Sprache, int dateigroesse, String dateiformat) {
		super(Autor, Titel, Sprache);
		this.dateigroesse = dateigroesse;
		this.dateiformat = dateiformat;
	}
	
	public abstract void speichern();
}