package app.core.evaluation;

public class TermExpression implements Expr {
	private String value;

	public TermExpression(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
