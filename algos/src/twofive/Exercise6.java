package twofive;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Collections;

public class Exercise6 {

    public static int partition(int[] array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = array[lo];
        while (true) {
            while (array[++i] < v) if (i == hi) break;
            while (v < array[--j]) if (j == lo) break;
            if (j <= i)
                break;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        int temp = array[j];
        array[j] = v;
        array[lo] = temp;
        return j;
    }

    public static int select(int[] a, int k) {
        StdRandom.shuffle(a);
        return select(a, k, 0, a.length - 1);
    }

    public static int select(int[] a, int k, int lo, int hi) {
        int j = partition(a, lo, hi);
        if (j == k)
            return k;
        if (j > k)
            return select(a, k, lo, j - 1);
        else
            return select(a, k, j + 1, hi);
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 7, 3, 6, 8, 9, 5, 4};

        System.out.println(select(array, 3));
    }

}
