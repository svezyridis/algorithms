package twoone;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;
import java.util.ArrayList;

public class Distribution {

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
        int N = Integer.parseInt(args[2]);
        Double[] a = new Double[N];
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-1, 1.1 * T);
        StdDraw.setPenRadius((double) 1 / (double) T * 5);
        Double total = 0.0;
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++)
                a[j] = StdRandom.uniform();
            double time = time(alg1, a);
            if (i == 0) {
                StdDraw.setYscale(-1.2 * time, 1.05 * time);
            }
            total += time;
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(i, time);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, total / (double) (i + 1));
        }

    }
}
