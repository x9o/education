public class SCCImpl implements SCC {
	//Algorithmus Zusammenhängende Komponenten
	
    private int[] cp;

    @Override
    public void compute(Graph g) {
        cp = new int[g.size()];

        //Bestimmung der Zusammenhangs Komponenten durch 2 Tiefensuchen
        DFS d1 = new DFSImpl();
        d1.search(g); //Erste Tiefensuche
        
        Graph gt = g.transpose(); //Für die 2te Tiefensuche wird G^t benötigt
        
        DFS d2 = new DFSImpl();
        d2.search(gt, d1); //Zweite Tiefensuche

        int count = 0;

        for(int i=g.size()-1; i>=0; i--){
            int res = d2.det(d2.sequ(i));
            if(cp[d2.sequ(i)] == 0) cp[d2.sequ(i)] = ++count;

            for(int j=i-1; j>=0; j--){
                int res2 = d2.det(d2.sequ(j));
                if(res2 > res){
                    cp[d2.sequ(j)] = count;
                } else {
                    i = j+1;
                    break;
                }
            }
        }
    }

    @Override
    public int component(int v) {
        return cp[v];
    }
}