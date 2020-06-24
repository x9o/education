abstract class Bin extends Expr{
	
	private Expr left;
	private Expr right;
	
	protected Bin(Expr l, Expr r) {
		this.left = l;
		this.right = r;
	}
	
	protected abstract double combine (double l, double r);
	
	protected abstract String oper();
	
	@Override
	public double compute() {
		
		if(oper() == "+") {
			return left.compute() + right.compute();
		} else if(oper() == "-") {
			return left.compute() - right.compute();
		} else if(oper() == "*") {
			return left.compute() * right.compute();
		} else if(oper() == "/") {
			return left.compute() / right.compute();
		}
		
		return -1;
	}
	
	public String toString() {
		
		return this.left + " " + oper() + " " + this.right;
	}
	
	public boolean equals(Object other) {
		
		if(other instanceof Bin) {
			Bin a = (Bin)other;
			if(this.right.equals(a.right) && this.left.equals(a.left) && this.oper().equals(a.oper())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

	