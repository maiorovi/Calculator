package app.core.tokenizer;

import app.core.tokenizer.support.Token;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {
	public List<Token> tokenize(String expression) {
		ArrayList<Token> tokens = new ArrayList();
		String cleanedExpression = expression.replaceAll("\\s+", "");

		for (int i = 0; i < cleanedExpression.length(); i++) {
			if (Character.isDigit(cleanedExpression.charAt(i))) {
				Token number = parseNumber(cleanedExpression, i);
				tokens.add(number);
				i = i + number.length() - 1;
			} else {
				tokens.add(new Token(cleanedExpression.charAt(i)));
			}
		}

		return tokens;
	}

	private Token parseNumber(String expression, int i) {
		StringBuilder builder = new StringBuilder();

		while(i < expression.length() && Character.isDigit(expression.charAt(i))) {
			builder.append(expression.charAt(i));
			i++;
		}

		return new Token(builder.toString());
	}
}
