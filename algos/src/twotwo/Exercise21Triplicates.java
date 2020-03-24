package twotwo;

import twoone.Merge;

public class Exercise21Triplicates {

    public static Comparable findTriplicates(Comparable[] a, Comparable[] b, Comparable[] c) { // Merge a[lo..mid] with a[mid+1..hi].
        int i = 0, j = 0;
        for (int k = 0; k < a.length + b.length; k++)
            if (i == a.length) break;
            else if (j == b.length) break;
            else if (a[i].compareTo(b[j]) < 0) i++;
            else if (a[i].compareTo(b[j]) > 0) j++;
            else {
                int lo = 0;
                int hi = c.length;
                System.out.println("looking for: " + a[i]);
                while (hi > lo) {
                    int mid = lo + (hi - lo) / 2;
                    if (c[mid].compareTo(a[i]) > 0)
                        hi = mid - 1;
                    else if (c[mid].compareTo(a[i]) < 0)
                        lo = mid + 1;
                    else
                        return c[mid];
                }
            }
        return null;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] ar1 = {2, 5, 6, 8, 9, 1, 10, 23, 12};
        Integer[] ar2 = {0, 76, 534, 33, 4, 7, 9, 5};
        Integer[] ar3 = {5, 12, 2, 3, 6};
        Merge.sort(ar1);
        Merge.sort(ar2);
        Merge.sort(ar3);
        System.out.println(findTriplicates(ar1, ar2, ar3));
    }
}
