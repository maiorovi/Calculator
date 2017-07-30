package app.core.expr_parser;

import app.core.evaluation.Expr;
import app.core.evaluation.Mult;
import app.core.evaluation.Subtract;
import app.core.evaluation.Sum;
import app.core.tokenizer.support.Token;
import com.google.common.collect.ImmutableMap;

import javax.naming.ldap.ExtendedRequest;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class OperatorFactory {

	private ImmutableMap<Predicate<Token>, BiFunction<Expr, Expr, Expr>> operatorMapping =
	ImmutableMap.<Predicate<Token>, BiFunction<Expr, Expr, Expr>>builder()
			.put(Operator::isPlus, (ex1, ex2) -> new Sum(ex1, ex2))
			.put(Operator::isMultiplication, (ex1, ex2) -> new Mult(ex1, ex2))
			.put(Operator::isMinus, (ex1, ex2) -> new Subtract(ex1, ex2))
			.build();

	public Expr createOperator(Expr operand1, Expr operand2, Token op) {
		for (Predicate<Token> predicate : operatorMapping.keySet()) {
			if (predicate.test(op)) {
				return operatorMapping.get(predicate).apply(operand1, operand2);
			}
		}

		throw new IllegalArgumentException();
	}
}
