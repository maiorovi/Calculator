package app.core.tokenizer.support;

import app.core.expr_parser.Operator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.apache.commons.lang3.StringUtils;

public final class Token {
	private static final ImmutableSet<String> OPERATORS = ImmutableSet.<String>builder()
			.add("+")
			.add()
			.build();

	private final String token;

	public Token(String token) {
		this.token = token;
	}

	public Token(Character token) {
		this.token = String.valueOf(token);
	}

	public String getToken() {
		return token;
	}

	public int length() {
		return token.length();
	}

	public boolean isNumber() {
		return StringUtils.isNumeric(token) || isNegativeNumeric(token);
	}

	private boolean isNegativeNumeric(String value) {
		return value.charAt(0) == '-' && StringUtils.isNumeric(value.substring(1));
	}

	public boolean isLeftParentheses() {
		return "(".equals(token);
	}

	public boolean isRightParentheses() {
		return ")".equals(token);
	}

	public boolean isOperator() {
		return Operator.isOperator(this);
	}
}
