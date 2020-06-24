
public class Girokonto extends Konto{
	
	private int anzahlBuchungen = 0;
	private static double gebuehr = 0;
	private double dispoLimit = 0;
	
	public Girokonto(String inh, double dispoLimit) {
		super(inh);
		this.dispoLimit = dispoLimit;
	}

	double gebuehr() {
	
		Girokonto.gebuehr = anzahlBuchungen * 0.5;
		System.out.println("Gebühren: " + Girokonto.gebuehr + "€");
		return gebuehr;
	}

	int anzahlBuchungen() {
		
		this.anzahlBuchungen = anzahlBuchungen++;
		System.out.println("Anzahl der Buchungen: " + anzahlBuchungen);
		return anzahlBuchungen;
	}
	
	double dispoLimit() {
		
		dispoLimit = 200;
		return dispoLimit;
	}
	
	protected double gebuehren() {
		
		double finalGebuehren = this.anzahlBuchungen * Girokonto.gebuehr;
		
		if(this.kontostand - finalGebuehren > this.kontostand + this.dispoLimit) {
			return -1;
		} else {
		
			this.kontostand = this.kontostand - finalGebuehren;
			this.anzahlBuchungen = 0;
			return finalGebuehren;
		}
	}
	
	@Override
	public void einzahlen(double betrag) {
		
		super.einzahlen(betrag);
		anzahlBuchungen++;
	}
	
	@Override
	public void auszahlen(double betrag) {
		
		if(this.kontostand + this.dispoLimit >= betrag){
			this.kontostand += this.dispoLimit;
			super.auszahlen(betrag);
			this.kontostand -= this.dispoLimit;
			anzahlBuchungen++;
		}
	}
	
	@Override
	public boolean ueberweisen(double betrag, Konto zielkonto) {
		
		this.kontostand += this.dispoLimit;
		boolean zw = super.ueberweisen(betrag, zielkonto);
		this.kontostand -= this.dispoLimit;
		if(zw){
			this.anzahlBuchungen++;
			return zw;
		} else {
			return zw;
		}
	}
	
}
