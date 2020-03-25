package twothree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickMedian3 {

    private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1, mid = lo + (hi - lo) / 2;
        if (a[lo].compareTo(a[mid]) > 0)
            exch(a, lo, mid);
        if (a[mid].compareTo(a[hi]) > 0)
            exch(a, mid, hi);
        if (a[lo].compareTo(a[mid]) > 0)
            exch(a, lo, mid);
        exch(a, lo, mid);
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[max]) > 0)
                max = i;
        }
        exch(a, max, a.length - 1);
        sort(a, 0, a.length - 1);
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
