package app.core.tokenizer;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ExpressionTokenizerTest {

	private ExpressionTokenizer tokenizer = new ExpressionTokenizer();

	@Test
	public void tokenizesSimpleSumExpression() throws Exception {
		assertThat(tokenizer.tokenize("3 + 5"))
				.extracting("token")
				.containsExactly("3", "+", "5");
	}

	@Test
	public void tokenizesSimpleSummExpressionWithBrackets() throws Exception {
		assertThat(tokenizer.tokenize("2* (3234 + 5) / 12"))
				.extracting("token")
				.containsExactly("2", "*", "(", "3234", "+", "5", ")","/", "12");
	}
}
