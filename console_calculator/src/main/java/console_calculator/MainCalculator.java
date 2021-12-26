package console_calculator;

import java.util.Scanner;

public class MainCalculator {
    private static final String START_CALCULATOR_COMMAND_NAME = "calc";
    private static final String END_CALCULATOR_COMMAND_NAME = "end";
    private static boolean isWork = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("CONSOLE CALCULATOR:");
        System.out.println("Start program\n");

        while (isWork) {
            System.out.print("Enter command calc/end: ");
            String command = in.nextLine();
            System.out.println("");

            command = command.replace(" ", "");
            if (command.equalsIgnoreCase(START_CALCULATOR_COMMAND_NAME)) {

                while (isWork) {
                    try {
                        System.out.print("Enter expression/end: ");
                        String expression = in.nextLine();
                        expression = expression.replace(" ", "");

                        if (expression.equalsIgnoreCase(END_CALCULATOR_COMMAND_NAME)){
                            System.out.print("End program");
                            isWork = false;
                        } else {
                            System.out.print("Reverse polish notation is: ");
                            String rpn = Converter.getInstance().convert(expression);
                            System.out.println(rpn);

                            System.out.print("Result: ");
                            double result = Calculation.getInstance().gerResult(rpn);
                            System.out.println(result);
                            System.out.println("");
                        }
                    } catch (ExpressionException ex) {
                        System.out.println(ex.getMessage() + ex.getExpression());
                        System.out.println("");
                    } catch (Exception ex) {
                        System.out.println("An error has occurred in the application");
                        System.out.println("");
                    }
                }

            } else if (command.equalsIgnoreCase(END_CALCULATOR_COMMAND_NAME)) {
                System.out.print("End program");
                isWork = false;
            } else {
                System.out.println("Invalid command value");
            }
        }
    }
}
