package app.core.evaluation;

import app.core.tokenizer.support.Token;

public class Numb implements Expr {
	private double value;

	public Numb(double value) {
		this.value = value;
	}

	public Numb(String value) {
		this.value = Double.valueOf(value);
	}

	public Numb(Token token) {
		if (token == null) {
			throw new IllegalArgumentException("Token can`t be null");
		}

		this.value = Double.valueOf(token.getToken());
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
