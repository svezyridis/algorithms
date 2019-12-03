package onefive;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * WeightedQuickUnion
 */
public class WeightedQuickUnion {

    private int[] id;
    private int count;
    private int[] sz;
    int accesses;

    // access to component id (site indexed)
    // number of components
    public WeightedQuickUnion(int N) { // Initialize component id array.
        count = N;
        accesses = 0;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getAccesses() {
        return accesses;
    }

    public int find(int p) {
        // accesses++;
        while (p != id[p]) {
            // accesses++;
            p = id[p];
        }

        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        accesses++;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();

        WeightedQuickUnion uf = new WeightedQuickUnion(N);
        int access = 0;
        long start = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            /**
             * StdOut.print("id contents: "); for (int x : uf.id) { StdOut.print(x + " "); }
             */

            // StdOut.println("\n" + (uf.getAccesses() - access) + " times array accessed");
            access = uf.getAccesses();

        }
        StdOut.println(uf.count() + " components");
        System.out.println(System.currentTimeMillis() - start + " ms to complete");

    }

}