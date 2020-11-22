import java.util.ArrayList;

public class Uebung8{
	
	public Uebung8() {
	}

	private int[] arr = new int[50];
	private int count = 0, skip = 0;
	
	ArrayList<String> emails = new ArrayList<String>();	
	
	public String pruefeEmail(String email) throws RedundantException{

		try {
			
			if(email.isEmpty()) {
				
				throw new EmptyException("Empty E-Mail!");
			}
			
			if(email.charAt(0) == '@') {
				
				throw new PrefixException("Falsche Prefix!");
			}
			
			if(!email.contains("@")) {
				
				throw new AtException("Keine @ vorhanden!");
			}
			
			if((email.length() - email.replace("@", "").length()) >1 ) {
				
				throw new AtException("Mehere @ gefunden!");
			}
			
			if((email.substring(email.length()-1)).equals("@")) {
				throw new DomainException("Domain fehlt!");
			}

			
		} catch (EmptyException e){
			email = "name@hs-aalen.de";
			
		} catch (PrefixException e){
			email = "name" + email;

		} catch (AtException e){
			
			if(!email.contains("@")) { //Kein "@" vorhanden!
				
				email = email + "@hs-aalen.de";
			} else {
				
				for(int i=0; i<email.length(); i++) {
					if(email.charAt(i) == '@') {
						arr[i] = 1;
						count++;
					}
				}
				
				if(count > 1) { //mehr als 1 gefunden!
					for(int i=0; i<arr.length; i++) {
						if(arr[i] == 1) {
							
							if(skip == 0) {
								skip = 1;
							} else {
		
								email = email.substring(0,i) + email.substring(i+1);
							}
						}				
					}						
				}
			}
	
	} catch (DomainException e){
		email = email + "hs-aalen.de";	
	
	}
		
	for(String s : emails) {
		if(s.equals(email)) {
			throw new RedundantException("Email ist bereits in verwendung!");
		}
	}
		
	emails.add(email); 
	return email;
	}
}