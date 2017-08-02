package app.core.evaluation;

public class Negation implements Expr {

	private Expr expr;

	public Negation(Expr expr) {
		this.expr = expr;
	}

	@Override
	public boolean isNumb() {
		return false;
	}

	@Override
	public boolean isSum() {
		return false;
	}

	@Override
	public boolean isMult() {
		return false;
	}

	@Override
	public boolean isSubtraction() {
		return false;
	}

	@Override
	public boolean isDivision() {
		return false;
	}

	public boolean isNegation() {
		return true;
	}

	public Expr getExpr() {
		return expr;
	}
}
