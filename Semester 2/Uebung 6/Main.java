public class Main {
	
	public static void main(String[] args) {
		
		ListImpl1 L1 = new ListImpl1();
		ListImpl2 L2 = new ListImpl2(10);

		System.out.println(L1.put(5)); //Zahl hinzufügen
		System.out.println(L1.get()); //Erste Zahl lesen
		System.out.println(L1.get(2)); //Zahl übergenben
		System.out.println(L1.remove(1)); //Zahl entfernen

		System.out.println(L2.put(5)); //Zahl hinzufügen
		System.out.println(L2.get()); //Erste Zahl les0n
		System.out.println(L2.get(5)); //Zahl übergenben
		System.out.println(L2.remove(1)); //Zahl entfernen
	}
}