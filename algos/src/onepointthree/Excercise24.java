package onepointthree;

import onepointthree.Stack.Node;

/**
 * Excercise24
 */
public class Excercise24 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        Node target = stack.getRandomNode();
        System.out.println("node to remove after");
        System.out.println(target.item);
        stack.removeAfter(target);
        System.out.println("new stack");
        for(int x:stack){
            System.out.println(x);
        }
    }
}