package onepointthree;

import java.util.Iterator;

/**
 * Excercise32
 */
public class Excercise32<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private int size;
    private Node first;
    private Node last;

    public Node createNode(Item item) {
        Node node = new Node();
        node.item = item;
        return node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            first = createNode(item);
            last=first;
        } else {
            last.next=createNode(item);
            last=last.next;
        }
        size++;
    }

    public void push(Item item){
        if(isEmpty()){
            first=createNode(item);
            last=first;
        }
        else{
            Node newNode=createNode(item);
            newNode.next=first;
            first=newNode;
        }
        size++;
    }
    public void pop(){
        if(isEmpty()) return;
        first=first.next;
        size--;
        if(isEmpty()) last=null;

    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

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
        Excercise32<Integer> list=new Excercise32<Integer>();

        list.pop();
        list.push(1);
        list.enqueue(2);
        list.push(0);

        list.pop();
        list.pop();
        list.pop();
        list.pop();

        for(int x:list)
            System.out.println(x);

    }
}