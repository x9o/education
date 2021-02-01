import java.util.ArrayList;

public class DFSImpl implements DFS {
	//Tiefensuche

    private int[] det; //Entdeckungszeit
    private int[] fin; //Abschlusszeit
    private int[] pred;
    
    private ArrayList<Integer> sequ = new ArrayList<>();
    
    private int count = 1; //Zähler für die Zeiten
    private boolean sort_b = true;
    
    @Override
    public void search(Graph g) {
    	
        det = new int[g.size()];
        pred = new int[g.size()];
        fin = new int[g.size()];

        for(int u=0; u<g.size(); u++) { //Durchsuche den zu u gehörenden Teilgraphen
            if(det[u] == 0) {
            	pred[u] = -1;
                det[u] = count++; //Setze delta(u) auf den nächsten Zeitwert
            }
            for(int v=0; v<g.deg(u); v++){ //Wenn v weiß ist
            	if(det[g.succ(u, v)] == 0) {
            		det[g.succ(u, v)] = count++;
            		pred[g.succ(u, v)] = u; //Setze pi(v) = u
            		hilfsfunc(g, g.succ(u, v));	//Durchsuche rekursiv den zu v gehörenden Teilgraphen
                }
            }
            if(fin[u] == 0){
            	fin[u] = count++; //phi(u) auf den nächsten Zeitwert
            	sequ.add(u);
            }
        }
    }

    @Override
    public void search(Graph g, DFS d){
        det = new int[g.size()];
        pred = new int[g.size()];
        fin = new int[g.size()];

        for(int u=g.size()-1; u>=0; u--){
            if(det[d.sequ(u)] == 0){
            	pred[d.sequ(u)] = -1;
            	det[d.sequ(u)] = count++;
            }
            for(int v=0; v<g.deg(d.sequ(u)); v++){
            	if(det[g.succ(d.sequ(u), v)] == 0){
                	det[g.succ(d.sequ(u), v)] = count++;
                	pred[g.succ(d.sequ(u), v)] = d.sequ(u);
                	hilfsfunc(g, g.succ(d.sequ(u), v));
                }
            }
            if(fin[d.sequ(u)] == 0){
            	fin[d.sequ(u)] = count++;
            	sequ.add(d.sequ(u));
            }
        }
    }

    @Override
    public boolean sort(Graph g){
        det = new int[g.size()];
        pred = new int[g.size()];
        fin = new int[g.size()];

        for(int u=0; u<g.size(); u++){
            if(det[u] == 0) {
            	pred[u] = -1;
            	det[u] = count++;
            }
            for(int v=0; v<g.deg(u); v++){
                if(det[g.succ(u, v)] == 0){
                	det[g.succ(u, v)] = count++;
                	pred[g.succ(u, v)] = u;		
                    hilfsfunc(g, g.succ(u, v)); 
                }
            }
            if(fin[u] == 0){
            	fin[u] = count++;
            	sequ.add(u);
            }
        }
        return sort_b;
    }
    
    private void hilfsfunc(Graph g, int u){ //Die rekursive hilfsfunc
        for(int v=0; v<g.deg(u); v++){
            if(fin[g.succ(u, v)] == 0 && det[g.succ(u, v)] != 0){
            	sort_b = false;
            }
            if(det[g.succ(u, v)] == 0) {
            	det[g.succ(u, v)] = count++;
            	pred[g.succ(u, v)] = u;    
            	hilfsfunc(g, g.succ(u, v));
            }
        }
        fin[u] = count++;
        sequ.add(u);
    }
    
    @Override
    public int det(int v){
        return det[v];
    }
    @Override
    public int fin(int v){
        return fin[v];
    }
    @Override
    public int sequ(int i){
        return sequ.get(i);
    }
}