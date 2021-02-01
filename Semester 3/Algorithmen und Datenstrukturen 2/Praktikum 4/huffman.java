// Knoten f√ºr den Huffman-Trie
class HNode{
	// chars enth√§lt bei Blattknoten ein Zeichen, ansonsten alle Zeichen der darunterliegenden Knoten
	// Beispiel:
	// 			ab
	//         /  \
	//        a    b
	public String chars;
	// Linkes Kind
	public HNode leftChild;
	// Rechtes Kind
	public HNode rightChild;
}
class Huffman {
	// Feld mit Huffman-Codes zu den einzelnen Zeichen.
	// Wenn char c = 'a', dann ist codes[c] ein Code, der aus Nullen und Einsen besteht, mit dem das Zeichen a kodiert werden soll.
	private String[] codes;

	// Wurzelknoten des Pr√§fix-Codebaums
	private HNode root;
	
	// Konstruktor
	public Huffman() {
		super();
		this.root = null;
		this.codes = new String[256];
	}
	
	BinHeap<Integer, HNode> heap = new BinHeap<>();

	// Pr¸fen, ob ein Text mit dem aktuell erstellten Huffman-Code kodiert werden kann, 
	//ob also alle Zeichen einen Pr√§fix-Code besitzen. Wenn ja, return true, wenn nein, return false.
	public boolean canEncode(String text){ //funktioniert
		if(text == null) { System.out.println("Bitte einen nicht leeren Text eingeben!"); return false;}
		char[] input = text.toCharArray();
		int ascii=0;
		
		for(int i=0; i<input.length;) {
			ascii = (int) input[i];			
			if(codes[ascii] != null && codes[ascii] != "") { //Ist ein code verf¸gbar?
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	// Vor dem eigentlichen Algorithmus kann mit dieser Funktion die H√§ufigkeit 
	// der einzelnen Zeichen aus dem √ºbergebenen Text errechnet werden.
	// Hierzu kann die Anzahl des Vorkommens eines Zeichens berechnet werden und in einem Array gespeichert werden.
	// F√ºr jedes Zeichen c enth√§lt das Array f an Stelle c die H√§ufigkeit 
	// (also f['a'] ist die H√§ufigkeit von a im Text. Kommt das Zeichen nicht vor, ist die H√§ufigkeit 0.)
	// Zur Erinnerung: ein char kann wie eine Ganzzahl verwendet werden, daher funktioniert f[c] f√ºr jedes char c.
	public Integer[] calculateFrequencies(String text){ //funktioniert
		if(text == null) { System.out.println("Bitte einen nicht leeren Text eingeben!"); return null;} //Wenn text leer ist
		Integer[] f = new Integer[256];
		int count=0;

		for(int i=0; i<text.length(); i++) { //¸ber alle zeichen iterieren
			for(int j=0; j<text.length(); j++) { //¸ber alles iterieren
				if((text.charAt(i) - 0) == (text.charAt(j) - 0)) { //gleiche ASCII-Zeichen
					count = count + 1;
					f[text.charAt(i) - 0] = count; //H‰ufigkeit z‰hlen
				}
			}
			count = 0;
		}
		return f;
	}
	
	// Iterativer Algorithmus zur Erstellung des Pr√§fix-Codes (Skript S.115) mithilfe von BinHeap.
	// frequencies enth√§lt die H√§ufigkeiten (siehe calculateFrequencies). H√§ufigkeit von 0 bedeutet, das entsprechende Zeichen ist nicht im Text vorhanden und wir brauchen keinen Pr√§fixcode daf√ºr.
	// Die Funktion setzt den Knoten root auf den Wurzelknoten des Pr√§fixCode-Baums und gibt diesen Wurzelknoten au√üerdem zur√ºck
	public HNode constructPrefixCode(Integer[] frequencies) { //funktioniert
		if(frequencies.length == 0) { System.out.println("H‰ufigkeitstabelle ist leer!"); return null; } //Wenn Array frequencies leer ist
		
		for(int c=0; c<frequencies.length; c++) { //schritt 1 funktioniert
			if(frequencies[c] > 0) {
				HNode node = new HNode();
				node.chars = Character.toString ((char) c); //ASCII-Nummer zu Buchstabe
				heap.insert(frequencies[c], node); //H‰ufigkeit und HNode in Heap einf¸gen
			}
		}

		while(heap.size() >= 2) { //schritt 2 funktioniert
			//schritt 2.1	
			int var_prio = heap.minimum().prio(); //frequency
			HNode x = heap.extractMin().data(); //HNode x
			
			int var2_prio = heap.minimum().prio(); //frequency
			HNode y = heap.extractMin().data(); //HNode y
			
			//schritt 2.2
			HNode xy = new HNode(); //HNode x und y kombination
			xy.leftChild = x; //Verkn¸pfungen
            xy.rightChild = y;
			xy.chars = x.chars + y.chars; //Zeichen zuweisung des HNodes xy
            heap.insert((var_prio + var2_prio), xy);
		}
		//Schritt 2.3 funktioniert
		if(heap.minimum() == null) {
			System.out.println("Wurzelknoten darf nicht leer sein! Bitte f¸gen Sie ein Zeichen der H‰ufigkeitstabelle hinzu!");
			return null;
		}
		root = heap.extractMin().data();
		
		//Array codes initalisieren
		for(int i=0; i<256; i++) {
			codes[i] = "";
		}
		
		//Schritt 2.4 funktioniert
		traverseTree(root, codes, "");
		return root;
	}
	
	private void traverseTree(HNode root, String codes[], String my_string) { //funktioniert
		int ascii = 0;
		char character = '\0';
		
		if(root.leftChild != null && root.rightChild != null) { //Wenn Blattknoten
			traverseTree(root.leftChild, codes, my_string + "0");
			traverseTree(root.rightChild, codes, my_string + "1");
		} else {
			String z = root.chars;
			character = z.charAt(0);
			ascii = (int) character;
			codes[ascii] = my_string;
		}
	}

	// Kodierung einer Zeichenkette text (Skript S.108)
	// Die Ergebnis-Zeichenkette enth√§lt nur Nullen und Einsen
	// (der Einfachheit halber wird dennoch ein String-Objekt verwendet)
	// Kodierung: linker Teilbaum -> 0, rechter Teilbaum -> 1
	// Erster Parameter: Zu kodierender Text
	// Zweiter Parameter zeigt an, ob ein neuer Pr√§fixcode erzeugt werden soll (true) 
	//oder mit dem aktuellen Pr√§fixcode gearbeitet werden soll (false)
	public String encode(String text, boolean newPrefixCode){ //funktioniert
		if(text == null) {System.out.println("Bitte einen nicht leeren Text eingeben!"); return null;}; //Wenn Text leer ist
		String result = "";
		char[] input = text.toCharArray();
        int ascii=0;

		if(newPrefixCode) { //funktioniert
			Integer[] frequencies = new Integer[256]; //deklarieren eines neuen arrays
			for(int i=0; i<frequencies.length; i++) {
				frequencies[i] = 0; //array mit 0'en f¸llen
			}
			for(int k=0; k<input.length; k++) {
				ascii = (int) input[k];
				frequencies[ascii] = frequencies[ascii] + 1;
			}
			constructPrefixCode(frequencies); //neue Codes generieren
			for(int n=0; n<text.length(); n++) {
				ascii = (int) input[n]; //ASCII-Zahl des Eingegebenen Textes rausfinden
	        	if(codes[ascii] != null && codes[ascii] != "0") { //Nicht leer oder null
	        		result = result + codes[ascii]; //Speichern in result
	        	} else {
	        		System.out.println("Text kann nicht kodiert werden.");
	        		return null;
	        	}
	        }
        } else { //funktioniert
	        for(int i=0; i<input.length; i++) {
	        	ascii = (int) input[i]; //ASCII-Zahl des Eingegebenen Textes rausfinden
	        	if(codes[ascii] != null && codes[ascii] != "") { //Nicht leer oder null
	        		result = result + codes[ascii]; //Speichern in result
	        	} else {
	        		System.out.println("Text kann nicht kodiert werden.");
	        		return null;
	        	}
	        }
        }
        return result;
     }

	// Dekodierung eines Huffman-Kodierten Textes. (Skipt S.107)
	// Die Ergebnis-Zeichenkette ist der urspr√ºngliche Text vor der Huffman-Kodierung
	public String decode(String huffmanEncoded){ //funktioniert
		if(huffmanEncoded == null || root == null) {System.out.println("Bitte einen nicht leeren Text eingeben, oder Wurzelknoten setzen lassen durch 'prefixes'!"); return null;} //Wenn Text leer ist
		String result = "";
		char[] input = huffmanEncoded.toCharArray(); //funktioniert
        Boolean is_wrong = true;
        
        HNode pointer = new HNode();
        pointer = root;
        
        for(int i=0; i<input.length; i++) { //f¸r jeden Char
        	if(input[i] == '0') {
        		pointer = pointer.leftChild; //links f¸r '0'
        	} else {
        		pointer = pointer.rightChild; //rechts f¸r '1'
        	}
        	if(pointer.leftChild == null & pointer.rightChild == null) { //Wenn Blattknoten
        		result = result + pointer.chars; //HNode sein char speichern
        		pointer = root; //zur¸ck zum root
        		is_wrong = false;
        	}
        }
        if(is_wrong) { //Wenn Code nicht zu einem Buchstaben f¸hrt
        	System.out.println("Bitte vollst‰ndigen Code eingeben!");
        	return null;
        }
        return result;
     }

	// Dekodierung eines Huffman-Kodierten Textes mithilfe des √ºbergebenen Pr√§fix-Codebaums. (Skipt S.107) Der aktuelle Baum soll dabei nicht √ºberschrieben werden.
	// Die Ergebnis-Zeichenkette ist der urspr√ºngliche Text vor der Huffman-Kodierung
	public String decode(String huffmanEncoded, HNode rootNode){ //funktioniert
		if(huffmanEncoded == null || rootNode == null) { System.out.println("Bitte einen nicht leeren Text/Knoten eingeben!"); return null;}; //Wenn Text/Knoten leer/null ist
		String result = "";
		char[] input = huffmanEncoded.toCharArray();
		int ascii = 0;
		Boolean is_wrong = true;
		String[] codes2 = new String[256];
		
		HNode pointer = new HNode();
        pointer = rootNode;
		
        Integer[] frequencies = new Integer[256]; //deklarieren eines neuen arrays
        
		for(int i=0; i<frequencies.length; i++) {
			frequencies[i] = 0; //array mit 0'en f¸llen
		}
		
		for(int k=0; k<input.length; k++) {
			ascii = (int) input[k];
			frequencies[ascii] = frequencies[ascii] + 1; //H‰ufigkeiten in das Array f¸llen
		}

		//Array codes initalisieren
	    for(int l=0; l<256; l++) {
	    	codes2[l] = "";
	    }
	    
	    traverseTree(pointer, codes2, ""); //Neue Codes vom neuen Root aus

        for(int x=0; x<input.length; x++) { //f¸r jedes Zeichen
        	if(input[x] == '0') {
        		pointer = pointer.leftChild; //links f¸r '0'
        	} else {
        		pointer = pointer.rightChild; //rechts f¸r '1'
        	}
        	if(pointer.leftChild == null & pointer.rightChild == null) { //Wenn Blattknoten
        		result = result + pointer.chars; //HNode sein char speichern
        		pointer = rootNode; //zur¸ck zum root
        		is_wrong = false;
        	}
        }
        
        if(is_wrong) { //Wenn Code nicht zu einem Buchstaben f¸hrt
        	System.out.println("Bitte vollst‰ndigen Code eingeben!");
        	return null;
        }
        
        return result;
     }

	// Pr‰fixcodes ausgeben
	// Reihenfolge: preOrder, also WLR, zuerst Wurzel, 
	// dann linker Teilbaum, dann rechter Teilbaum
	// modus = false: Zeichenkette jedes Knoten ausgeben
	// modus = true: Zeichenkette und Code jedes Blattknoten ausgeben
	// Beispiele siehe in PNG-Datei P4_Huffman_Dump_Beispiel.png
	public void dumpPrefixCodes(boolean modus){ //funktioniert
		if(root == null) return;
		if(modus) {
			dump(); //funktioniert
		} else {
			dumpTree(root); //funktioniert
		}
	}
	
	public void dump() { //funktioniert
		int number=0;
		String temp="";
		String temp2="";
		Boolean flag = true;
		String[][] arr = new String[256][256];
		
		for(int a=0; a<256; a++) { //Initalisieren des 2 Dimensionalen Arrays
			for(int b=0; b<256; b++) {
				arr[a][b] = "";
			}
		}
		
		for(int i=0; i<256; i++) { //Kopiere das Array codes[] zum 2 Dimensionalen Array
			if(codes[i] != "") {
				arr[number][0] = Character.toString ((char) i); //new array
				arr[number][1] = codes[i];
				number++;
			}
		}
		
		while(flag) { //BubbleSort des 2 Dimensionales Arrays
			for(int k=0; k<256; k++) {
				for(int l=0; l<255; l++) {
					if(arr[l][0] != null && arr[l][1] != null) {
						if(arr[l][1].length() > arr[l+1][1].length()) {
							temp = arr[l][0];						
							temp2 = arr[l][1];
							arr[l][0] = arr[l+1][0];
							arr[l][1] = arr[l+1][1];
							arr[l+1][0] = temp;
							arr[l+1][1] = temp2;
							flag = false;
						}
					}
				}
			}
		}
		
		for(int z=0; z<256; z++) { //Ausgabe des Arrays
			if(arr[z][0] != "") {
				System.out.println(arr[z][0] + " " +arr[z][1]);
			}
		}
    }

	public void dumpTree(HNode root) { //funktioniert
        if(root != null) System.out.println(root.chars);
        if(root.leftChild != null) dumpTree(root.leftChild);
        if(root.rightChild != null) dumpTree(root.rightChild);
    }
}

// Interaktives Testprogramm f√ºr die Klasse Huffman.
class HuffmanTest {
	public static void main(String[] args) throws java.io.IOException {
		// H√§ufigkeiten (ASCII-Tabelle)
		/*
		Integer[] exampleFrequencies = new Integer[]{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 3676, 3, 160, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 472, 1, 252, 8, 39, 38,
				18, 12, 13, 14, 13, 9, 9, 15, 45, 12,
				1, 1, 1, 18, 1, 23, 76, 110, 160, 62, // 65 = A
				66, 104, 19, 33, 9, 74, 148, 108, 402, 11,
				44, 1, 300, 270, 270, 12, 40, 62, 2, 11,
				42, 0, 0, 0, 0, 0, 0, 1164, 389, 593, // 97 = a
				832, 3186, 332, 525, 908, 1666, 46, 370, 739, 541,
				2010, 560, 220, 5, 1508, 1382, 1348, 648, 199, 313,
				13, 56, 214, 1, 1, 1, 1, 1
		};
		
		*/

		Integer[] exampleFrequencies = new Integer[]{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 5, 2, 1, 1, 0, //A=5 B=2 C=1 D=1
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 2, 0, 0, 0, 0, 0, 0, 0, //R=2
		};
		
		Integer[] exampleFrequencies2 = new Integer[]{
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 1, 0, 0, //a=1
				0, 0, 0, 0, 1, 0, 0, 0, 2, 0, //l=2 h=1
				0, 1, 0, 0, 0, 0, 0, 0, 0, 0, //o=1
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		};
		
		HNode lastPrefixCode = null;
		HNode newTree = null;
		
		// Standardeingabestrom System.in als InputStreamReader
		// und diesen wiederum als BufferedReader "verpacken",
		// damit man bequem zeilenweise lesen kann.
		java.io.BufferedReader r = new java.io.BufferedReader(
				new java.io.InputStreamReader(System.in));
		
		
		Huffman h = new Huffman();
		// Endlosschleife.
		while (true) {

			// Eingabezeile vom Benutzer lesen, ggf. ausgeben (wenn das
			// Programm nicht interaktiv verwendet wird) und in einzelne
			// W√∂rter zerlegen.
			// Abbruch bei Ende der Eingabe oder leerer Eingabezeile.
			System.out.print(">>> ");
			String line = r.readLine();
			if (line == null || line.equals("")) return;
			if (System.console() == null) System.out.println(line);
			String[] cmd = line.split(" ");

			String funct = cmd[0];
			String text = null;
			if (cmd.length == 1) {
				if (funct == "dec") {
					System.out.println("Nothing to decode.");
					continue;
				} else
					if (funct.startsWith("enc")){
						System.out.println("Nothing to encode.");
						continue;
					}
			} else {
				text = line.substring(line.indexOf(' ')+1);
			}
			String result;
			switch (funct) {
				case "enc0": // Kodieren ohne neue Pr√§fixcodes zu errechnen
					result = h.encode(text, false);
					if (result!=null)
						System.out.println("Kodierter Text: "+result);
					break;
				case "enc1": // Kodieren mit Berechnung neuer Pr√§fixcodes
					result = h.encode(text, true);
					if (result!=null)
						System.out.println("Kodierter Text: "+result);
					break;
				case "dec": // Dekodieren eines Textes mit aktuellem Pr√§fixcode
					result = h.decode(text);
					if (result!=null)
						System.out.println("Dekodierter Text: "+result);
					break;
				case "decpref": // Dekodieren mit √ºbergebenem Pr√§fixcode
					result = h.decode(text, lastPrefixCode);
					if (result!=null)
						System.out.println("Dekodierter Text: "+result);
					break;
				case "prefixes": // Pr√§fix-Codes erstellen mit H√§ufigkeiten aus vorgeg. Feld
					lastPrefixCode = h.constructPrefixCode(exampleFrequencies);
					break;
					
				case "prefixes2":
					newTree = h.constructPrefixCode(exampleFrequencies2);
					break;
				case "decpref2":
					result = h.decode(text, newTree);
					if (result!=null)
						System.out.println("Dekodierter Text: "+result);
					break;
				case "dump": // Pr‰fix-Codes ausgeben
					h.dumpPrefixCodes(true);
			   		break;
				case "dumptree": // Pr‰fix-Codebaum-Knoten ausgeben
					h.dumpPrefixCodes(false);
					break;
				default:
					System.out.println("Unknown Function: " + funct);
					System.out.println("	Possible values: ");
					System.out.println("		enc0 - Encode using current prefix codes");
					System.out.println("		enc1 - Construct new prefix codes and then encode");
					System.out.println("		dec - Decode using current prefix codes");
					System.out.println("		decpref - Decode using a given prefix code");
					System.out.println("		prefixes - Construct new prefix codes");
					System.out.println("		dump - Dump prefix code");
					System.out.println("		dumptree - Dump prefix code tree");
					return;
			}
		}
	}
}