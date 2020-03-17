package twotwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise16 {
    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        System.out.println("merging " + lo + " - " + mid + " with " + (mid + 1) + " " + hi);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        show(a);
    }

    public static void sort(Comparable[] a) { // Do lg N passes of pairwise merges.
        int N = a.length;
        aux = new Comparable[N];
        int lo1 = 0;
        int hi1 = 0;
        int lo2 = 0;
        int hi2 = 0;
        while (true) {
            System.out.println(lo1);
            hi1 = findSortedSubarray(a, lo1);
            if (lo1 == 0 && hi1 == N - 1)
                break;
            System.out.println(hi1);
            lo2 = hi1 != N - 1 ? hi1 + 1 : hi1;
            hi2 = findSortedSubarray(a, lo2);
            System.out.println(hi2);
            merge(a, lo1, hi1, hi2);
            if (hi2 == N - 1)
                lo1 = 0;
            else
                lo1 = hi2 + 1;

        }

    }

    private static int findSortedSubarray(Comparable[] array, int lo) {
        int N = array.length;
        int result = lo;
        int i;
        for (i = lo; i < N; i++) {
            if (i == N - 1 || !(array[i].compareTo(array[i + 1]) <= 0))
                break;
        }
        return i;
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
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
