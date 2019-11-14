package onepointthree;

import onepointthree.Stack.Node;

/**
 * Excercise24
 */
public class Excercise25 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        Node target = stack.getRandomNode();
        Node newNode= stack.getNewNode();
        newNode.item=7;
        
        System.out.println("node to insert after");
        System.out.println(target.item);

        System.out.println("node to insert");
        System.out.println(newNode.item);
        stack.insertAfter(target, newNode);;
        System.out.println("new stack");
        for(int x:stack){
            System.out.println(x);
        }
    }
}