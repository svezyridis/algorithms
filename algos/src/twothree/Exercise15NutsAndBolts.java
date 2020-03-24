package twothree;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Exercise15NutsAndBolts {
    public static int partition(Comparable[] a, int lo, int hi, Comparable pivot) {
        int i = lo - 1, j = hi + 1;
        while (true) {
            while (less(a[++i], pivot)) if (i == hi) break;
            while (less(pivot, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
            --i;
            ++j;
        }
        return j;
    }

    public static void sort(Comparable[] nuts, Comparable[] bolts) {
        StdRandom.shuffle(bolts);
        StdRandom.shuffle(nuts);
        sort(nuts, bolts, 0, nuts.length - 1);
    }

    public static void sort2(Comparable[] nuts, Comparable[] bolts) {
        StdRandom.shuffle(bolts);
        StdRandom.shuffle(nuts);
        sort2(nuts, bolts, 0, nuts.length - 1);
    }

    public static void sort(Comparable[] nuts, Comparable[] bolts, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nuts, lo, hi, bolts[hi]);
        partition(bolts, lo, hi, nuts[j]);
        sort(nuts, bolts, lo, j - 1);
        sort(nuts, bolts, j + 1, hi);
    }

    public static void sort2(Comparable[] nuts, Comparable[] bolts, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nuts, lo, hi, bolts[hi]);
        partition2(bolts, lo, hi, nuts[j]);
        sort(nuts, bolts, lo, j - 1);
        sort(nuts, bolts, j + 1, hi);
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // Similar to standard partition method. Here we pass the pivot element
    // too instead of choosing it inside the method.
    private static int partition2(Comparable[] arr, int low, int high, Comparable pivot) {
        int i = low;
        Comparable temp1, temp2;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
            } else if (arr[j].compareTo(pivot) == 0) {
                temp1 = arr[j];
                arr[j] = arr[high];
                arr[high] = temp1;
                j--;
            }
        }
        temp2 = arr[i];
        arr[i] = arr[high];
        arr[high] = temp2;

        // Return the partition index of an array based on the pivot
        // element of other array.
        return i;
    }


    private static void show(Comparable[] a) { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = 1000000;
        Double[] nuts = new Double[N];
        for (int z = 0; z < N; z++)
            nuts[z] = Double.valueOf(z);
        Double[] bolts = Arrays.copyOf(nuts, N);
        Stopwatch timer = new Stopwatch();
        sort(nuts, bolts);
        System.out.println(timer.elapsedTime());
        boolean sorted = true;
        for (int k = 0; k < N; k++)
            if (nuts[k] != bolts[k])
                sorted = false;
        System.out.println(sorted);
        timer = new Stopwatch();
        sort2(nuts, bolts);
        System.out.println(timer.elapsedTime());
        sorted = true;
        for (int k = 0; k < N; k++)
            if (nuts[k] != bolts[k])
                sorted = false;
        System.out.println(sorted);
    }
}
