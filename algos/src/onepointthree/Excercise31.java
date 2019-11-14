package onepointthree;

import java.util.Iterator;

import onepointthree.Stack.Node;

/**
 * Excercise31
 */
public class Excercise31<Item> implements Iterable<Item> {
    DoubleNode first;
    DoubleNode last;
    int N;

    private class DoubleNode {
        Item item;
        DoubleNode previous;
        DoubleNode next;
    }

    /**
     * <h1>add</h1> Adds the item to the beggining of the list
     * 
     * @param item item to be added
     */

    public void add(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;
        if (first == null)
            last = newNode;
        else {
            newNode.next = first;
            first.previous = newNode;
        }
        first = newNode;
        N++;
    }

    /**
     * <h1>push</h1> Adds the item to the end of the list
     * 
     * @param item item to be added
     */

    public void push(Item item) {
        DoubleNode newNode = new DoubleNode();
        newNode.item = item;
        if (last == null)
            first = newNode;
        if (last != null) {
            newNode.previous = last;
            last.next = newNode;
        }
        last = newNode;
        N++;
    }

    /**
     * <h2>pop</h2> Removes the item from the beggining of the list
     * 
     */

    public void pop() {
        System.out.println(N);
        if (N > 1) {
            first.next.previous = null;
            first = first.next;
        }
        if (N == 1) {
            last = null;
            first = null;
        }
        N--;
    }

    public DoubleNode getNthNode(int n) {
        if (n <= N) {
            DoubleNode current = first;
            for (int i = 2; i < n; i++)
                current = current.next;
            return current;
        }
        return null;
    }

    /**
     * <h2>remove</h2>
     * 
     * Removes the last element of the list
     */
    public void remove() {
        if (N > 1) {
            last.previous.next = null;
            last = last.previous;
        }
        if (N == 1) {
            first = null;
            last = null;
        }
        N--;
    }

    void reversePrint() {
        DoubleNode current = last;
        while (current != null) {
            System.out.println(current.item);
            current = current.previous;
        }
    }

    void insertBefore(DoubleNode node, Item item) {
        DoubleNode nodeToAdd = new DoubleNode();
        nodeToAdd.item = item;
        nodeToAdd.previous = node.previous;
        nodeToAdd.next = node;
        node.previous = node;
    }

    void insertAfter(DoubleNode node, Item item) {
        DoubleNode nodeToAdd = new DoubleNode();
        nodeToAdd.item = item;
        nodeToAdd.previous = node;
        nodeToAdd.next = node.next;
        node.next = nodeToAdd;
    }

    @Override
    public Iterator<Item> iterator() {
        return new doubleList();
    }

    public class doubleList implements Iterator<Item> {
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
        Excercise31<Integer> dlist = new Excercise31<Integer>();
        dlist.push(3);
        dlist.add(2);
        dlist.push(4);
        dlist.push(5);
        dlist.add(1);

        for (int x : dlist)
            System.out.println(x);
        System.out.println("reversePrint");
        dlist.reversePrint();

        dlist.pop();
        dlist.remove();
        dlist.pop();
        dlist.remove();
        dlist.remove();
        dlist.pop();

        for (int x : dlist)
            System.out.println(x);
        System.out.println("reversePrint");
        dlist.reversePrint();

        dlist.push(3);
        dlist.add(2);
        dlist.push(4);
        dlist.push(5);
        dlist.add(1);
        //dlist.insertAfter(dlist.getNthNode(5), 6);
        dlist.insertBefore(dlist.getNthNode(1), 0);

        for (int x : dlist)
            System.out.println(x);
        System.out.println("reversePrint");
        dlist.reversePrint();

    }

}