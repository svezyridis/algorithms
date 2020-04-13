package twofour;

import edu.princeton.cs.algs4.StdRandom;

public class IndexMaxPQ<Item extends Comparable<Item>> {
    int N;
    int pq[];
    int qp[];
    Item[] keys;

    public IndexMaxPQ(int maxN) {
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i < maxN + 1; i++)
            qp[i] = -1;
        keys = (Item[]) new Comparable[maxN + 1];
    }

    public IndexMaxPQ() {
        pq = new int[2];
        qp = new int[2];
        qp[0] = -1;
        qp[1] = -1;
        keys = (Item[]) new Comparable[2];
    }

    public void insert(int k, Item item) {
        if (N == pq.length - 1)
            resize(2 * N + 1);
        pq[++N] = k;
        keys[k] = item;
        qp[k] = N;
        swim(N);
    }

    public void change(int k, Item item) {
        if (keys[k].compareTo(item) > 0) {
            keys[k] = item;
            sink(qp[k]);
        } else {
            keys[k] = item;
            swim(qp[k]);
        }
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        int temp = qp[k];
        exch(temp, N--);
        sink(temp);
        qp[k] = -1;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        qp[pq[i]] = j;
        qp[pq[j]] = i;
        int temp2 = pq[i];
        pq[i] = pq[j];
        pq[j] = temp2;
    }

    Item max() {
        return keys[pq[1]];
    }

    int maxIndex() {
        return pq[1];
    }

    public void resize(int newSize) {
        Item[] temp = (Item[]) new Comparable[newSize];
        for (int i = 0; i <= N; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
        int[] temp2 = new int[newSize];
        for (int i = 0; i <= N; i++) {
            temp2[i] = pq[i];
        }
        pq = temp2;
        temp2 = new int[newSize];
        for (int i = 0; i <= N; i++) {
            temp2[i] = qp[i];
        }
        qp = temp2;
    }


    int delMax() {
        int result = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return result;
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    private void show() {
        for (int i = 1; i <= N; i++) {
            System.out.println("index: " + pq[i] + " value " + keys[pq[i]]);
        }
    }

    public static void main(String[] args) {
        IndexMaxPQ<Double> pq = new IndexMaxPQ<>();
        for (int i = 0; i < 5; i++) {
            double val = StdRandom.uniform();
            pq.insert(i + 1, val);
            System.out.println("index: " + (i + 1) + " value" + val);
        }
        System.out.println("priority queue");
        pq.show();
        System.out.println(pq.contains(2));
        pq.change(2, 15.0);
        pq.show();
        System.out.println();
        pq.delete(2);
        pq.show();
        System.out.println(pq.contains(2));
        System.out.println();


        while (!pq.isEmpty()) {
            System.out.print("value " + pq.max() + " index:" + pq.delMax());
            System.out.println();
        }
    }

}
