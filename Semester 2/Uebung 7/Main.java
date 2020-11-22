public class Main {

	public static void main(String[] args) {
	
		Uebung8 a = new Uebung8();
		
		try {
		
			System.out.println(a.pruefeEmail(""));
			System.out.println(a.pruefeEmail("abc@domain.de"));
			System.out.println(a.pruefeEmail("@domain.de"));
			System.out.println(a.pruefeEmail("hugo@"));
			System.out.println(a.pruefeEmail("name@@hs-aalen.de"));
			System.out.println(a.pruefeEmail("name@@@hs-aalen.de"));
		
		} catch (RedundantException e) {
			e.printStackTrace();
		}
	}
}
