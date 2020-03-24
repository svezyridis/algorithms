package twotwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise16NaturalMergeSort {
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
        int N = a.length;
        aux = new Comparable[N];
        int lo = 0;
        int mid = 0;
        int hi = 0;
        while (true) {
            mid = findSortedSubarray(a, lo);
            if (mid == N - 1) {
                if (lo == 0) //array is sorted
                    break;
                else {
                    lo = 0;
                    continue;
                }
            }
            hi = findSortedSubarray(a, mid + 1);
            merge(a, lo, mid, hi);
            lo = hi == N - 1 ? 0 : hi + 1;
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
        //show(a);
        System.out.println(isSorted(a));
    }
}
