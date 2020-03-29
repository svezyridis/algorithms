package twofour;

import edu.princeton.cs.algs4.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;
    Key min;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ() {
        pq = (Key[]) new Comparable[2];
    }

    public MaxPQ(Key[] array) {
        pq = (Key[]) new Comparable[array.length + 1];
        int N = array.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(k, N);
        }
        pq = array;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (min == null || min.compareTo(v) > 0)
            min = v;
        if (N == pq.length - 1)
            resize(2 * N + 1);
        pq[++N] = v;
        swim(N);
    }

    public void resize(int newSize) {
        Key[] temp = (Key[]) new Comparable[newSize];
        for (int i = 0; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public Key delMax() {
        Key max = pq[1];
        if (max == min)
            min = null;
        exch(pq, 1, N--);
        pq[N + 1] = null;
        sink(1, N);
        if (N <= 0.25 * pq.length)
            resize(pq.length / 2);
        return max;
    }

    private boolean less(Key[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(Key[] pq, int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        Key temp = pq[k];
        while (k > 1 && pq[k / 2].compareTo(temp) < 0) {
            pq[k] = pq[k / 2];
            k = k / 2;
        }
        pq[k] = temp;
    }

    public Key min() {
        return min;
    }

    private void sink(int k, int N) {
        Key temp = pq[k];
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1))
                j++;
            if (temp.compareTo(pq[j]) >= 0) break;
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = temp;
    }

    public void show() {
        for (int i = 1; i <= N; i++)
            System.out.print(pq[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        MaxPQ<Double> que = new MaxPQ<Double>();

        for (int i = 0; i < 10; i++) {
            que.insert(StdRandom.uniform());
        }
        while (!que.isEmpty()) {
            System.out.println(que.delMax());
        }
    }

}
