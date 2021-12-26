package console_calculator;

public class ExpressionException extends Exception {
    private final String expression;

    public ExpressionException(String message, String expression) {
        super(message);
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
