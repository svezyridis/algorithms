package onepointthree;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Stack
 */
public class Stack<Item> implements Iterable<Item> {

    private Node first; // top of stack (most recently added node)
    public Node last;
    private int N;

    public void remove(String key) {
        Node current = first;
        if (current.item.equals(key))
            first = current.next;
        while (current.next != null) {
            if (current.next.item.equals(key)) {
                current.next = current.next.next;
                continue;
            }
            current = current.next;
        }
    }

    // number of items
    public class Node { // nested class to define nodes
        Item item;
        Node next;
    }

    public Node getRandomNode() {
        Node current = first;
        for (int i = 0; i < Math.floor(Math.random() * N); i++) {
            current = current.next;
        }
        return current;
    }

    public void removeAfter(Node before) {
        Node current = first;
        while (current.next != null) {
            if (current == before && current.next != null)
                current.next = current.next.next;
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean exists(Item item) {
        for (Item current : this) {
            if (item.equals(current))
                return true;
        }
        return false;
    }

    public void delete(int k) {
        if ((k == 1)) {
            first = first.next;
            return;
        }

        Node temp = first;
        if (k <= N) {
            for (int i = 2; i < k; i++)
                temp = temp.next;
            temp.next = temp.next.next;
        }
    }

    public int size() {
        return N;
    }

    public Node getNewNode() {
        return new Node();
    }

    public void insertAfter(Node previous, Node next) {
        Node current = first;
        while (current != null) {
            if (current == previous) {
                Node temp = current.next;
                current.next = next;
                next.next = temp;
                break;
            }
            current = current.next;
        }
    }

    // Or: N == 0.
    public void push(Item item) { // Add item to top of stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() { // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() { // return last inserted element
        return first.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {

        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) { // Create a stack and push/pop strings as directed on StdIn.
        System.out.println("programm has started");
        Stack<String> s = new Stack<String>();
        System.out.println("Stack created");
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}