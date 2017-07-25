package app.core.evaluation;

public class Numb implements Expr {
	private double value;

	public Numb(double value) {
		this.value = value;
	}

	public Numb(String value) {
		this.value = Double.valueOf(value);
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Numb{" +
				"value=" + value +
				'}';
	}
}
