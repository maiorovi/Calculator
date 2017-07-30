package app.core.evaluation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionProcessorTest {

	private ExpressionProcessor expressionProcessor = new ExpressionProcessor();

	@Test
	public void evaluatesSimpleNumberExpression() throws Exception {
		Numb numb = new Numb(6);

		assertThat(expressionProcessor.eval1(numb)).isEqualTo(6);
	}

	@Test
	public void evaluatesSumExpressionCorrectly() throws Exception {
		Numb numb1 = new Numb(6);
		Numb numb2 = new Numb(12);

		Sum sum = new Sum(numb1, numb2);

		assertThat(expressionProcessor.eval1(sum)).isEqualTo(18);
	}
}
