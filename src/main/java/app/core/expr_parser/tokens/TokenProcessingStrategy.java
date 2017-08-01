package app.core.expr_parser.tokens;

import app.core.evaluation.Expr;
import app.core.evaluation.Numb;
import app.core.expr_parser.Operator;
import app.core.expr_parser.OperatorFactory;
import app.core.tokenizer.support.Token;

import java.util.LinkedList;
import java.util.Stack;

public enum TokenProcessingStrategy implements TokenProcessor {
    PROCESS_NUMERIC_TOKEN {
        @Override
        public boolean shouldProcess(Token token) {
            return token.isNumber();
        }

        @Override
        public void process(Token token, LinkedList<Expr> valueStack, LinkedList<Token> operatorStack) {
            valueStack.push(new Numb(token.getToken()));
        }
    },

    PROCESS_OPERATOR {
        private OperatorFactory operatorFactory = new OperatorFactory();

        @Override
        public boolean shouldProcess(Token token) {
            return token.isOperator();
        }

        @Override
        public void process(Token token, LinkedList<Expr> valueStack, LinkedList<Token> operatorStack) {
            while(!operatorStack.isEmpty() &&
                    !operatorStack.peek().isLeftParentheses() &&
                    Operator.fromToken(operatorStack.peek()).getPrecedence() >= Operator.fromToken(token).getPrecedence()) {
                Expr ex2 = valueStack.pop();
                Expr ex1 = valueStack.pop();

                Expr createdOp = operatorFactory.createOperator(ex1, ex2, operatorStack.pop());
                valueStack.push(createdOp);
            }
            operatorStack.push(token);
        }
    },

    PROCESS_LEFT_PARENTHESIS {
        @Override
        public boolean shouldProcess(Token token) {
            return token.isLeftParentheses();
        }

        @Override
        public void process(Token token, LinkedList<Expr> valueStack, LinkedList<Token> operatorStack) {
            operatorStack.push(token);
        }
    },

    PROCESS_RIGHT_PARENTHESIS {
        private OperatorFactory operatorFactory = new OperatorFactory();

        @Override
        public boolean shouldProcess(Token token) {
            return token.isRightParentheses();
        }

        @Override
        public void process(Token token, LinkedList<Expr> valueStack, LinkedList<Token> operatorStack) {
            while(!operatorStack.peek().isLeftParentheses()) {
                Token operator = operatorStack.pop();

                Expr ex2 = valueStack.pop();
                Expr ex1 = valueStack.pop();
                valueStack.push(operatorFactory.createOperator(ex1, ex2, operator));
            }

            operatorStack.pop();
        }
    };


}
