import java.util.ArrayList;

public class BFSImpl implements BFS {
	//Breitensuche

    private int[] dist;
    private int[] pred;
    ArrayList<Integer> fifo = new ArrayList<>();

    @Override
    public void search(Graph g, int s){
        dist = new int[g.size()];
        pred = new int[g.size()];
        
        for (int i = 0; i < g.size(); i++){
            dist[i] = INF; //array indexe unendlich setzen
            pred[i] = NIL; //array indexe nil setzen
        }
        
        dist[s]=0; //delta s=0
        pred[s]=NIL; //pi von s nil setzen
        fifo.add(s); //einfügen in eine fifo Warteschlange
        
        int count=0;
        while(count < fifo.size()){ //solange Warteschlange nicht leer ist
            for(int v=0; v<g.deg(fifo.get(count)); v++){ //Für jeden Nachfolger v von u
                if(dist[g.succ(fifo.get(count), v)] == INF){ //wenn gamma(v) unendlich ist
                   dist[g.succ(fifo.get(count), v)] = dist[fifo.get(count)] + 1; //Setze gamma(v)
                   pred[g.succ(fifo.get(count), v)] = fifo.get(count); //pi(v)= u
                   fifo.add(g.succ(fifo.get(count), v)); //Füge v am ende der Warteschlange an
                }
            }
            count++;
        }
    }

    @Override
    public int dist(int v){
    	//if (v >= 0 && v <= dist.length){
    		return dist[v];
    	//}
    	//return -1;
    }
    @Override
    public int pred(int v){
    	//if (v >= 0 && v <= pred.length){
    		return pred[v];
    	//}
    	//return -1;
    }
}