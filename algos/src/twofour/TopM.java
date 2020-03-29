package twofour;

import edu.princeton.cs.algs4.*;

import java.util.Comparator;

public class TopM {
    private static class Point {
        double x;
        double y;
        double z;

        public Point(String point) {
            String[] a = point.split("\\s+");
            x = Double.parseDouble(a[0]);
            y = Double.parseDouble(a[1]);
            z = Double.parseDouble(a[2]);
        }

        public double getDistance() {
            return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
        }

    }

    public static class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            if (o1.getDistance() > o2.getDistance()) return 1;
            else if (o2.getDistance() > o1.getDistance()) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        edu.princeton.cs.algs4.MaxPQ<Point> pq = new edu.princeton.cs.algs4.MaxPQ<Point>(M + 1, new PointComparator());
        while (StdIn.hasNextLine()) { // Create an entry from the next line and put on the PQ.
            pq.insert(new Point(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMax();
        }
        while (!pq.isEmpty()) System.out.println(pq.delMax().getDistance());
    }
}
