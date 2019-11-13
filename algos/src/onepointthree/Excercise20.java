package onepointthree;

/**
 * Excercise20
 */
public class Excercise20 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.delete(1);
        for (int x : stack) {
            System.out.println(x);
        }
    }
}