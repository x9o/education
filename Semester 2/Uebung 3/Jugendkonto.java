public class Jugendkonto extends Konto{

	int alter;
		
	public Jugendkonto(String inh, int alter) {
		super(inh);
		this.alter = alter;
	}
	
	protected double neuesAlter(int alter) {
		
		if(alter <= this.alter) {
			System.out.println("Alter fehler!");
		} else {
			this.kontostand += 100;
		}
		return this.kontostand;
	}
}