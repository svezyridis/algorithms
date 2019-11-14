package onepointthree;

import java.util.Iterator;

/**
 * Excercise49
 */
public class Excercise49<Item> {
    private class Stack<Item> implements Iterable<Item>{
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
    }

    Stack<Item> main;
    Stack<Item> last;
    Stack<Item> secondLast;

    public void enqueue(Item item){
        main.push(item);
        if(last.isEmpty()) last.push(item);
        if(secondLast.isEmpty()) last.push(item);
    }

    public void dequeue(){
        last.pop();
        if(!secondLast.isEmpty()) last.push(secondLast.first.item);
        secondLast.pop();                                          
    }


}