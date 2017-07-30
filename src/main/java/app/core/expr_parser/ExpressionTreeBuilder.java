package app.core.expr_parser;

import app.core.evaluation.Expr;
import app.core.expr_parser.tokens.TokenProcessingStrategy;
import app.core.expr_parser.tokens.TokenProcessor;
import app.core.tokenizer.support.Token;
import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.List;

public class ExpressionTreeBuilder {
    // TODO: this chain might be configured externally
    private ImmutableList<TokenProcessor> tokenProcessorsChain = ImmutableList.<TokenProcessor>builder()
            .add(TokenProcessingStrategy.PROCESS_NUMERIC_TOKEN)
            .add(TokenProcessingStrategy.PROCESS_OPERATOR)
            .build();

    public Expr build(List<Token> tokens) {
        LinkedList<Expr> valueStack = new LinkedList<Expr>();
        LinkedList<Token> operatorStack = new LinkedList<Token>();

        for (Token token : tokens) {
            for (TokenProcessor tokenProcessor : tokenProcessorsChain) {
                if (tokenProcessor.shouldProcess(token)) {
                    tokenProcessor.process(token, valueStack, operatorStack);
                    break;
                }
            }
        }

        createTreeFromOperators(valueStack, operatorStack);

        return valueStack.pop();
    }

    private void createTreeFromOperators(LinkedList<Expr> valueStack, LinkedList<Token> operatorStack) {
        OperatorFactory operatorFactory = new OperatorFactory();
        while (!operatorStack.isEmpty()) {
            Expr ex1 = valueStack.pop();
            Expr ex2 = valueStack.pop();

            Expr createdOp = operatorFactory.createOperator(ex1, ex2, operatorStack.pop());
            valueStack.push(createdOp);
        }
    }

}
