import java.util.ArrayList;

public class ListImpl1 implements ListInterface2{
	
	ArrayList<Integer> collector = new ArrayList<Integer>();
	
	public ListImpl1() {
		
	}
	
	
	@Override
	public boolean put(int x) {
		
		if(x >= 0) {
			
			collector.add(x);
			return true;
		}
		return false;
	}


	@Override
	public int get() {
		
		if(collector.get(0) == 0) {
			
			return -1;
		} else {
			return collector.get(0);
		}
	}

	@Override
	public int get(int i) {
		
		try {
		
			if(collector.get(i) == 0) {
			
				return -1;
			} else {
				return collector.get(i);
			}	
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public boolean remove(int i) {
		
		try {
		
			if(collector.get(i) != 0) {
			
				collector.remove(i);
				return true;
			} else {
			
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
}