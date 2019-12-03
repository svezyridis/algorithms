package onefour;

import java.math.BigInteger;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ThreeSum {
    public static int count(int[] a) { // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++) {
                    if (i + j + k == 0) {
                        cnt++;
                    }
                }
        return cnt;
    }

    public static void main(String[] args) {
        String filename = args[0];
        int[] a = new In(filename).readAllInts();
        StdOut.println(count(a));
    }
}