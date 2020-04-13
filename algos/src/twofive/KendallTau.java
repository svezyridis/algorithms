package twofive;

public class KendallTau {
    public static int count(int[] a, int[] b, int[] aux, int lo, int mid, int hi) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (b[aux[i]] > b[aux[j]]) {
                count += mid - i + 1;
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];

            }
        }
        return count;

    }


    public static int kendall(int[] a, int[] b) {
        int[] aux1 = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            aux1[b[i]] = i;
        }
        int[] aux2 = new int[a.length];
        return kendall(a, aux1, aux2, 0, a.length - 1);

    }

    public static int kendall(int[] array, int[] aux1, int[] aux2, int lo, int hi) {
        int count = 0;
        if (lo >= hi) return 0;

        int mid = lo + (hi - lo) / 2;
        count += kendall(array, aux1, aux2, lo, mid);
        count += kendall(array, aux1, aux2, mid + 1, hi);
        count += count(array, aux1, aux2, lo, mid, hi);
        return count;
    }

    public static void main(String[] args) {
        int[] first = new int[]{0, 1, 2, 3, 4};
        int[] second = new int[]{2, 3, 0, 1, 4};
        System.out.println(kendall(second, first));
        for (int i = 0; i < first.length; i++) {
            System.out.println(first[i] + " " + second[i]);
        }
        System.out.println(kendall(second, first));
    }
}
