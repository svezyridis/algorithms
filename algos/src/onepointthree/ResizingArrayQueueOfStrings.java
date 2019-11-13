package onepointthree;

import java.util.Iterator;

/**
 * ResizingArrayQueueOfStrings
 */
public class ResizingArrayQueueOfStrings implements Iterable<String> {

    int N = 0;
    String[] a = new String[1];

    public void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public int getLength(){
        return a.length;
    }

    public int getSize(){
        return N;
    }

    public void enqueue(String s) {
        if (N < a.length)
            a[N++] = s;
        else {
            resize(2 * N);
            a[N++] = s;
        }
    }

    public String dequeue() {
        String res = a[0];
        for (int i = 0; i < N - 1; i++) {
            a[i] = a[i + 1];
        }
        N--;
        if (N < a.length / 4) {
            resize(a.length / 2);
        }
        return res;
    }

    public Iterator<String> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<String> { // Support LIFO iteration.
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public String next() {
            return a[i++];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) {
        
        ResizingArrayQueueOfStrings queue=new ResizingArrayQueueOfStrings();
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        queue.enqueue("s");
        System.out.println(queue.getLength()+" "+ queue.getSize());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.getLength()+" "+ queue.getSize());

        ResizingArrayQueueOfStrings queue2=new ResizingArrayQueueOfStrings();
        queue2.enqueue("s");
        queue2.enqueue("a");
        queue2.enqueue("v");
        queue2.enqueue("v");
        queue2.dequeue();
        for(String s:queue2){
            System.out.println(s);
        }
    }
}