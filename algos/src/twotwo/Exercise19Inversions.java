package twotwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Exercise19Inversions {


    public static int count(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        int count = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += mid + 1 - i;
            } else a[k] = aux[i++];
        return count;
    }

    public static int count(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return count(a, aux, 0, a.length - 1);
    }

    private static int count(Comparable[] a, Comparable[] aux, int lo, int hi) { // Sort a[lo..hi].
        int count = 0;
        int N=a.length;
        if (hi <= lo) return count;
        int mid = lo + (hi - lo) / 2;
        count += count(a, aux, lo, mid); // Sort left half.
        count += count(a, aux, mid + 1, hi); // Sort right half.
        count += count(a, aux, lo, mid, hi); // Merge results (code on page 271).
        return count;
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
        Integer[] a = new Integer[]{ 1, 20, 6, 4, 5};
        System.out.println(count(a));
    }

}
