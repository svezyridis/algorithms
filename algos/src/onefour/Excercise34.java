package onefour;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise34
 */

public class Excercise34 {

    public static int hotOrCold(int number, int N) {
        int lo = 0;
        int hi = N;
        int current;
        int cnt = 0;
        String res;
        while (lo <= hi) {
            cnt++;
            current = lo + (hi - lo) / 2;
            res = isHotOrCold(current, current + 1, number);
            if (current == number) {
                System.out.println("Found number after " + cnt + " guesses");
                return current;
            } else if (res.equals("hot")) {
                lo = current + 1;
            } else {
                hi = current - 1;
            }
        }
        return -1;
    }

    public static String isHotOrCold(int previous, int current, int number) {
        String res = Math.abs(previous - number) > Math.abs(current - number) ? "hot" : "cold";
        return res;
    }

    public static void main(String[] args) {
        int N = 100000000;

        int number = (int) Math.floor(Math.random() * N);
        StdOut.println("Hot or Cold: " + hotOrCold(3, 10) + " Expected: 3");
        StdOut.println("Hot or Cold: " + hotOrCold(12, 20) + " Expected: 12");
        StdOut.println("Hot or Cold: " + hotOrCold(11, 10) + " Expected: -1");
        StdOut.println("Hot or Cold: " + hotOrCold(number, N) + " Expected: " + number);
    }
}