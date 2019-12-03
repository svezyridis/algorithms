package onefour;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise18
 */
public class Excercise18 {
    public static int localMinimum(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        if (a.length == 0)
            return -1;
        if (a.length == 1)
            return 0;
        if (a.length == 2)
            return a[0] > a[1] ? 1 : 0;

        while (lo <= hi) {
            int med = lo + (hi - lo) / 2;
            // Corner case
            if (med == 0) {
                if (a[med] < a[med + 1])
                    return med;
                else
                    return -1;
            }

            // Corner case
            if (med == a.length - 1) {
                if (a[med] < a[med - 1])
                    return med;
                else
                    return -1;
            }

            if (a[med] < a[med + 1] && a[med] < a[med - 1])
                return med;
            else if (a[med + 1] > a[med - 1])
                hi = med - 1;
            else if (a[med + 1] < a[med - 1])
                lo = med + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array1 = { 10, -9, 20, 25, 21, 40, 50, -20 };
        int[] array2 = { -4, -3, 9, 4, 10, 2, 20 };
        int[] array3 = { 5, -3, -5, -6, -7, -8 };
        int[] array4 = { 5 };
        int[] array5 = { 10, 20 };
        int[] array6 = { 7, 20, 30 };

        int localMinimum1 = localMinimum(array1);
        int localMinimum2 = localMinimum(array2);
        int localMinimum3 = localMinimum(array3);
        int localMinimum4 = localMinimum(array4);
        int localMinimum5 = localMinimum(array5);
        int localMinimum6 = localMinimum(array6);

        StdOut.println("Local Minimum: " + array1[localMinimum1] + " Expected: -9 or -20");
        StdOut.println("Local Minimum: " + array2[localMinimum2] + " Expected: 4 or -4 or 2");
        StdOut.println("Local Minimum: " + array3[localMinimum3] + " Expected: -8");
        StdOut.println("Local Minimum: " + array4[localMinimum4] + " Expected: 5");
        StdOut.println("Local Minimum: " + array5[localMinimum5] + " Expected: 10");
        StdOut.println("Local Minimum: " + array6[localMinimum6] + " Expected: 7");

    }
}