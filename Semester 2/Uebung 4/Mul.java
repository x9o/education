
public class Mul extends Bin{
	
	public Mul(Expr l, Expr r){
		super(l, r);
	}
	
	@Override
	protected double combine(double l, double r){
		
		return l * r;
	}
	
	@Override
	protected String oper(){
		
		return "*";
	}
}