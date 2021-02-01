public class GraphImpl implements Graph {
	//Transponierte Graphen

    private int[][] is;

    public GraphImpl(int[][] is) {
        this.is = is;
    }
    
    @Override
    public int size() {
        return is.length;
    }
    @Override
    public int deg(int v) {
        return is[v].length;
    }
    @Override
    public int succ(int v, int i) {
        return is[v][i];
    }
    @Override
    public Graph transpose() {
        int[][] adj = new int[size()][size()];
        int[] size2 = new int[size()];

        for(int v=0; v<size(); v++){ //Für jeden Knoten
            int k=deg(v); //Grad/Anzahl von ausgehenden Kanten von v
            for (int i=0; i<k; i++){ //k mal
                int n=succ(v, i); //nachfolger Knoten von v in n speichern
                adj[n][size2[n]] = v; //Adjazenzmatrix => Inzidenzmatrix
                size2[n]++; //liste vergrößern, weil Inzidenzmatrix
            }
        }
        int[][] iedges = new int[size()][]; //Neues 2 Dimensionales Array für die neuen transponierten Kanten

        for(int v=0; v<size(); v++){ //Für jeden Knoten
        	iedges[v] = new int[size2[v]];
            for(int i=0; i<size2[v]; i++){
            	iedges[v][i] = adj[v][i]; //Inzidenzmatrix in das neue Array iedges schreiben
            }
        }
        return new GraphImpl(iedges); //transponierten Graph zurückgeben
    }
}