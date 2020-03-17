package twotwo;

import edu.princeton.cs.algs4.*;

public class Exercise10FasterSort {
    private static Comparable[] aux;


    public static void merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
// Allocate space just once.
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // Sort left half.
        sort(a, mid + 1, hi); // Sort right half.
        merge(a, lo, mid, hi); // Merge results (code on page 271).
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
        for (int i = 1; i < a.length; i++) {
            System.out.println(less(a[i], a[i - 1]));
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
        Double[] arr = new Double[100000];
        double mergetotal = 0;
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100000; i++)
                arr[i] = StdRandom.uniform();
            Stopwatch timer = new Stopwatch();
            Merge.sort(arr);
            mergetotal += timer.elapsedTime();
        }

        System.out.println(mergetotal + " time elapsed for merge");

        double fastermergetotal = 0;
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100000; i++)
                arr[i] = StdRandom.uniform();
            Stopwatch timer = new Stopwatch();
            Merge.sort(arr);
           fastermergetotal += timer.elapsedTime();
        }
        System.out.println(fastermergetotal + " time elapsed for fastermerge");
    }
}
