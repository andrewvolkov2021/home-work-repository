package console_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculation {
    private static final Calculation instance = new Calculation();

    private Calculation() {
    }

    public double gerResult(String rpn) throws ExpressionException {
        Deque<Double> stack = new ArrayDeque<>();
        String[] list = rpn.split(" ");

        for (String item : list) {
            if (item.matches("[0-9]+\\.?[0-9]*")) {
                stack.add(Double.parseDouble(item));
            } else if (item.equals("u-") || item.equals(("%"))) {
                getResultOfUnaryOperation(item, stack);
            } else {
                getResultOfBinaryOperation(item, stack);
            }
        }

        if (stack.size() != 1) {
            throw new ExpressionException("Calculation error", rpn);
        }
        return stack.removeLast();
    }

    private void getResultOfUnaryOperation(String operator, Deque<Double> stack) {
        double operand = stack.removeLast();
        switch (operator){
            case ("u-"):
                operand *= -1;
                stack.add(operand);
                break;
            case ("%"):
                operand *= 0.01;
                stack.add(operand);
                break;
        }
    }

    private void getResultOfBinaryOperation(String operator, Deque<Double> stack) throws ExpressionException {
        double operand;
        double operand2 = stack.removeLast();
        double operand1 = stack.removeLast();
        switch (operator) {
            case ("^") :
                operand = Math.pow(operand1, operand2);
                stack.add(operand);
                break;
            case ("*") :
                operand = operand1 * operand2;
                stack.add(operand);
                break;
            case ("/") :
                if (operand2 == 0) {
                    throw new ExpressionException("Division by zero: ", operand1 + " / " + operand2);
                }
                operand = operand1 / operand2;
                stack.add(operand);

                break;
            case ("+") :
                operand = operand1 + operand2;
                stack.add(operand);
                break;
            case ("-") :
                operand = operand1 - operand2;
                stack.add(operand);
                break;
        }
    }

    public static Calculation getInstance() {
        return instance;
    }
}
