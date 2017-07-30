package app.core;

import app.core.evaluation.Expr;
import app.core.evaluation.ExpressionProcessor;
import app.core.expr_parser.ExpressionTreeBuilder;
import app.core.tokenizer.ExpressionTokenizer;
import app.core.tokenizer.support.Token;

import java.util.List;

public class CalculationEngine {
	private ExpressionTreeBuilder expressionTreeBuilder;
	private ExpressionTokenizer expressionTokenizer;
	private ExpressionProcessor expressionProcessor;

	public CalculationEngine(ExpressionTreeBuilder expressionTreeBuilder, ExpressionTokenizer expressionTokenizer,
	                         ExpressionProcessor expressionProcessor) {
		this.expressionTreeBuilder = expressionTreeBuilder;
		this.expressionTokenizer = expressionTokenizer;
		this.expressionProcessor = expressionProcessor;
	}

	public Double calculate(String expr) {
		List<Token> tokens = expressionTokenizer.tokenize(expr);
		Expr expressionTree = expressionTreeBuilder.build(tokens);

		return expressionProcessor.eval1(expressionTree);
	}


}
