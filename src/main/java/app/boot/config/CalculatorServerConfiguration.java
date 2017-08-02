package app.boot.config;

import app.core.CalculationEngine;
import app.core.evaluation.ExpressionProcessor;
import app.core.expr_parser.ExpressionTreeBuilder;
import app.core.tokenizer.ExpressionTokenizer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorServerConfiguration {

	@Bean
	public CalculationEngine calculationEngine(ExpressionTreeBuilder expressionTreeBuilder,
	                                           ExpressionTokenizer expressionTokenizer,
                                               ExpressionProcessor expressionProcessor) {
		return new CalculationEngine(expressionTreeBuilder, expressionTokenizer, expressionProcessor);
	}

	@Bean
	public ExpressionTreeBuilder expressionTreeBuilder() {
		return new ExpressionTreeBuilder();
	}

	@Bean
	public ExpressionTokenizer expressionTokenizer() {
		return new ExpressionTokenizer();
	}

	@Bean
	public ExpressionProcessor expressionProcessor() {
		return new ExpressionProcessor();
	}
}
