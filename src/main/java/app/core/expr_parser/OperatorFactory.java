package app.core.expr_parser;

import app.core.evaluation.Expr;
import app.core.evaluation.Mult;
import app.core.evaluation.Sum;
import app.core.tokenizer.support.Token;

public class OperatorFactory {

	public Expr createOperator(Expr operand1, Expr operand2, Token op) {
		if (Operator.isPlus(op)) {
			return new Sum(operand1, operand2);
		} else if (Operator.isMultiplication(op)) {
			return new Mult(operand1, operand2);
		}



		throw new IllegalArgumentException();
	}
}
