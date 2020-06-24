
public class Sub extends Bin{
	
	public Sub(Expr l, Expr r){
		super(l, r);
	}
	
	@Override
	protected double combine(double l, double r){

		return l - r;
	}
	
	@Override
	protected String oper(){
		
		return "-";
	}
}