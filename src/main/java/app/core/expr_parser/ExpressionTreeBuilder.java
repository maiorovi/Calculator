package app.core.expr_parser;

import app.core.evaluation.Expr;
import app.core.evaluation.Numb;
import app.core.evaluation.Sum;
import app.core.evaluation.TermExpression;
import app.core.tokenizer.support.Token;

import java.util.LinkedList;
import java.util.List;

public class ExpressionTreeBuilder {
	private OperatorFactory operatorFactory;

	public ExpressionTreeBuilder(OperatorFactory operatorFactory) {
		this.operatorFactory = operatorFactory;
	}

	public Expr build(List<Token> tokens) {
		LinkedList<Expr> valueStack = new LinkedList<Expr>();
		LinkedList<Token> operatorStack = new LinkedList<Token>();

		for (Token token : tokens) {
			if (token.isNumber()) {
				valueStack.push(new Numb(token.getToken()));
			} else if (token.isLeftParentheses()) {
				operatorStack.push(token);
			} else if (token.isRightParentheses()) {
//				while(operatorStack.peekFirst() instanceof TermExpression) {
//
//				}
			} else if (token.isOperator()) {
				while(!operatorStack.isEmpty() && Operator.fromToken(operatorStack.peek()).getPrecedence() >= Operator.fromToken(token).getPrecedence()) {
					Expr ex1 = valueStack.pop();
					Expr ex2 = valueStack.pop();

					Expr createdOp = operatorFactory.createOperator(ex1, ex2, operatorStack.pop());
					valueStack.push(createdOp);
				}
				operatorStack.push(token);
			}
		}


		while(!operatorStack.isEmpty()) {
			Expr ex1 = valueStack.pop();
			Expr ex2 = valueStack.pop();

			Expr createdOp = operatorFactory.createOperator(ex1, ex2, operatorStack.pop());
			valueStack.push(createdOp);
		}


		return valueStack.pop();
	}

}
