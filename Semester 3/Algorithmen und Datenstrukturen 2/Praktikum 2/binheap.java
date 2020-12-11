// Als Binomial-Halde implementierte Minimum-Vorrangwarteschlange
// mit Prioritäten eines beliebigen Typs P (der die Schnittstelle
// Comparable<P> oder Comparable<P'> für einen Obertyp P' von P
// implementieren muss) und zusätzlichen Daten eines beliebigen Typs D.
class BinHeap <P extends Comparable<? super P>, D> {
    // Eintrag einer solchen Warteschlange bzw. Halde, bestehend aus
    // einer Priorität prio mit Typ P und zusätzlichen Daten data mit
    // Typ D.
    // Wenn der Eintrag momentan tatsächlich zu einer Halde gehört,
    // verweist node auf den zugehörigen Knoten eines Binomialbaums
    // dieser Halde.
    public static class Entry <P extends Comparable<? super P>, D> {
	// Priorität, zusätzliche Daten und zugehöriger Knoten.
	private P prio;
	private D data;
	private Node<P, D> node;

	// Eintrag mit Priorität p und zusätzlichen Daten d erzeugen.
	private Entry (P p, D d) {
	    prio = p;
	    data = d;
	}

	// Priorität bzw. zusätzliche Daten liefern.
	public P prio () { return prio; }
	public D data () { return data; }
    }

    // Knoten eines Binomialbaums innerhalb einer solchen Halde.
    // Neben den eigentlichen Knotendaten (degree, parent, child,
    // sibling), enthält der Knoten einen Verweis auf den zugehörigen
    // Eintrag.
    private static class Node <P extends Comparable<? super P>, D> {
	// Zugehöriger Eintrag.
		// Grad des Knotens.
	private int degree;

	// Vorgänger (falls vorhanden; bei einem Wurzelknoten null).
	private Node<P, D> parent;

	// Nachfolger mit dem größten Grad
	// (falls vorhanden; bei einem Blattknoten null).
	private Node<P, D> child;

	// Zirkuläre Verkettung aller Nachfolger eines Knotens
	// bzw. einfache Verkettung aller Wurzelknoten einer Halde,
	// jeweils sortiert nach aufsteigendem Grad.
	private Node<P, D> sibling;

	Entry<P,D> entry;

	// Knoten erzeugen, der auf den Eintrag e verweist
	// und umgekehrt.
	private Node (Entry<P, D> e) {
	    entry = e;
	    e.node = this;
	}

	// Priorität des Knotens, d. h. des zugehörigen Eintrags
	// liefern.
	private P prio () { return entry.prio; }
    }
    
	private Node<P,D> first_node;
	
	private class zwischenspeicher { //Klasse f�r Zwischenspeicher
		public Node<P, D> m1, m2, m3;

		public zwischenspeicher() { //Konstruktor
			m1 = null;
			m2 = null;
			m3 = null;
		}

		public boolean push(Node<P, D> n) { //speichern der Werte in den Zwischenspeicher
			if (m1 == null) {
				m1 = n;
				return true;
			}
			if (m2 == null) {
				m2 = n;
				return true;
			}
			if (m3 == null) {
				m3 = n;
				return true;
			}
			return false;
		}

		public boolean isEmpty() { //Wenn der Zwischenspeicher leer ist, true
			if (m1 == null && m2 == null && m3 == null) return true;
			return false;
		}

		public int size(){
			int counter = 0;
			if(m1 != null) counter++;
			if(m2 != null) counter++;
			if(m3 != null) counter++;
			return counter;
		}

		public Node<P, D> pop() { //
			Node<P, D> node = null;
			if (m1 != null) {
				node = m1; //speichern des Wertes
				m1 = null; //setze m1 null, sodass auf den n�chsten Node zugegriffen wird
			} else if (m2 != null) {
				node = m2; //speichern des Wertes
				m2 = null; //setze m2 null, sodass auf den n�chsten Node zugegriffen wird
			} else if (m3 != null) {
				node = m3; //speichern des Wertes
				m3 = null;
			}
			return node;
		}
	}

	public BinHeap(){
		super();
		this.first_node = null;
	}

	boolean contains(Entry<P,D> e){ //funktioniert
		if(e == null) return false;
		
		Node<P,D> parent = e.node;
		
		while(parent.parent != null){ //solange "hochgehen", bis kein parent mehr vorhanden ist
			parent = parent.parent;
		}
		
		Node<P,D> runner = first_node;
		
		while(runner != null){
			if(runner == parent) {
				return true; //Knoten gefunden
			}
			runner = runner.sibling; //Siblings anschauen
		}
		return false;
	}
	
