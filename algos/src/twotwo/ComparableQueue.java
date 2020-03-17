package twotwo;

import onepointthree.Queue;

import java.util.Iterator;

public class ComparableQueue<Comparable> implements Iterable<Comparable> {
    Node first; // link to least recently added node
    Node last; // link to most recently added node
    private int N; // number of items on the queue

    private class Node { // nested class to define nodes
        Comparable item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    } // Or: N == 0.

    public int size() {
        return N;
    }

    public void enqueue(Comparable item) { // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }

    public Comparable dequeue() { // Remove item from the beginning of the list.
        Comparable item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        N--;
        return item;
    }

    public Comparable peek() {
        return first.item;
    }

    public Iterator<Comparable> iterator() {
        return (Iterator<Comparable>) new ListIterator();
    }

    private class ListIterator implements Iterator<Comparable> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Comparable next() {
            Comparable item = current.item;
            current = current.next;
            return item;
        }
    }
}

