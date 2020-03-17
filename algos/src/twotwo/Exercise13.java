package twotwo;

import onepointthree.Queue;

public class Exercise13 {

    public static ComparableQueue merge(ComparableQueue que1, ComparableQueue que2) {
        ComparableQueue result = new ComparableQueue();
        int newQueSize = que1.size() + que2.size();
        for (int i = 0; i < newQueSize; i++) {
            if (que1.isEmpty())
                result.enqueue(que2.dequeue());
            else if (que2.isEmpty())
                result.enqueue(que1.dequeue());
            else if (less((Comparable) que1.peek(), (Comparable) que2.peek()))
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
        ComparableQueue queue1 = new ComparableQueue();
        ComparableQueue queue2 = new ComparableQueue();
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);

        queue2.enqueue(-4);
        queue2.enqueue(-3);
        queue2.enqueue(-2);

        ComparableQueue result = merge(queue1, queue2);
        for (Object item : result) {
            System.out.println(item);
        }
    }
}