	Entry<P,D> extractMin(){ //funktioniert
		Entry<P,D> e = minimum();
		Entry<P, D> tmp = e;
		if(!extract(e)) return null; //false => null
		return tmp;
	}
	
	boolean remove(Entry<P,D> e){ //funktioniert
		if(e == null) return false; //entry null return false
		if(!contains(e)) return false; //wenn Wert nicht enthalten return false
		if(!changePrio(e, minimum().prio())) return false;
		
		extract(e); //"entnehmen"
		//verweise des knotens entfernen
		e.node.parent = null;
		e.node.sibling = null;
		e.node.child = null;
		return true;
	}
	
	boolean changePrio(Entry<P,D> e, P p){
		if(!contains(e)) return false;
		if(p == null) return false;
		
		Node<P,D> node, node2;
		Entry<P,D> entry_parent;
		
		//neue Prio kleiner oder gleich der alten
		if(e.prio().compareTo(p) >= 0){
			e.prio = p;
			while(e.node.parent != null){
				node = e.node.parent;
				
				if(node.prio().compareTo(e.prio()) >= 0){
					node2 = e.node;
					entry_parent = node.entry;
					node.entry = e;
					e.node = node;
					entry_parent.node = node2;
					node2.entry = entry_parent;
				} else {
					break;
				}
			}
		} else { //neue Prio gr��er der alten
			e.prio = p;
			if(e.node.child == null) return true;
			if(!remove(e)) return false;
			
			BinHeap<P,D> tmp = new BinHeap<P, D>();
			Node<P,D> n = new Node<P, D>(e);
			
			tmp.first_node = n;
			n.child = n.sibling = n.parent = null; //verweise des Knotens entfernen
			n.degree = 0;
			this.first_node = merge_heap(this,tmp).first_node; //heaps fixen
		}
		return true;
	}
	
	boolean extract(Entry<P,D> e){
		if(this.isEmpty()) return false;
		if(!contains(e)) return false;
		if(e.node.parent != null) return false;
		
		Node<P,D> node = first_node;
		
		if(e.node == first_node && e.node.sibling == null) {
			first_node = null; //"l�schen" des Wurzelknotens, da nicht mehr n�tig
		}
		
		
		if(node.sibling != null){ //wenn siblings vorhanden sind

			while(node.sibling != e.node) { //node mit dem entry finden
				node = node.sibling; //n�chsten Node betrachten
			}
			node.sibling = e.node.sibling; //node mit entry gefunden
			e.node.sibling = null; //node mit dem entry "l�schen"
		}
		
		node = e.node.child;
		
		if(node != null){ //wenn child vorhanden
			do {
				BinHeap<P,D> tmp_heap = new BinHeap<P, D>();
				tmp_heap.first_node = node; //neuer first_node des neues heaps
				node = node.sibling; 
				//verweise im neuen heap "l�schen"
				tmp_heap.first_node.sibling = null;
				tmp_heap.first_node.parent = null;
				
				this.first_node = merge_heap(this,tmp_heap).first_node; //heap fixen und first_node speichern
			} while(node.sibling != e.node.child.sibling);
		}
		
		e.node.parent = null; //min node "l�schen"
		
		return true;
	}
	
	Entry<P,D> minimum(){ //funktioniert
		if(this.isEmpty()) return null;
		
		Entry<P,D> max = this.first_node.entry;
		
		while(max.node.sibling != null){
			if(max.prio().compareTo(max.node.sibling.prio()) > 0) { //wenn max.prio gr��er ist als Sibling.prio
				max = max.node.sibling.entry; //kleineren Wert (Sibling) in max speichern
			}
		}
		return max;
	}
	
	boolean isEmpty () { //funktioniert
		if(first_node == null) return true; //check first_node
    	return false;
	}
	
	int size () { //funktioniert
		if(first_node == null) return 0;
		
		int count = 0;
		int total_count = 0;
		
		Node<P, D> node = this.first_node;
		//size anhand des Grades der Knoten berechnen
		while(node != null){
			count = 1;
			count = count << node.degree; //left shift f�r jeden Knoten-Grad
			total_count += count; //ergebnis speichern
			node = node.sibling; //next sibling
		}
		return total_count; //total
	}
	
	Entry<P, D> insert (P p, D d){ //funktioniert
		if(p == null || d == null) return null; //wenn p oder d null

		Entry<P,D> entry = new Entry<P, D>(p,d);
		Node<P,D> node = new Node<P, D>(entry);
		BinHeap<P,D> tmp_heap = new BinHeap<P, D>();
		
		tmp_heap.first_node = node; //node in Heap speichern
		//verweise des Knotens "l�schen"
		node.child = null;
		node.sibling = null;
		node.parent = null;
		node.degree = 0;
		
		this.first_node = merge_heap(this,tmp_heap).first_node; //heap mergen und first_node setzten f�r sp�ter
		return entry;
	}
	
