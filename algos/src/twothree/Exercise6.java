package twothree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Exercise6 {

    public static int total;

    private static int[] partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        int count = 0;
        while (true) {
            count++;
            while (less(a[++i], v)) {
                count++;
                if (i == hi) break;
            }
            count++;
            while (less(v, a[--j])) {
                count++;
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return new int[]{j, count};
    }

    public static double log(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static int sort(Comparable[] a) {
        total = 0;
        StdRandom.shuffle(a);
        return sort(a, 0, a.length - 1);
    }

    private static int sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
        if (hi <= lo) {
            total += 1;
            return 0;
        }
        if (hi - lo == 1) {
            total++;
        }
        int count = 0;
        int[] result = partition(a, lo, hi);
        count += result[1];
        int j = result[0];
        count += sort(a, lo, j - 1);
        count += sort(a, j + 1, hi); // Sort right half.
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
        for (int N = 100; N <= 10000; N *= 10) {
            Double[] a = new Double[N];
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            int actual = sort(a);
            System.out.println(actual + " " + 2 * N * Math.log(N));
            System.out.println(total);
        }
    }
}
