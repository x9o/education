public class MSFImpl implements MSF {
	//Algorithmus von Prim

    int[] pred;

    @Override
    public void compute(WeightedGraph g, int s) {
        pred = new int[g.size()]; //pi(v)
        
        BinHeap<Double, Integer> heap = new BinHeap<>();
        BinHeap.Entry<Double, Integer>[] entry = new BinHeap.Entry[g.size()];

        for(int i=0; i<g.size(); i++) { 
            if(i != s) {
            	entry[i] = heap.insert(Double.POSITIVE_INFINITY, i); // +unendlich in jeden Entry
                pred[i] = NIL;
            }
        }

        pred[s] = NIL; //Startknoten nil
        int u = s;
        
        while(!(heap.isEmpty())){ //solange Heap nicht leer ist
            for(int i=0; i<g.deg(u); i++){ //Für jeden Nachfolger von v von u
                int v=g.succ(u, i);
                if(heap.contains(entry[v]) && g.weight(u, i) < entry[v].prio()){ //Wenn v und p(u,v) < gamma(v)
                    heap.changePrio(entry[v], g.weight(u, i)); //erniedrige die Prio von v auf (u,v)
                    pred[v] = u; //setze v = u
                }
            }
            BinHeap.Entry<Double, Integer> min = heap.extractMin();
            u = min.data();
        }
    }

    @Override
    public int pred(int v) {
    	return pred[v];
    }
}