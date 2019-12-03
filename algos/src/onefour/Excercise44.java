package onefour;

import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Excercise44
 */
public class Excercise44 {

    public static void main(String[] args) {
        int range = Integer.parseInt(args[0]);
        StdRandom.uniform(0, range - 1);
        Set<Integer> uniqueInts = new HashSet<Integer>();
        double cnt = 0;
        for (int i = 0; i < 1000; i++) {
            uniqueInts = new HashSet<Integer>();
            while (uniqueInts.size() < range - 1) {
                uniqueInts.add(StdRandom.uniform(0, range - 1));
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(cnt / 1000);
    }
}