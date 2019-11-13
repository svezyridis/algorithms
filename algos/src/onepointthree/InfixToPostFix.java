package onepointthree;
import onepointthree.EvaluatePostfix;

/**
 * InfixToPostFix
 */
public class InfixToPostFix {

    public static void main(String[] args) {
        String input = " 2 + 4 / 5 * ( 5 - 3 ) * 5 * 4 ";
        String[] inputValues = input.split("\\s");
        Stack<String> operands = new Stack<String>();
        Stack<String> operators = new Stack<String>();
        int parentheses = 0;
        for (String s : inputValues) {
            System.out.println(s);
            if (s.equals("+") || s.equals("-")) {
                if ((!operators.isEmpty()) && (operators.peek().equals("*") || operators.peek().equals("/"))
                        && parentheses == 0)
                    operands.push(operators.peek());
                operators.push(s);
            } else if (s.equals("*") || s.equals("/"))
                operators.push(s);
            else if (s.equals(")")) {
                parentheses--;
                operands.push(operators.pop());
            } else if (s.equals("("))
                parentheses++;
            else {
                operands.push(s);
            }
        }
        while (!operators.isEmpty())
            operands.push(operators.pop());
        String res = "";
        while (!operands.isEmpty())
            res = operands.pop() + res;
        System.out.println(res);
        System.out.println("evaluation:");
        System.out.println(EvaluatePostfix.evaluateExpression(res));
        
    }
}