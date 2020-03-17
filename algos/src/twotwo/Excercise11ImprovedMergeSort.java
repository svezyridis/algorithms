package twotwo;

import edu.princeton.cs.algs4.*;

public class Excercise11ImprovedMergeSort {


    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {
        int indexLeft = low;
        int indexRight = middle + 1;

        for (int i = low; i <= high; i++) {
            if (indexLeft > middle) {
                aux[i] = array[indexRight++];
            } else if (indexRight > high) {
                aux[i] = array[indexLeft++];
            } else if (array[indexLeft].compareTo(array[indexRight]) <= 0) {
                aux[i] = array[indexLeft++];   // to ensure stability
            } else {
                aux[i] = array[indexRight++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
// Allocate space just once.
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] array, Comparable[] aux, int low, int high) { // Sort a[lo..hi].

        int middle = low + (high - low) / 2;
        if (high - low < 10) {
            insertionSort(aux, low, high);
            return;
        }
        sort(aux, array, low, middle); // Sort left half.
        sort(aux, array, low, middle);
        sort(aux, array, middle + 1, high);
        //Improvement #2 - Skip merge if the array is already in order
        if (array[middle].compareTo(array[middle + 1]) <= 0) {
            System.arraycopy(array, low, aux, low, high - low + 1);
            return;
        }

        merge(array, aux, low, middle, high);
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
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
        assert !isSorted(a);
        show(a);
    }
}
