
public class NatZahl implements Ordered{
	
	protected int i;
	
	public NatZahl(int i) {
		if(i < 0)
		{
			this.i = 0;
		}
		else {
			this.i = i;
		}
	}

	@Override
	public boolean eq(Object o) {
		
		if(o instanceof NatZahl)
		{
			NatZahl z = (NatZahl) o;
			return this.i == z.i;
		}

		return false;
	}

	@Override
	public boolean neq(Object o) {
		return !this.eq(o);
	}

	@Override
	public boolean gt(Object o) {

		if(o instanceof NatZahl)
		{
			NatZahl z = (NatZahl) o;
			return this.i > z.i;
		}

		return false;	
	}

	@Override
	public boolean lt(Object o) {
		return !this.gt(o) && !this.eq(o);
	}

	@Override
	public boolean ge(Object o) {
		return !lt(o);
	}

	@Override
	public boolean le(Object o) {
		return !this.gt(o);
	}

	@Override
	public String toString() {
		return Integer.toString(this.i);
	}	
}