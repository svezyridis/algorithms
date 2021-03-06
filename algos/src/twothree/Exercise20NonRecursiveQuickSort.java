package twothree;

import edu.princeton.cs.algs4.*;

public class Exercise20NonRecursiveQuickSort {

    private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Stack<Integer[]> stack = new Stack();
        stack.push(new Integer[]{0, a.length - 1});
        while (!stack.isEmpty()) {
            Integer[] array = stack.pop();
            int j = partition(a, array[0], array[1]);
            if (j - array[0] >= array[1] - j) {
                if (j - array[0] > 1)
                    stack.push(new Integer[]{array[0], j - 1});
                if (array[1] - j > 1)
                    stack.push(new Integer[]{j + 1, array[1]});
            } else {
                if (array[1] - j > 1)
                    stack.push(new Integer[]{j + 1, array[1]});
                if (j - array[0] > 1)
                    stack.push(new Integer[]{array[0], j - 1});
            }
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi); // Sort right half.

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        Stopwatch timer = new Stopwatch();
        sort(a);
        System.out.println("elapsed time: " + timer.elapsedTime());
        System.out.println(isSorted(a));
    }
}
