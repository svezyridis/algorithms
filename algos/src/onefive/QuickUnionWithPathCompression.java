package onefive;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * QuickUnionWithPathCompression
 */
public class QuickUnionWithPathCompression {
    private int[] id;
    private int count;
    int accesses;

    // access to component id (site indexed)
    // number of components
    public QuickUnionWithPathCompression(int N) { // Initialize component id array.
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
            accesses ++;
            id[p]=id[id[p]];
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

    public static void main(String[] args) {
        int N = StdIn.readInt();

        QuickUnionWithPathCompression uf = new QuickUnionWithPathCompression(N);
        int access = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            StdOut.print("id contents: ");
            for (int x : uf.id) {
                StdOut.print(x + " ");
            }

            StdOut.println("\n" + (uf.getAccesses() - access) + " times array accessed");
            access = uf.getAccesses();

        }
    }
}