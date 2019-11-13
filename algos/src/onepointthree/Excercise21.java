package onepointthree;
/**
 * Excercise21
 */
public class Excercise21 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.exists(1));
        System.out.println(stack.exists(7));
    }
}