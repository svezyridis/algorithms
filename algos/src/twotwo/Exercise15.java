package twotwo;

import edu.princeton.cs.algs4.StdRandom;
import onepointthree.Queue;

public class Exercise15 {

    public static Queue<Comparable> merge(Queue<Comparable> que1, Queue<Comparable> que2) {
        Queue result = new Queue();
        int newQueSize = que1.size() + que2.size();
        for (int i = 0; i < newQueSize; i++) {
            if (que1.isEmpty())
                result.enqueue(que2.dequeue());
            else if (que2.isEmpty())
                result.enqueue(que1.dequeue());
            else if (less(que1.peek(), que2.peek()))
                result.enqueue(que1.dequeue());
            else
                result.enqueue(que2.dequeue());
        }
        return result;
    }

    private static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }


    public static void main(String[] args) {
        Queue<Queue<Comparable>> queue = new Queue<Queue<Comparable>>();
        for (int i = 0; i < 100; i++) {
            Queue<Comparable> temp = new Queue<Comparable>();
            temp.enqueue(StdRandom.uniform());
            queue.enqueue(temp);
        }
        while (queue.size() != 1) {
            queue.enqueue(merge(queue.dequeue(), queue.dequeue()));
        }
        Queue<Comparable> result = queue.dequeue();
        for (Comparable item : result) {
            System.out.println(item);
        }
    }

}
