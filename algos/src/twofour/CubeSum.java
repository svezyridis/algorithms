package twofour;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

public class CubeSum {
    static MinPQ<pair> pq = new MinPQ(new pairComperator());

    private static class pair {
        int i;
        int j;
        int sum;

        public pair(int i, int j) {
            this.i = i;
            this.j = j;
            this.sum = (int) Math.pow(i, 3) + (int) Math.pow(j, 3);
        }
    }

    private static class pairComperator implements Comparator<pair> {

        @Override
        public int compare(pair o1, pair o2) {
            if (o1.sum > o2.sum) return 1;
            else if (o1.sum < o2.sum) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        for (int i = 0; i <= N; i++) {
            pq.insert(new pair(i, 0));
        }
        boolean first = true;
        pair current = null;
        pair previous = null;
        while (!pq.isEmpty()) {
            previous = current;
            current = pq.delMin();
            if (previous != null && previous.sum == current.sum) {
                if (previous.i != previous.j && previous.i != current.i && previous.i != current.j && previous.j != current.i && previous.j != current.j && current.i != current.j)
                    System.out.println("a=" + previous.i + " b=" + previous.j + " c=" + current.i + " d=" + current.j + " sum=" + current.sum);
            }
            if (current.j < N) {
                pq.insert(new pair(current.i, current.j + 1));
            }
        }
    }
}
