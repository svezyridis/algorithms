package onefour;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise16
 */
public class Excercise16 {

    public static int[] closestPair(double[] a) {
        int[] res = new int[2];
        if (a.length < 2)
            return new int[] { -1, -1 };
        Arrays.sort(a);
        double min = Math.abs(a[0] - a[1]);
        res = new int[] { 0, 1 };
        for (int i = 1; i < a.length - 1; i++) {
            if (Math.abs(a[i] - a[i + 1]) < min) {
                min = Math.abs(a[i] - a[i + 1]);
                res = new int[] { i, i + 1 };
            }
        }
        return res;
    }

    public static void main(String[] args) {
        double[] array1 = {-5.2, 9.4, 20, -10, 21.1, 40, 50, -20};
        double[] array2 = {-4, -3, 0, 10, 20};
        double[] array3 = {-10, -3, 0, 2, 4, 20};

        int[] closestPair1 = closestPair(array1);
        int[] closestPair2 = closestPair(array2);
        int[] closestPair3 = closestPair(array3);

        StdOut.println("Closest pair: " + array1[closestPair1[0]] + " " + array1[closestPair1[1]] + " Expected: 20.0 21.1");
        StdOut.println("Closest pair: " + array2[closestPair2[0]] + " " + array2[closestPair2[1]] + " Expected: -4.0 -3.0");
        StdOut.println("Closest pair: " + array3[closestPair3[0]] + " " + array3[closestPair3[1]] + " Expected: 0.0 2.0");
    }
}