
public class NatZahl1 extends AbstractOrdered {

	private int n;
	
	public NatZahl1(int n) {
		if(n > 0) {
			this.n = n;
		} else {
			this.n = 1;
		}
	}

	@Override
	public boolean eq(Object o) {
		
		if(o instanceof NatZahl1) {
			
			NatZahl1 z = (NatZahl1) o;
			return this.n == z.n;
		}
		
		if(o instanceof NatZahl) {
			
			NatZahl z = (NatZahl) o;
			return this.n == z.i;
		}
		
		if(o instanceof Integer) {
			
			return this.n == (Integer) o;
		}
		
		return false;
	}
	
	@Override
	public boolean gt(Object o) {
		
		if(o instanceof NatZahl1) {
			
			NatZahl1 z = (NatZahl1) o;
			return this.n > z.n;
		}
		
		if(o instanceof NatZahl) {
			
			NatZahl z = (NatZahl) o;
			return this.n > z.i;
		}
		
		if(o instanceof Integer) {
			
			return this.n > (Integer) o;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.n);
	}
}