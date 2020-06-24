
public class RationaleZahl {

	
	private int zaehler, nenner, temp, z1, z2, z3, z4;
	private Integer ggT;
	private double kommawert;
	
	public static void main (String[] args) {
		RationaleZahl Zahl1 = new RationaleZahl(156, 66);
		
		Zahl1.alsBruchAusdrucken();
		Zahl1.alsKommawertAusgeben();
		Zahl1.kehrwert();
		Zahl1.ggTBerechnen();
		Zahl1.kuerzen();
		Zahl1.gekuerzt();
		Zahl1.ungekuerzt();
	}
	
	public RationaleZahl(int z, int n) {
		
		this.zaehler = z;
		this.nenner = n;
	}
	
	public void alsBruchAusdrucken() {
		
		System.out.println("Als Bruch: "+ this.zaehler + "/" + this.nenner);
	}
	
	public void alsKommawertAusgeben() {
		double x = (this.zaehler);
		double y = (this.nenner);
		this.kommawert = x / y;
		kommawert = Math. round(this.kommawert * 10) / 10.0;
		System.out.println("Als Kommawert: " + kommawert);
	}
	
	public RationaleZahl kehrwert() {
		
		RationaleZahl Zahl2 = new RationaleZahl(zaehler, nenner);
		
		this.temp = Zahl2.zaehler;
		Zahl2.zaehler = Zahl2.nenner;
		Zahl2.nenner = this.temp;
		System.out.println("Kehrwert: " + Zahl2.zaehler + "/" + Zahl2.nenner);
		return Zahl2;
	}
	
	private int ggTBerechnen() {
		
		if(this.nenner > this.zaehler){
			this.z2 = this.nenner;
			this.z1 = this.zaehler;
			
		} else {
			
			this.z1 = this.zaehler;
			this.z2 = this.nenner;
		}
		
		temp = this.z1 % this.z2;
		
		while(this.temp != 0) {
			
			this.z1 = this.z2;
			this.z2 = this.temp;
			this.temp = this.z1 % this.z2;
		}
		
		this.ggT = this.z2;
		System.out.println("ggT ist: " + ggT);
		return ggT;
	}
	
	public boolean kuerzen() {
		
		if(this.ggT > 0 && this.ggT < this.zaehler && this.ggT < this.nenner) {
			this.zaehler = this.zaehler / this.ggT;
			this.nenner = this.nenner / this.ggT;
			
			this.z3 = this.zaehler;
			this.z4 = this.nenner;
			
			System.out. println("Kürzen: " + this.zaehler + "/" + this.nenner);
			return true;
		
		} else {
			return false;
		}
	}
	
	public RationaleZahl gekuerzt() {
		
		RationaleZahl Zahl3 = new RationaleZahl(zaehler, nenner);
		
		if(ggT > 0 && ggT != Zahl3.zaehler && ggT != Zahl3.nenner) {
			Zahl3.zaehler = this.zaehler / ggT;
			Zahl3.nenner = this.nenner / ggT;
			
		} else {
			
			Zahl3.zaehler = this.zaehler;
			Zahl3.nenner = this.nenner;
		}
		System.out.println("Gekürzt: " + this.zaehler + "/" + this.nenner);
		return Zahl3;
	}
	
	public RationaleZahl ungekuerzt(){
		
		RationaleZahl Zahl4 = new RationaleZahl(this.zaehler, this.nenner);
		
		zaehler = this.z3 * this.ggT;
		nenner = this.z4 * this.ggT;
		System.out.println("Ungekürzt: " + zaehler + "/" + nenner);
		return Zahl4;
	}
}