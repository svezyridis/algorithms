package onepointthree;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise12
 */
public class Excercise12 {

    public static  Stack<String> copy(Stack<String> initial){
        Stack<String> res=new Stack<String>();
        Stack<String> temp=new Stack<String>();
        for(String s:initial){
            temp.push(s);
        }
        for(String s:temp){
            res.push(s);
        }
        return res;
    }
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
		stack.push("First Item");
		stack.push("Second Item");
		stack.push("Third Item");
		
		Stack<String> copy = copy(stack);
		stack.pop();
		stack.pop();
		
		for (String s : copy) {
			StdOut.println(s);
		}

		StdOut.println("\nExpected: " +
                "\nThird Item\n" +
                "Second Item\n" +
                "First Item");
    }
}