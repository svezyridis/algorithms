package onepointthree;

import java.util.Iterator;

/**
 * CircularQeueue
 */
public class CircularQeueue<Item> implements Iterable<Item> {
    Node last;
    int N;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        N++;
    }

    public void dequeue() {
        if (N == 0)
            return;
        if (N == 1)
            last = null;

        if (N > 1)
            last.next = last.next.next;
        N--;

    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        Node current = last.next;

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
        CircularQeueue<Integer> queue = new CircularQeueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.dequeue();
        int i = 0;
        for (int x : queue) {
            System.out.println(x);
            i++;
            if (i == 10)
                break;
        }
    }

}