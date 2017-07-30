package app.core;

import app.core.evaluation.ExpressionProcessor;
import app.core.expr_parser.ExpressionTreeBuilder;
import app.core.expr_parser.OperatorFactory;
import app.core.tokenizer.ExpressionTokenizer;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CalculationEngineIntegrationTest {
	private CalculationEngine calculationEngine;

	@Before
	public void setUp() throws Exception {
		calculationEngine = new CalculationEngine(new ExpressionTreeBuilder(),
				new ExpressionTokenizer(), new ExpressionProcessor());
	}

	@Test
	public void calculatesSimpleSumExpression() throws Exception {
		assertThat(calculationEngine.calculate("3 + 5")).isEqualTo(8);
	}

	@Test
	public void calculatesCorrectlyExpressionWithPrecedence() throws Exception {
		assertThat(calculationEngine.calculate("3 + 5*3")).isEqualTo(18);
	}
}
