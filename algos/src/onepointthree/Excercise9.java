package onepointthree;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise9
 */
public class Excercise9 {

    public static void main(String[] args) {
        System.out.println("reading input");
        String input = "";
        while (StdIn.hasNextLine()) {
            System.out.println(StdIn.hasNextLine());
            input += StdIn.readLine();
        }

        System.out.println("read input");
        String[] inputValues = input.split("\\s");
        Stack<String> operands = new Stack<String>();
        Stack<String> operators = new Stack<String>();
        Stack<String> res = new Stack<String>();
        for (String val : inputValues) {
            if (val.equals(" "))
                continue;
            if (val.equals("+"))
                operators.push(val);
            else if (val.equals("-"))
                operators.push(val);
            else if (val.equals("*"))
                operators.push(val);
            else if (val.equals(")")) {
                String operator = operators.pop();
                String operand2 = operands.pop();
                String operand1 = operands.pop();
                operands.push("( " + operand1 + " " + operator + " " + operand2 + " )");
            } else
                operands.push(val);
        }
        for (String word : operands)
            res.push(word);
        for (String w : res) {
            StdOut.print(w);
        }
    }
}