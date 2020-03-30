package twofour;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise30DynamicMedianFinding {
    MinPQ<Double> minPQ = new MinPQ<>();
    MaxPQ<Double> maxPQ = new MaxPQ<>();
    int N = 0;

    public void insert(Double item) {
        if (maxPQ.size() == minPQ.size()) {
            minPQ.insert(item);
            if (!maxPQ.isEmpty() && maxPQ.max().compareTo(minPQ.min()) > 0) {
                Double temp = minPQ.delMin();
                minPQ.insert(maxPQ.delMax());
                maxPQ.insert(temp);
            }
        } else {
            if (item.compareTo(minPQ.min()) < 0)
                maxPQ.insert(item);
            else {
                minPQ.insert(item);
                maxPQ.insert(minPQ.delMin());
            }
        }
        N++;
    }

    public void deleteMedian() {
        if (N % 2 == 0) {
            minPQ.delMin();
            maxPQ.delMax();
        } else
            minPQ.delMin();
    }

    public Double median() {
        if (N % 2 == 0) {
            return (maxPQ.max() + minPQ.min()) / 2;
        } else
            return minPQ.min();
    }

    public void show() {
        for (Double val : minPQ) {
            System.out.print(val + " ");
        }
        System.out.println();
        for (Double val : maxPQ) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Exercise30DynamicMedianFinding pqm = new Exercise30DynamicMedianFinding();
        for (int i = 0; i < 9; i++) {
            pqm.insert(StdRandom.uniform());
        }
        pqm.show();
    }
}
