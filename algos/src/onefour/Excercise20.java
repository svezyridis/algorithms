package onefour;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise20
 */
public class Excercise20 {

    public static int bitonicSearch(int[] a, int key) {
        // Find turning point
        int lo = 0;
        int hi = a.length - 1;
        int med = 0;
        int cnt = 0;
        while (lo <= hi) {
            cnt++;
            med = lo + (hi - lo) / 2;
            if (a[med] == key)
                return med;
            if (med == 0) {
                med = a[med] > a[med + 1] ? med : med + 1;
                break;
            }
            if (med == a.length-1) {
                med = a[med] > a[med - 1] ? med : med - 1;
                break;
            }
            if (a[med] > a[med - 1] && a[med + 1] < a[med])
                break;
            else if (a[med] > a[med - 1] && a[med + 1] > a[med])
                lo = med + 1;
            else
                hi = med - 1;
        }
        System.out.println(cnt+" after top search");

        // regular binary search
        lo = 0;
        hi = med;
        while (lo <= hi) {
            cnt++;
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        System.out.println(cnt+" after ascending");

        // descending binary search
        lo = med;
        hi = a.length - 1;
        while (lo <= hi) {
            cnt++;
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid])
                hi = mid - 1;
            else if (key < a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        System.out.println(cnt+" after descending");
        return -1;
    }

    public static void main(String[] args) {
        int[] array1 = { 1, 2, 3, 4, -1, -2, -3 };
        int[] array2 = { 1, 5, 4, 3, 2, 0 };
        int[] array3 = new int[10000000];
        for(int i=0; i<array3.length;i++){
            array3[i]=i;
        }
        int[] array4 = { 2, 4, 8, 16, 32 };
        int[] array5 = { 2, 1 };
        int[] array6 = { 9 };

  
        int indexOfElement4 = bitonicSearch(array3, -1);
        StdOut.println("Index of element: " + indexOfElement4 + " Expected: -1");

    }
}