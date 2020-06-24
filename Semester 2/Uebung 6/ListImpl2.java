public class ListImpl2 implements ListInterface2{

	int arr[];
	int x;
	int y = 0;
	
	public ListImpl2(int size) {
		
		arr = new int[size];
	
	}
	
	public boolean put(int x) {
		
		if(x >= 0) {
			
			try {
				
				arr[y] = x;
				y++;
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;	
	}
	
	public int get() {
		
		try {
	
			if(arr[0] > 0) {
				return arr[0];
			} else {
				return -1;
			}
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	public int get(int i) {
		
		if(arr[i] == 0) {
			return -1;
		} else {
			return arr[i];
		}
	}
	
	public boolean remove(int i) {
		
		if(arr[i] == 0) {
			return false;
		} else {
			arr[i] = 0;
			return true;
		}
	}
}