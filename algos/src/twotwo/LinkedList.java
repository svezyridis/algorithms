package twotwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import onepointthree.Queue;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;

public class LinkedList<Item> {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of items on the queue

    private static class Node { // nested class to define nodes
        Comparable item;
        Node next;
    }


    public static Node merge(LinkedList a, Node low, Node mid, Node hi, Node previous) {
        Node aux;
        Node leftNode = low;
        Node rightNode = mid.next;
        Node leftEnd = mid.next;
        Node rightEnd = hi.next;
        if (less(leftNode.item, rightNode.item)) {
            aux = leftNode;
            leftNode = leftNode.next;
        } else {
            aux = rightNode;
            rightNode = rightNode.next;
            if (previous == null) {
                a.first = aux;
            } else {
                previous.next = aux;
            }
        }
        while (leftNode != leftEnd || rightNode != rightEnd) {
            if (leftNode == leftEnd) {
                aux.next = rightNode;
                rightNode = rightNode.next;
            } else if (rightNode == rightEnd) {
                aux.next = leftNode;
                leftNode = leftNode.next;
            } else if (less(leftNode.item, rightNode.item)) {
                aux.next = leftNode;
                leftNode = leftNode.next;
            } else {
                aux.next = rightNode;
                rightNode = rightNode.next;
            }
            aux = aux.next;
        }
        aux.next = rightEnd;
        if (hi == a.last)
            a.last = aux;
        return aux;
    }

    public static Node mergeShuffle(LinkedList a, Node low, Node mid, Node hi, Node previous) {
        Node aux;
        Node leftNode = low;
        Node rightNode = mid.next;
        Node leftEnd = mid.next;
        Node rightEnd = hi.next;
        if (Math.random() > 0.5) {
            aux = leftNode;
            leftNode = leftNode.next;
        } else {
            aux = rightNode;
            rightNode = rightNode.next;
            if (previous == null) {
                a.first = aux;
            } else {
                previous.next = aux;
            }
        }
        while (leftNode != leftEnd || rightNode != rightEnd) {
            if (leftNode == leftEnd) {
                aux.next = rightNode;
                rightNode = rightNode.next;
            } else if (rightNode == rightEnd) {
                aux.next = leftNode;
                leftNode = leftNode.next;
            } else if (Math.random() > 0.5) {
                aux.next = leftNode;
                leftNode = leftNode.next;
            } else {
                aux.next = rightNode;
                rightNode = rightNode.next;
            }
            aux = aux.next;
        }
        aux.next = rightEnd;
        if (hi == a.last)
            a.last = aux;
        return aux;
    }

    public static void sort(LinkedList a) {
        int N = a.size();
        Node previous = null;
        Node lo = a.first;
        Node mid = a.first;
        Node hi = a.first;
        Node nextLo;
        while (true) {
            mid = findSortedSubList(lo);
            if (mid == a.last) {
                if (lo == a.first) //array is sorted
                    break;
                else {
                    lo = a.first;
                    previous = null;
                    continue;
                }
            }
            hi = findSortedSubList(mid.next);
            nextLo = hi.next;
            Node temp = merge(a, lo, mid, hi, previous);
            lo = nextLo == null ? a.first : nextLo;
            previous = nextLo == null ? null : temp;
        }
    }

    public static void shuffle(LinkedList a) {
        int N = a.size();
        for (int sz = 1; sz < N; sz = sz + sz) {
            Node previous = null;
            for (Node lo = a.first; findNodeAfterNPositions(lo, sz) != a.last; lo = findNodeAfterNPositions(previous, 1)) {
                System.out.println(sz);
                previous = mergeShuffle(a, lo, findNodeAfterNPositions(lo, sz - 1), findNodeAfterNPositions(lo, sz + sz - 1), previous);
                print(a);
            }
        }


    }

    public static Node findSortedSubList(Node start) {
        Node result = start;
        while (result.next != null && result.item.compareTo(result.next.item) <= 0)
            result = result.next;
        return result;
    }

    public static Node findNodeAfterNPositions(Node first, int N) {
        Node result = first;
        for (int i = 0; i < N; i++) {
            if (result.next != null)
                result = result.next;
            else
                break;
        }
        return result;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public boolean isEmpty() {
        return first == null;
    } // Or: N == 0.

    public int size() {
        return N;
    }

    public static void print(LinkedList a) {
        Node current = a.first;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();

    }

    public static boolean isSorted(LinkedList a) {
        Node current = a.first;
        while (current.next != null) {
            if (current.item.compareTo(current.next.item) > 0) return false;
            current = current.next;
        }
        return true;

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


    public static void main(String[] args) throws FileNotFoundException {
        LinkedList list1 = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list1.enqueue(StdRandom.uniform());
        }
        //sort(list1);
        print(list1);
        shuffle(list1);
        print(list1);
    }
}