	private Node<P, D> merge_tree(Node<P, D> B1, Node<P, D> B2){ //funktioniert
		if(B1.prio().compareTo(B2.prio()) > 0) { //Wenn B2 niedrigere Prio
			B2.sibling = null;
			B2.degree = B2.degree + 1;
			B1.parent = B2;
			B2.parent = null;
			if(B2.child == null) {
				B2.child = B1.sibling = B1;
			} else {
				B1.sibling = B2.child.sibling;
				B2.child = B2.child.sibling = B1;
			}
			return B2;
			
		} else { //Wenn B2 gr��er oder gleich
			B1.sibling = null;
			B1.degree = B1.degree + 1;
			B2.parent = B1;
			B1.parent = null;
			if(B1.child == null) {
				B1.child = B2.sibling = B2;
			} else {
				B2.sibling = B1.child.sibling;
				B1.child = B1.child.sibling = B2;
			}
			return B1;
		}
	}
	
	private BinHeap<P, D> merge_heap(BinHeap<P, D> h1, BinHeap<P, D> h2) { //funktioniert
		if(h1 == null || h2 == null) return null;
		if(h1.isEmpty() && !h2.isEmpty()) return h2;
		if(!h1.isEmpty() && h2.isEmpty()) return h1;
		
		Node<P,D> hilfsnode = null;
		Node<P,D> neue_Halde_Ende = null;
		
		zwischenspeicher mem = new zwischenspeicher(); //aufruf des Zwischenspeichers
		BinHeap<P, D> new_heap = new BinHeap<P, D>(); //leeren Heap erstellen
		
		int k=0; //setze k=0

		while(!(h1.isEmpty()) || !(h2.isEmpty()) || !mem.isEmpty()) { //solange H1 oder H2 oder der Zwischenspeicher nicht leer ist
			if(!h1.isEmpty()) {
				if(h1.first_node.degree == k) { //wenn H1 Grad k besitzt
					hilfsnode = h1.first_node; //first_node in hilfsnode speichern
					h1.first_node = hilfsnode.sibling; //sibling speichern in first_node
					hilfsnode.sibling = null; //verweis l�schen
					hilfsnode.parent = null; //verweis l�schen
					if(!mem.push(hilfsnode)) return null; //zum zwischenspeicher hinzuf�gen
				}
			}
			
			if(!h2.isEmpty()) { //entsprechend f�r H2
				if(h2.first_node.degree == k) {
					hilfsnode = h2.first_node;
					h2.first_node = hilfsnode.sibling;
					hilfsnode.sibling = null;
					hilfsnode.parent = null;
					if(!mem.push(hilfsnode)) return null;
				}
			}
			
			if((mem.size()%2)==1) { //wenn der zwischenspeicher jetzt 1 oder 3 B�ume enth�lt
				hilfsnode = mem.pop(); //Node aus zw holen
				if(new_heap.first_node == null){
					new_heap.first_node = hilfsnode;
					neue_Halde_Ende = hilfsnode;
				} else {
					neue_Halde_Ende.sibling = hilfsnode;
					neue_Halde_Ende = hilfsnode;
				}
			}
			
			if(mem.size()==2){ //wenn der zwischenspeicher jetzt 2 B�ume enth�lt
				hilfsnode = merge_tree(mem.pop(),mem.pop()); //Baum erstellen
				mem.push(hilfsnode); //als �bertrag in den zwischenspeicher
			}
			
			k++; //erh�he k um 1
		}
		return new_heap;
	}
	
	void dump_node(Node<P,D> node, int depth) {
		for(int i=0; i<depth;i++) { //leerzeichen anhand des Grades ausgeben
			System.out.print("	");
		}
		System.out.println(node.prio() + " " + node.entry.data); //ausgabe des Knotens
	}
	
	void dump_sibs(Node<P,D> node, int depth) {
		if(node == null) return;
		dump_node(node, depth); //f�r jeden Knoten, rekursiv dump_node aufrufen
		
		if(node.child != null) dump_sibs(node.child.sibling, depth+1); //f�r Kind Knoten dump_sibs aufrufen und ausgeben
		
		if(node.parent != null) {
			if (node.parent.child.sibling != node.sibling) { 
				dump_sibs(node.sibling, depth); //f�r den Parent dump_sibs aufrufen und ausgeben
			}
		}
		if(node.parent == null) dump_sibs(node.sibling, depth); //f�r Sibling dump_sibs aufrufen und ausgeben
	}
	
	void dump () { //funktioniert
		if(this.isEmpty()) return;
		dump_sibs(this.first_node, 0); //rekursiv dump_sibs aufrufen
	}
}