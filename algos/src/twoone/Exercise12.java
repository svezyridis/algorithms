package twoone;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise12 {
    public static void main(String[] args) {
        for(int i=100; i<1000000; i*=10){
            Double[] a = new Double[i];
            for (int j = 0; j < i; j++)
                a[j] = StdRandom.uniform();
            Shell.sort(a);
        }
    }
}
