package app.core.tokenizer;

import app.core.tokenizer.support.Token;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExpressionTokenizer {
	private Set<Character> legalOperators = Sets.newHashSet('+', '-', '*', '/', '(');

	public List<Token> tokenize(String expression) {
		ArrayList<Token> tokens = new ArrayList();
		String cleanedExpression = expression.replaceAll("\\s+", "");

		for (int i = 0; i < cleanedExpression.length(); i++) {
			if ((cleanedExpression.charAt(i) == '-' || cleanedExpression.charAt(i) == '+') &&
					Character.isDigit(cleanedExpression.charAt(i + 1)) &&
					(i == 0 || legalOperators.contains(cleanedExpression.charAt(i - 1)))) {
				Token number = parseNumber(cleanedExpression, i + 1, String.valueOf(cleanedExpression.charAt(i)));
				tokens.add(number);
				i = i + number.length() - 1;
			} else if ((cleanedExpression.charAt(i) == '-' || cleanedExpression.charAt(i) == '+') &&
					(cleanedExpression.charAt(i + 1) == '(') &&
					(i == 0 || legalOperators.contains(cleanedExpression.charAt(i - 1)))) {
				StringBuilder builder = new StringBuilder();

				builder.append(cleanedExpression.charAt(i));
				builder.append(cleanedExpression.charAt(i + 1));

				tokens.add(new Token(builder.toString()));
				i = i + 1;
			} else if (Character.isDigit(cleanedExpression.charAt(i))) {
				Token number = parseNumber(cleanedExpression, i);
				tokens.add(number);
				i = i + number.length() - 1;
			} else {
				tokens.add(new Token(cleanedExpression.charAt(i)));
			}
		}

		return tokens;
	}

	private Token parseNumber(String expression, int i, String prefix) {
		StringBuilder builder = new StringBuilder();

		while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
			builder.append(expression.charAt(i));
			i++;
		}

		return new Token(prefix + builder.toString());
	}

	private Token parseNumber(String expression, int i) {
		return parseNumber(expression, i, "");
	}
}
