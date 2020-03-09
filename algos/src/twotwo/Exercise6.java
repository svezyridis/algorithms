package twotwo;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class Exercise6 {

    private static Comparable[] aux;

    public static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }


    public static int merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;
        int count = 0;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++)
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += 2;
            } else a[k] = aux[i++];
        return count + 4;
    }

    public static int sortTD(Comparable[] a) {
        aux = new Comparable[a.length];
        return sort(a, 0, a.length - 1);
    }

    public static int sortBU(Comparable[] a) { // Do lg N passes of pairwise merges.
        int count = 0;
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) // sz: subarray size
            for (int lo = 0; lo < N - sz; lo += sz + sz) // lo: subarray index
                count += merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
        return count;
    }

    private static int sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
        int count = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        count += sort(a, lo, mid); // Sort left half.
        count += sort(a, mid + 1, hi); // Sort right half.
        count += merge(a, lo, mid, hi); // Merge results (code on page 271).
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

    public static void main(String[] args) {
        int buCount[] = new int[512];
        int tdCount[] = new int[512];
        StdDraw.setCanvasSize(600, 600);
        for (int N = 1; N <= 512; N++) {
            Comparable[] array = new Comparable[N];
            Comparable[] array2 = new Comparable[N];
            for (int i = 0; i < array.length; i++) {
                array[i] = StdRandom.uniform();
                array2[i] = array[i];
            }
            buCount[N-1] = sortBU(array);
            tdCount[N-1] = sortTD(array2);
        }
        StdDraw.setXscale(-1,513);
        StdDraw.setPenRadius(0.05);
        StdDraw.setYscale(0,6*log2(512));
        for(int i=0;i<512;i++){
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i+1,6*log2(i+1));
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i+1,buCount[i]);
            StdDraw.setPenColor(Color.GREEN);
        }

    }
}
