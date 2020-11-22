import verlangsartikel.*;

public class Main {

	public static void main(String[] args) {
		
		Buch[] buch = new Buch[4];
	    Betriebsanleitung x = new Betriebsanleitung("Mark Zuckerberg", "AX350", "deutsch", 20, "PDF", 1990);
	    Betriebsanleitung k = new Betriebsanleitung("Homer", "MP305","deutsch", 20, "PDF", 2001);
	    Lehrbuch y = new Lehrbuch("Bill Gates", "Mathematik","deutsch", 20, false,2000);
	    Lehrbuch z = new Lehrbuch("Marge", "Physik","deutsch", 20, true, 2002);
	    buch[0] = x;
	    buch[1] = y;
	    buch[2] = k;
	    buch[3] = z;

	    for(int i = 0;i<buch.length;i++){
	        buch[i].drucken();
	    }

	}
}