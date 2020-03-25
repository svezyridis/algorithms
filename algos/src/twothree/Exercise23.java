package twothree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Exercise23 {

    public static void sort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] array, int low, int high) {

        if (low >= high) {
            return;
        }

        int i = low;
        int j = high + 1;

        int p = low;
        int q = high + 1;

        Comparable pivot = array[low];

        while (true) {

            if (i >= low && array[i].compareTo(pivot) == 0) {
                exch(array, ++p, i);
            }
            if (j <= high && array[j].compareTo(pivot) == 0) {
                exch(array, --q, j);
            }

            while (less(array[++i], pivot)) {
                if (i == high) {
                    break;
                }
            }

            while (less(pivot, array[--j])) {
                if (j == low) {
                    break;
                }
            }

            //pointers cross
            if (i == j && array[i].compareTo(pivot) == 0) {
                exch(array, ++p, i);
            }
            if (i >= j) {
                break;
            }

            exch(array, i, j);
        }

        i = j + 1;

        for (int k = low; k <= p; k++) {
            exch(array, k, j--);
        }
        for (int k = high; k >= q; k--) {
            exch(array, k, i++);
        }

        quickSort(array, low, j);
        quickSort(array, i, high);
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
        show(a);
    }
}
