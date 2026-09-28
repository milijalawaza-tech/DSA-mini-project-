package restOfCodes;

import DataStructures.Stack;

public class PostfixEvaluator {

    public static void main(String[] args) {
        String expression = "5 3 + 2 *";
        System.out.println("Result: " + evaluate(expression));
    }

    public static double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        String[] tokens = expression.trim().split("\\s+");
        Stack stack = new Stack(tokens.length);

        for (String token : tokens) {
            if (isOperator(token)) {
                if (stack.isEmpty()) throw new IllegalArgumentException("Invalid postfix expression.");
                String val2 = stack.pop();
                if (stack.isEmpty()) throw new IllegalArgumentException("Invalid postfix expression.");
                String val1 = stack.pop();

                double operand2 = Double.parseDouble(val2);
                double operand1 = Double.parseDouble(val1);

                double result = performOperation(operand1, operand2, token);
                stack.push(String.valueOf(result));
            } else {
                try {
                    Double.parseDouble(token);
                    stack.push(token);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token encountered: " + token);
                }
            }
        }

        String finalResult = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("The expression is malformed. Extra operands remaining.");
        }
        return Double.parseDouble(finalResult);
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
                token.equals("×") || token.equals("÷") ||
                token.equals("*") || token.equals("/");
    }

    private static double performOperation(double op1, double op2, String operator) {
        switch (operator) {
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "×":
            case "*": return op1 * op2;
            case "÷":
            case "/":
                if (op2 == 0) throw new ArithmeticException("Division by zero error");
                return op1 / op2;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
