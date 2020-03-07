package twoone;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class DoublingTest {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Heap")) Heap.sort(a);
        if (alg.equals("Exercise11")) Exercise11.sort(a);
        if (alg.equals("Exercise29")) Exercise29.sort(a);
        if (alg.equals("SentinelInsertion")) SentinelInsertion.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) { // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) { // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }


    public static void main(String[] args) {
        String alg1 = args[0];
        int T = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);
        double t = timeRandomInput(alg1, 1000, T); // total for first time
        System.out.println("Time for N=1000: " + t / T);
        for (int N = 2000; N <= max; N *= 2) {
            double previous = t;
            t = timeRandomInput(alg1, N, T);
            System.out.println("Time for N=" + N + ": " + t / T);
            System.out.println("ratio: " + t / previous);
        }
    }
}
