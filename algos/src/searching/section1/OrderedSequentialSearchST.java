package searching.section1;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
    Node first;
    int N;

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.val = val;
            this.key = key;
            this.next = next;
        }
    }

    @Override
    public int put(Key key, Value val) {
        Node previous = null;
        boolean found = false;
        int count = 0;
        for (Node current = first; current != null; current = current.next) {
            int cmp = current.key.compareTo(key);
            if (cmp > 0) break;
            if (cmp == 0) {
                current.val = val;
                return count;
            }
            previous = current;
            count++;
        }
        N++;
        if (previous == null) {
            first = new Node(key, val, first);
        } else {
            previous.next = new Node(key, val, previous.next);
        }
        return count;
    }

    @Override
    public Value get(Key key) {
        for (Node current = first; current != null; current = current.next) {
            if (current.key.equals(key)) {
                return current.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        return first.key;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        int result = 0;
        for (Node current = first; current != null; current = current.next) {
            if (current.key.compareTo(key) >= 0) break;
            result++;
        }
        return result;
    }

    @Override
    public Key select(int k) {
        if (k > N - 1) return null;
        int count = 0;
        for (Node current = first; current != null; current = current.next) {
            if (count == k) return current.key;
            count++;
        }
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        for (Node current = first; current != null; current = current.next) {
            queue.enqueue(current.key);
        }
        return queue;
    }
}
