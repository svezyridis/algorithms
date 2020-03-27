package twofour;

import edu.princeton.cs.algs4.StdRandom;


public class Exercise3<Item extends Comparable<Item>> {
    Item[] array;
    int N;
    LinkedList<Item> list;

    private static class LinkedList<Item extends Comparable<Item>> {
        Node first;
        int size;

        public class Node {
            Comparable item;
            Node next;

            public Node(Comparable item) {
                this.item = item;
            }
        }

        public void insertOrdered(Comparable item) {
            if (size == 0) {
                first = new Node(item);
            } else {
                Node temp = new Node(item);
                if (item.compareTo(first.item) >= 0) {
                    temp.next = first;
                    first = temp;
                } else {
                    Node current = first;
                    while (current.next != null) {
                        if (item.compareTo(current.next.item) > 0) {
                            temp.next = current.next;
                            current.next = temp;
                            break;
                        }
                        current = current.next;
                    }
                    if (current.next == null) current.next = temp;
                }
            }
            size++;
        }

        public void insert(Comparable item) {
            Node temp = new Node(item);
            temp.next = first;
            first = temp;
            size++;
        }
    }

    public Exercise3(int maxItems) {
        N = 0;
        array = (Item[]) new Comparable[maxItems];
    }

    public Exercise3() {
        list = new LinkedList<>();
    }

    public void insertOrderedArray(Item item) {
        if (isEmpty()) {
            array[0] = item;
            N++;
            return;
        }
        int i = -1;
        while (array[++i].compareTo(item) <= 0) if (i == N - 1) break;
        if (i == N - 1) {
            if (array[i].compareTo(item) > 0) {
                array[N] = array[i];
                array[i] = item;
            } else array[N] = item;
        } else {
            for (int j = N; j > i; j--) {
                array[j] = array[j - 1];
            }
            array[i] = item;
        }
        N++;
    }

    public void insertOrderedList(Comparable item) {
        list.insertOrdered(item);
        N++;
    }

    public void insertList(Comparable item) {
        list.insert(item);
        N++;
    }

    public Comparable delMaxSortedList() {
        Comparable result = list.first.item;
        list.first = list.first.next;
        list.size--;
        N--;
        return result;
    }

    public Comparable delMaxList() {
        Comparable max = list.first.item;
        LinkedList.Node current = list.first;
        while (current != null) {
            if (current.item.compareTo(max) > 0)
                max = current.item;
            current = current.next;
        }
        if (max.compareTo(list.first.item) == 0) {
            list.first = list.first.next;
            N--;
            list.size--;
            return max;
        }

        current = list.first;
        while (current.next != null) {
            if (current.next.item.compareTo(max) == 0) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        N--;
        list.size--;
        return max;
    }

    public Comparable delMaxSorted() {
        Comparable result = array[N - 1];
        array[N - 1] = null;
        N--;
        return result;
    }

    public void insertRandomArray(Item item) {
        array[N] = item;
        N++;
    }

    public Comparable delMaxRandom() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (array[i].compareTo(array[max]) > 0)
                max = i;
        }
        Comparable result = array[max];
        for (int j = max; j < N - 1; j++) {
            array[j] = array[j + 1];
        }
        array[N - 1] = null;
        N--;
        return result;
    }


    private boolean less(Comparable[] array, int i, int j) {
        return array[i].compareTo(array[j]) < 0;
    }

    private void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public static void show(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void show(LinkedList list) {
        LinkedList.Node current = list.first;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Exercise3 unSortedList = new Exercise3();
        for (int i = 0; i < 10; i++) {
            unSortedList.insertList(StdRandom.uniform());
        }
        show(unSortedList.list);
        while (!unSortedList.isEmpty()) {
            System.out.println(unSortedList.delMaxList());
            show(unSortedList.list);
        }

    }
}
