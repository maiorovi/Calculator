package app.core.evaluation;

public class Sum implements Expr {

	private Expr ex1;
	private Expr ex2;

	public Sum(Expr ex1, Expr ex2) {
		this.ex1 = ex1;
		this.ex2 = ex2;
	}

	public Expr getEx1() {
		return ex1;
	}

	public Expr getEx2() {
		return ex2;
	}
}
