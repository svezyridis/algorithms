package onepointthree;

/**
 * Excercise26
 */
public class Excercise26 {


    public static void main(String[] args) {
        Stack<String> stack=new Stack<String>();
        stack.push("giorgos");
        stack.push("savvas");
        stack.push("savvas");
        stack.push("giorgos");
        stack.push("giorgos");
        stack.push("savvas");
        stack.push("savvas");
        stack.push("savvas");
        stack.push("savvas");
        stack.push("savvas");
        stack.push("giorgos");

        System.out.println("Printing list");
        for(String s:stack){
            System.out.println(s);
        }
        System.out.println("removing instances of: "+"giorgos");
        stack.remove("giorgos");
        System.out.println("Printing list");
        for(String s:stack){
            System.out.println(s);
        }
    }
}