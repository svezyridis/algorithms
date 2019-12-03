package onefour;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise17
 */
public class Excercise17 {
    public static double[] farthestPair(double[] a) {
        double[] res = new double[2];

        double min = a[0], max = a[0];
        res[0] = a[0];
        res[1] = a[1];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
                res[0] = a[i];
            }
            if (a[i] > max) {
                max = a[i];
                res[1] = a[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        double[] array1 = { -5.2, 9.4, 20, -10, 21.1, 40, 50, -20 };
        double[] array2 = { -4, -3, 0, 10, 20 };
        double[] array3 = { -10, -3, 0, 2, 4, 20 };

        double[] farthestPair1 = farthestPair(array1);
        double[] farthestPair2 = farthestPair(array2);
        double[] farthestPair3 = farthestPair(array3);

        StdOut.println("Farthest pair: " + farthestPair1[0] + " " + farthestPair1[1] + " Expected: -20.0 50.0");
        StdOut.println("Farthest pair: " + farthestPair2[0] + " " + farthestPair2[1] + " Expected: -4.0 20.0");
        StdOut.println("Farthest pair: " + farthestPair3[0] + " " + farthestPair3[1] + " Expected: -10.0 20.0");
    }
}