package app.core.expr_parser.tokens;

import app.core.evaluation.Expr;
import app.core.tokenizer.support.Token;

import java.util.LinkedList;
import java.util.Stack;

public interface TokenProcessor {
    boolean shouldProcess(Token token);
    void process(Token token, LinkedList<Expr> valueStack, LinkedList<Token> operatorStack);
}
