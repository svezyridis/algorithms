package twofour;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ(Key[] array) {
        int N = array.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(array, k, N);
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
        if (N == pq.length - 1)
            resize(pq, 2 * N + 1);
        pq[++N] = v;
        swim(N);
    }

    public void resize(Key[] pq, int newSize) {
        Key[] temp = (Key[]) new Comparable[newSize];
        for (int i = 0; i < pq.length; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public Key delMax() {
        Key max = pq[1];
        exch(pq, 1, N--);
        pq[N + 1] = null;
        sink(pq, 1, N);
        if (N <= 0.25 * pq.length)
            resize(pq, pq.length / 2);
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
        while (k > 1 && less(pq, k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    private void sink(Key[] array, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1))
                j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

}
