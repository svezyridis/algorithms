package onepointthree;

/**
 * EvaluatePostfix
 */
public class EvaluatePostfix {

    public static String evaluateExpression(String input) {
        Stack<String> operands = new Stack<String>();
        String[] values = input.split("");
        for (String s : values) {
            System.out.println(s);
            if (s.equals("+")) {
                double operand2 = Double.parseDouble(operands.pop());
                double operand1 = Double.parseDouble(operands.pop());
                String res = Double.toString(operand1 + operand2);
                operands.push(res);
            }
            else if (s.equals("-")) {
                double operand2 = Double.parseDouble(operands.pop());
                double operand1 = Double.parseDouble(operands.pop());
                String res = Double.toString(operand1 - operand2);
                operands.push(res);
            }
            else if (s.equals("*")) {
                double operand2 = Double.parseDouble(operands.pop());
                double operand1 = Double.parseDouble(operands.pop());
                String res = Double.toString(operand1 * operand2);
                operands.push(res);
            }
            else if (s.equals("/")) {
                double operand2 = Double.parseDouble(operands.pop());
                double operand1 = Double.parseDouble(operands.pop());
                String res = Double.toString(operand1 / operand2);
                operands.push(res);
            }
            else operands.push(s);
        }
        return operands.pop();
    }
}