package app.core.evaluation;

import java.util.LinkedList;
import java.util.function.BiFunction;

public class ExpressionProcessor {
	private BiFunction<Double, Double, Double> addition = (x,y) -> x + y;
	private BiFunction<Double, Double, Double> multiplication = (x,y) -> x * y;

	public double eval1(Expr expr) {
		LinkedList<Expr> stack = new LinkedList<>();
		LinkedList<Double> operandQueue = new LinkedList<>();
		LinkedList<BiFunction<Double, Double, Double>> binaryOperatorQueue = new LinkedList<>();

		stack.push(expr);

		while(!stack.isEmpty()) {
			Expr currentExpr = stack.pop();
			if (currentExpr instanceof Numb) {
				operandQueue.addLast(((Numb) currentExpr).getValue());
			} else if (currentExpr instanceof Sum) {
				Sum sum = (Sum) currentExpr;
				stack.push(sum.getEx2());
				stack.push(sum.getEx1());
				binaryOperatorQueue.push(addition);
			} else if (currentExpr instanceof Mult) {
				Mult mult = (Mult) currentExpr;
				stack.push(mult.getEx2());
				stack.push(mult.getEx1());
				binaryOperatorQueue.push(multiplication);
			}
		}

		while(!binaryOperatorQueue.isEmpty()) {
			Double v1 = operandQueue.poll();
			Double v2=  operandQueue.poll();

			BiFunction<Double, Double, Double> operator = binaryOperatorQueue.pop();

			operandQueue.addFirst(operator.apply(v1, v2));
		}

		return operandQueue.poll();
	}

}
