package app.core.exp_parser;

import app.core.evaluation.Expr;
import app.core.evaluation.Numb;
import app.core.evaluation.Sum;
import app.core.expr_parser.ExpressionTreeBuilder;
import app.core.expr_parser.OperatorFactory;
import app.core.tokenizer.support.Token;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionTreeBuilderTest {

	private ExpressionTreeBuilder treeBuilder = new ExpressionTreeBuilder(new OperatorFactory());

	@Test
	public void name() throws Exception {
		List<Token> tokens = Lists.newArrayList(new Token("1"), new Token("+"), new Token("5"));

		Expr expr = treeBuilder.build(tokens);

		assertThat(expr).isInstanceOf(Sum.class);
		assertThat(((Sum) expr).getEx1()).isInstanceOf(Numb.class);
		assertThat(((Sum) expr).getEx2()).isInstanceOf(Numb.class);
	}
}
