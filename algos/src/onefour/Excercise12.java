package onefour;

import java.util.Arrays;

/**
 * Excercise12
 */
public class Excercise12 {

    public static void printSameValues(int[] a, int[] b) {
        int aIndex = 0;
        int bIndex = 0;
        int i = 0;
        while (aIndex < a.length && bIndex < b.length) {
            i++;
            if (a[aIndex] == b[bIndex]) {
                int val = a[aIndex];
                System.out.println(a[aIndex]);
                while (bIndex < b.length && b[bIndex] == val)
                    bIndex++;
                while (aIndex < a.length && a[aIndex] == val)
                    aIndex++;
            }
            while (aIndex < a.length && bIndex < b.length && a[aIndex] < b[bIndex])
                aIndex++;
            while (bIndex < b.length && aIndex < a.length && b[bIndex] < a[aIndex])
                bIndex++;
        }
        System.out.println(i);
    }

    public static void main(String[] args) {
        int[] array1 = { -2, 1, 2, 2, 5, 6, 6, 8, 25, 30 };
        int[] array2 = { 0, 1, 2, 2, 2, 3, 4, 5, 10, 20, 25, 30 };

        printSameValues(array1, array2);
    }
}