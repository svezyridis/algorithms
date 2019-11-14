package onepointthree;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Excercise33<Item> implements Iterable<Item> {
    private class DoubleNode {
        Item item;
        DoubleNode next;
        DoubleNode previous;
    }

    private int size;
    private DoubleNode first;
    private DoubleNode last;

    public DoubleNode createDoubleNode(Item item) {
        DoubleNode DoubleNode = new DoubleNode();
        DoubleNode.item = item;
        return DoubleNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushRight(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;
        if (last == null)
            first = newNode;
        if (last != null) {
            newNode.previous = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void pushLeft(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;
        if (first == null)
            last = newNode;
        else {
            newNode.next = first;
            first.previous = newNode;
        }
        first = newNode;
        size++;
    }

    public void popLeft() {
        if (size == 0)
            return;
        if (size > 1) {
            first.next.previous = null;
            first = first.next;
        }
        if (size == 1) {
            last = null;
            first = null;
        }
        size--;
    }

    public void popRight() {
        if (size == 0)
            return;
        if (size > 1) {
            last.previous.next = null;
            last = last.previous;
        }
        if (size == 1) {
            first = null;
            last = null;
        }
        size--;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        DoubleNode current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    public static void main(String[] args) {
        Excercise33<Integer> list = new Excercise33<Integer>();
        list.pushLeft(1);
        list.pushRight(2);
        list.pushLeft(0);
        list.pushRight(3);
        System.out.println("Expected 0123");

        for (int x : list)
            StdOut.print(x);

        list.popLeft();
        list.popRight();
        System.out.println("\nExpected 12");

        for (int x : list)
        StdOut.print(x);
    }
}