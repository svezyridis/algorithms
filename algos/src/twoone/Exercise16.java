package twoone;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Exercise16 {
    public static boolean check(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        Arrays.sort(a);
        boolean same = true;
        for (Comparable item : b) {
            if (!exists(item, a)) {
                same = false;
                break;
            }
        }
        return same;
    }

    public static boolean exists(Comparable item, Comparable[] array) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (item.compareTo(array[mid]) < 0) hi = mid - 1;
            else if (item.compareTo(array[mid]) > 0) lo = mid + 1;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Double[] a = new Double[1000];
        for (int j = 0; j < 1000; j++)
            a[j] = StdRandom.uniform();
        System.out.println(check(a));
    }
}

