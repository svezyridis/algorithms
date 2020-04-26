package searching.section1;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {

    private Node first;
    int N;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void delete(Key key) {
        Node previous=null;
        for(Node current=first;current!=null;current=current.next){
            if(current.key.equals(key)){
                if(previous==null)
                    first=first.next;
                else
                    previous.next=current.next;
            }
            N--;
        }
    }

    @Override
    public int put(Key key, Value val) {
        int count = 0;
        for (Node current = first; current != null; current = current.next) {
            count++;
            if (current.key.equals(key)) {
                current.value = val;
                return count;
            }
        }
        first = new Node(key, val, first);
        N++;
        return count;
    }

    @Override
    public Value get(Key key) {
        Node current = first;
        while (current != null) {
            if (current.key.equals(key))
                return current.value;
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    private class ListIterator implements Iterator<Key> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }

    @Override
    public Iterable<Key> keys() {
        return () -> new ListIterator() {
        };
    }
}
