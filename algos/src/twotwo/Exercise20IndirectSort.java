package twotwo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Exercise20IndirectSort {
    public static int[] aux;

    public static void merge(Comparable[] a, int[] result, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = result[k];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) result[k] = aux[j++];
            else if (j > hi) result[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) result[k] = aux[j++];
            else result[k] = aux[i++];
        }
    }

    public static int[] sort(Comparable[] a) {
        int[] result = new int[a.length];
        aux = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = i;
        }
        sort(a, result, 0, a.length - 1);
        return result;
    }

    private static void sort(Comparable[] a, int[] result, int lo, int hi) { // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, result, lo, mid); // Sort left half.
        sort(a, result, mid + 1, hi); // Sort right half.
        merge(a, result, lo, mid, hi); // Merge results (code on page 271).
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static boolean isSorted(Comparable[] a, int[] indexes) { // Test whether the array entries are in order.
        for (int i = 1; i < indexes.length; i++)
            if (less(a[indexes[i]], a[indexes[i - 1]])) return false;
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
        for (int i = 0; i < 10; i++) {
            Double[] array = new Double[100];
            for (int j = 0; j < 100; j++) {
                array[j] = StdRandom.uniform();
            }
            int[] result = sort(array);
            System.out.println(isSorted(array, result));
        }
    }

}
