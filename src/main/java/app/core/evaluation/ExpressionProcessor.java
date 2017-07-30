package app.core.evaluation;

import java.util.LinkedList;
import java.util.function.BiFunction;

public class ExpressionProcessor {
	private BiFunction<Double, Double, Double> addition = (x,y) -> x + y;
	private BiFunction<Double, Double, Double> multiplication = (x,y) -> x * y;
	private BiFunction<Double, Double, Double> subtraction = (x,y) -> x - y;

    public double eval1(Expr expr) {
        if (expr.isMult()) {
            Mult mult = (Mult) expr;
            return eval1(mult.getEx1()) * eval1(mult.getEx2());
        } else if (expr.isNumb()) {
            return ((Numb) expr).getValue();
        } else if (expr.isSum()) {
            Sum sum = (Sum) expr;
            return eval1(sum.getEx1()) + eval1((sum.getEx2()));
        } else if(expr.isSubtraction()) {
            Subtract subtract = (Subtract) expr;
            return eval1(subtract.getLeft()) - eval1((subtract.getRight()));
        }

        return -1;
    }

//	public double eval1(Expr expr) {
//		LinkedList<Expr> stack = new LinkedList<>();
//		LinkedList<Double> operandQueue = new LinkedList<>();
//		LinkedList<BiFunction<Double, Double, Double>> binaryOperatorQueue = new LinkedList<>();
//
//		stack.push(expr);
//
//		while(!stack.isEmpty()) {
//			Expr currentExpr = stack.pop();
//			if (currentExpr instanceof Numb) {
//				operandQueue.addLast(((Numb) currentExpr).getValue());
//			} else if (currentExpr instanceof Sum) {
//				Sum sum = (Sum) currentExpr;
//				stack.push(sum.getEx2());
//				stack.push(sum.getEx1());
//				binaryOperatorQueue.push(addition);
//			} else if (currentExpr instanceof Mult) {
//				Mult mult = (Mult) currentExpr;
//				stack.push(mult.getEx2());
//				stack.push(mult.getEx1());
//				binaryOperatorQueue.push(multiplication);
//			} else if (currentExpr instanceof Subtract) {
//                Subtract subtract = (Subtract) currentExpr;
//                stack.push(subtract.getLeft());
//                stack.push(subtract.getRight());
//                binaryOperatorQueue.push(subtraction);
//            }
//		}
//
//		while(!binaryOperatorQueue.isEmpty()) {
//			Double v1 = operandQueue.poll();
//			Double v2=  operandQueue.poll();
//
//			BiFunction<Double, Double, Double> operator = binaryOperatorQueue.pop();
//
//			operandQueue.addFirst(operator.apply(v1, v2));
//		}
//
//		return operandQueue.poll();
//	}

}
