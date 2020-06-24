
public class Const extends Expr{
	
	private double value = 0;
	
	public Const(double v) {
		
		this.value = v;
	}
	
	@Override
	public double compute() {
		return this.value;
	}
	
	public String toString() {
		return Double.toString(this.value);
	}
	
	public boolean equals(Object other) {
		if(this instanceof Const) {
			if(this.value == ((Const)other).value){
				return true;
			}
			return true;
		} else {
			return false;
		}
	}
}