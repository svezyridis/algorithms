package onefive;

import java.text.DecimalFormat;

/**
 * Excercise21
 */
public class Excercise21 {

    public static void main(String[] args) {
        for (int N = 200; N < 2000; N += 200) {
            int total = 0;
            for (int i = 1; i < 100; i++) {
                total += ErdosRenyi.count(N);
            }
            double estimate = 0.5 * N * Math.log(N);
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("Average of 100 trials for N=" + N + ": " + total / 100 + " 1/2Nln(N)= " + df.format(estimate));
        }
    }
}