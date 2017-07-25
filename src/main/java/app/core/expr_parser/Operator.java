package app.core.expr_parser;

import app.core.evaluation.Expr;
import app.core.evaluation.Sum;
import app.core.tokenizer.support.Token;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public enum Operator {
	PLUS("+", 1),
	MINUS("-", 1),
	MULTIPLY("*", 10)
	;


	private String representation;
	private int precedence;

	Operator(String representation, int precedence) {
		this.representation = representation;
		this.precedence = precedence;
	}

	public String getRepresentation() {
		return representation;
	}

	public int getPrecedence() {
		return precedence;
	}

	public static Map<String, Operator> representationToOperator = ImmutableMap.of(
			PLUS.getRepresentation(), PLUS,
			MINUS.getRepresentation(), MINUS,
			MULTIPLY.getRepresentation(), MULTIPLY
	);

	public static Operator fromToken(Token token) {
		return representationToOperator.get(token.getToken());
	}

	public static boolean isOperator(Token token) {
		return representationToOperator.containsKey(token.getToken());
	}

	public static Operator fromExpr(Expr expr) {
		 if (expr instanceof Sum) {
		 	return PLUS;
		 }

		 throw new IllegalArgumentException();
	}

	public static boolean isPlus(Token token) {
		return PLUS.representation.equals(token.getToken());
	}

	public static boolean isMultiplication(Token token) {
		return MULTIPLY.representation.equals(token.getToken());
	}
}
