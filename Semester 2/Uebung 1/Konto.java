public class Konto {
	
    private int kontonummer = 0;
    private String inhaberIn;
    protected double kontostand = 0;
    private static int counter = 0;
    
    
    public Konto(String inh){
    	
    	this.kontostand = 500;
    	this.inhaberIn = inh;
    	this.kontonummer = 1000 + counter;
    	counter ++;
    }

    public void einzahlen(double betrag){
        this.kontostand += betrag;
    }

    public void auszahlen(double betrag){
        if(kontostand >= 0){
        	kontostand = kontostand - betrag;
        } else{
        	System.out.println("Konto nicht gedeckt"); //Zu wenig Geld vorhanden
        }
    }

    public boolean ueberweisen(double betrag, Konto zielkonto){
        if((kontostand - betrag) < 0){
        	System.out.println("Konto ist jetzt im Minus");
            return false;
        } else if(kontonummer == zielkonto.kontonummer){
        	System.out.println("Keine Überweisung getaetigt! Gleiches Konto!");
        	return false;
        } else if (zielkonto.inhaberIn == null) {
			return false;
        } else {
            kontostand = kontostand - betrag;
            zielkonto.kontostand = zielkonto.kontostand + betrag;
            return true;
        }
    }

    public void ausgabe(){
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Inhaber/In: " + inhaberIn);
        System.out.println("Kontostand: " + kontostand);
    }
}