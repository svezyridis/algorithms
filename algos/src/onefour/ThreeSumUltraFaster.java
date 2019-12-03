package onefour;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * ThreeSumUltraFaster
 */
public class ThreeSumUltraFaster {

    public static int count(int[] a) { // Count triples that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            cnt += TwoSumFaster.count(a, i, N-1, -a[i]);
        return cnt;
    }

    public static void main(String[] args) {
        String filename = args[0];
        int[] a = new In(filename).readAllInts();
        StdOut.println(count(a));
    }
}