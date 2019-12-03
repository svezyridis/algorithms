package onefour;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DoublingRatio {
    public static double timeTrial(int N) { // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSumUltraFaster.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int times = Integer.parseInt(args[0]);
        double prev = timeTrial(125);
        for (int N = 250; true; N += N) {
            double time = 0;
            for (int i = 0; i < times; i++) {
                time += timeTrial(N);
            }
            time = time / times;
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}