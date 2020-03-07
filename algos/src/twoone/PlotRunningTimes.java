package twoone;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlotRunningTimes {

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
        int startValue = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);
        double multiplier = Double.parseDouble(args[3]);
        int noOfAttempts = Integer.parseInt(args[4]);
        LinkedHashMap<Integer, Double> times = new LinkedHashMap<>();
        int N;

        for (N = startValue; N <= max; N = (int) (N * multiplier)) {
            System.out.println(N);
            double t = timeRandomInput(alg1, N, noOfAttempts);
            times.put(N, t);
            System.out.println(t);

        }

        StdDraw.setCanvasSize(600, 600);
        double maxTime = 0;
        for (
                Map.Entry<Integer, Double> entry : times.entrySet()) {
            if (entry.getValue() > maxTime)
                maxTime = entry.getValue();
        }
        StdDraw.setYscale(-0.05 * maxTime, 1.2 * maxTime);
        StdDraw.setXscale(-0.05 * max, 1.2 * max);
        StdDraw.line(0, 0, 0, 1.2 * maxTime);
        StdDraw.line(0, 0, 1.2 * max, 0);
        StdDraw.setPenRadius(0.01);
        for (
                Map.Entry<Integer, Double> entry : times.entrySet()) {
            StdDraw.point(entry.getKey(), entry.getValue());
        }


    }


}
