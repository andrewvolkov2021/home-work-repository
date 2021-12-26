package console_calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Converter {
    private static final String OPERATORS = "*/+-%^";
    private static final String DELIMITERS = "()" + OPERATORS;

    private static final Converter instance = new Converter();

    private Converter() {
    }

    public String convert(String expression) throws ExpressionException {

        checkExpression(expression);

        StringBuilder rpn = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();

            if (!isDelimiters(curr)) {
                rpn.append(" " + curr);
                prev = curr;
                continue;
            }

            if (curr.equals("-") && (prev.equals("") || isDelimiters(prev))) {
                stack.add("u-");
                prev = curr;
                continue;
            }

            if (isOperators(curr)) {
                while (stack.peekLast() != null &&
                        getPriority(curr) <= getPriority(stack.getLast())) {
                    rpn.append(" " + stack.pollLast());
                }
                stack.add(curr);
                prev = curr;
                continue;
            }

            if (curr.equals("(")) {
                stack.add(curr);
                prev = curr;
                continue;
            }

            if (curr.equals(")")) {
                while (stack.peekLast() != null && !stack.peekLast().equals("(")) {
                    rpn.append(" " + stack.pollLast());
                }
                if (stack.peekLast() != null && stack.peekLast().equals("(")) {
                    stack.removeLast();
                } else {
                    throw new ExpressionException("Inconsistent parentheses. No open parenthesis: ", expression);
                }
            }
        }

        while (stack.peekLast() != null){
            if (!stack.peekLast().equals("(")) {
                rpn.append(" " + stack.pollLast());
            } else {
                throw new ExpressionException("Inconsistent parentheses. No closing parenthesis: ", expression);
            }
        }

        return rpn.toString().trim();
    }

    private boolean isOperators(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < OPERATORS.length(); i++) {
            if (token.charAt(0) == OPERATORS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDelimiters(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < DELIMITERS.length(); i++) {
            if (token.charAt(0) == DELIMITERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private int getPriority(String token){
        if (token.equals("(")){
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 2;
        }
        if (token.equals("*") || token.equals("/") || token.equals("%")) {
            return 3;
        }
        if (token.equals("^")) {
            return 4;
        }
        return 5;
    }

    private void checkExpression(String expression) throws ExpressionException {
        if (expression.matches(".*[^0-9\\(*/+\\-%^\\)\\.].*")) {
            throw new ExpressionException("Expression has invalid characters: ", expression);
        }

        if (expression.matches("^[*/+%^\\.].*")) {
            throw new ExpressionException("Expression cannot start with this operator: ", expression);
        }

        if (expression.matches(".*[\\.*/+^-]$") || expression.matches("^(\\-{2}).*")) {
            throw new ExpressionException("Expression cannot end with this operator: ", expression);
        }

        if (expression.matches(".*([*/+\\-%^\\.])([*/+%^\\.]).*")) {
            throw new ExpressionException("Invalid character sequence: ", expression);
        }

        if (expression.matches(".*(\\-{3,}).*")) {
            throw new ExpressionException("Invalid character sequence: ", expression);
        }

        if (expression.equals("")) {
            throw new ExpressionException("Empty expression: "," ");
        }
    }

    public static Converter getInstance(){
        return instance;
    }
}
