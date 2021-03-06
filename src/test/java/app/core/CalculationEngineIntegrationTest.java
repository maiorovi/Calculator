package app.core;

import app.core.evaluation.ExpressionProcessor;
import app.core.expr_parser.ExpressionTreeBuilder;
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

	@Test
	public void calculatesCorrectlyExpressionWithPrecedenceAndSubtraction() throws Exception {
		assertThat(calculationEngine.calculate("3 - 15 + 5*3")).isEqualTo(3);
		assertThat(calculationEngine.calculate("3 + 5*3 - 15")).isEqualTo(3);
		assertThat(calculationEngine.calculate("3 + 5*3 - 15 - 11")).isEqualTo(-8);
	}

	@Test
	public void calculatesCorrectlyBigExression() throws Exception {
		assertThat(calculationEngine.calculate("5*6 + 3 - 15 - 5*4 + 32")).isEqualTo(30);
	}

	@Test
	public void simpleDivisionTest() throws Exception {
		assertThat(calculationEngine.calculate("8 / 4")).isEqualTo(2);
		assertThat(calculationEngine.calculate("8 / 4 * 5")).isEqualTo(10);
		assertThat(calculationEngine.calculate(" 5 * 3 - 10 / 2 + 7")).isEqualTo(17);
	}

	@Test
	public void canWorkWithNegativeNumbers() throws Exception {
		assertThat(calculationEngine.calculate("8 / -4")).isEqualTo(-2);
		assertThat(calculationEngine.calculate("-8 / -4")).isEqualTo(2);
		assertThat(calculationEngine.calculate("8 / 4 * -5")).isEqualTo(-10);
		assertThat(calculationEngine.calculate(" 5 * 3 - 10 / -2 + 7")).isEqualTo(27);
	}

	@Test
	public void canWorkWithParenthesisCorrectly() throws Exception {
		assertThat(calculationEngine.calculate("5 * (-3) * 5")).isEqualTo(-75);
		assertThat(calculationEngine.calculate("(1 + 3) * 5")).isEqualTo(20);
		assertThat(calculationEngine.calculate("5 * (1 + 3) * 5")).isEqualTo(100);
		assertThat(calculationEngine.calculate("2* (1+3) - 2 * (4 - 2)")).isEqualTo(4);
		assertThat(calculationEngine.calculate("-2* (1+3) - 2 * (4 - 2)")).isEqualTo(-12);
		assertThat(calculationEngine.calculate("-2*(-1+3) - 2 * (4 - 2)")).isEqualTo(-8);
	}

	@Test
	public void understandPlusCorrectlyWhenItIsUsedAsUnaryOperator() throws Exception {
		assertThat(calculationEngine.calculate("+5 + 5")).isEqualTo(10);
		assertThat(calculationEngine.calculate("+ 5 + (+5 + 5)")).isEqualTo(15);
	}

	@Test
	public void understandsCorrectlyMinuseBeforeParenthesis() throws Exception {
		assertThat(calculationEngine.calculate("5 + (-(5+3))")).isEqualTo(-3);
		assertThat(calculationEngine.calculate("5 + (+(5+3))")).isEqualTo(13);
	}
}
