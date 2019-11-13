package onepointthree;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Parentheses
 */
public class Parentheses {

    public static void main(String[] args) {
        Stack<Character> rights = new Stack<Character>();
        boolean flag = true;
        while (!StdIn.isEmpty()) {
            char s = StdIn.readChar();
            System.out.println(s);
            if (s == '(' || s == '{' || s == '[') {
                rights.push(s);
            }
            if (s == ')') {
                if (rights.pop() != '(') {
                    flag = false;
                    break;
                }

            }
            if (s == '}') {
                if (rights.pop() != '{') {
                    flag = false;
                    break;
                }
            }
            if (s == ']') {
                if (rights.pop() != '[') {
                    flag = false;
                    break;
                }
            }

        }
        System.out.println(flag);
    }
}