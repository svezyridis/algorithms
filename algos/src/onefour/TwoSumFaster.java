package onefour;

/**
 * TwoSumFaster
 */
public class TwoSumFaster {
    public static int count(int[] a, int low, int high, int sum) {
        int cnt = 0;
        while (high > low) {
            if (a[high] + a[low] > sum)
                high--;
            else if (a[high] + a[low] < sum)
                low++;
            else {
                cnt++;
                low++;
                //high--;
            }
        }
        return cnt;
    }
}