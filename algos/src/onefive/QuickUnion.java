
package onefive;

import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
    private int[] id;
    private int count;
    int accesses;

    // access to component id (site indexed)
    // number of components
    public QuickUnion(int N) { // Initialize component id array.
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        accesses = 0;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        accesses++;
        while (p != id[p]) {
            accesses++;
            p = id[p];
        }
        return p;
    }

    public int getAccesses() {
        return accesses;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        accesses++;
        id[pRoot] = qRoot;
        count--;
    }

    private static void initializeCanvas(int currentXMaxScale, int currentYMaxScale) {
        StdDraw.setXscale(0, currentXMaxScale);
        StdDraw.setYscale(0, currentYMaxScale);
        StdDraw.setPenRadius(.005);
    }

    private static void drawPoint(double x, double y, boolean average) {

        StdDraw.setPenColor(average ? Color.RED : Color.GRAY);
        StdDraw.point(x, y);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();

        QuickUnion uf = new QuickUnion(N);
        ArrayList<Integer> access = new ArrayList<Integer>();
        int max = 0;
        int sum = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            /**
             * StdOut.print("id contents: "); for (int x : uf.id) { StdOut.print(x + " "); }
             */
            access.add(uf.getAccesses() - sum);
            if (access.get(access.size() - 1) > max)
                max = access.get(access.size() - 1);
            sum += access.get(access.size() - 1);
        }
        StdOut.println(uf.count() + " components");
        double yOffset = 10;
        double xOffset = 10;
        initializeCanvas((int) (1.1 * access.size()), (int) (1.1 * max));

        StdDraw.line(0, yOffset, access.size(), yOffset);
        StdDraw.line(xOffset, 0, xOffset, max);
        sum = 0;
        for (int j = 0; j < access.size(); j++) {
            sum += access.get(j);
            System.out.println(j + 1 + " " + access.get(j) + " " + sum);
            drawPoint((double) j + 1 + xOffset, (double) access.get(j) + yOffset, false);
            drawPoint((double) j + 1 + xOffset, (double) sum / (j + 1) + yOffset, true);
        }

    }
}