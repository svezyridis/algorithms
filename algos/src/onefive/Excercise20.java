package onefive;

import java.security.SecureRandom;

/**
 * Excercise20
 */
public class Excercise20 {
    private int[] id;
    private int count;
    private int[] sz;
    int accesses;
    private int size;

    public int newSite(int site) {

        if (size == id.length)
            resize(2 * id.length);
        id[size] = site;
        sz[size] = 1;
        count++;
        return size++;
    }

    private void resize(int max) { // Move stack to a new array of size max.
        int[] temp = new int[max];
        for (int i = 0; i < size; i++)
            temp[i] = id[i];
        id = temp;
        int[] temp2 = new int[max];
        for (int i = 0; i < size; i++)
            temp2[i] = sz[i];
        sz = temp2;
    }

    // access to component id (site indexed)
    // number of components
    public Excercise20() { // Initialize component id array.
        count = 0;
        accesses = 0;
        id = new int[1];
        sz = new int[1];
        size = 0;
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
        Excercise20 resizable = new Excercise20();

        for (int i = 0; i < 10; i++) {
            resizable.newSite(i);
        }
        int N = resizable.count();
        int cnt = 0;

        while (resizable.count() != 1) {
            SecureRandom r = new SecureRandom();
            int no1 = r.nextInt(N);
            int no2 = r.nextInt(N);
            System.out.println(no1 + " " + no2);
            cnt++;
            if (!resizable.connected(no1, no2)) {
                resizable.union(no1, no2);
            }
        }

        System.out.println(cnt);

    }
}