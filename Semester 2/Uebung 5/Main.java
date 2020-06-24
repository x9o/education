
public class Main {
	
	public static void main(String[] args) {
		
		NatZahl1 n1 = new NatZahl1(5);
		
		//Normale Aufgabe
		NatZahl1 n2 = new NatZahl1(5);
		System.out.println(n1.eq(n2));
		
		//Bonusaufgabe
		//System.out.println(n1.neq(5)); //mit einer GZ (INT)
		//NatZahl n3 = new NatZahl(6);
		//System.out.println(n1.neq(n3));
	}
}