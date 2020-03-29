package twofour;

import edu.princeton.cs.algs4.StdRandom;

import javax.swing.*;

public class Exercise29MinMax {

    private PQNode[] pqMin;
    private PQNode[] pqMax;
    int N = 0;

    enum Order {
        MIN, MAX;
    }

    public class PQNode implements Comparable<PQNode> {
        Comparable item;
        int minHeapIndex;
        int maxHeapIndex;

        @Override
        public int compareTo(PQNode other) {
            return item.compareTo(other.item);
        }

        public PQNode(Comparable item, int index) {
            this.item = item;
            minHeapIndex = index + 1;
            maxHeapIndex = index + 1;
        }
    }

    public Exercise29MinMax() {
        pqMin = new PQNode[2];
        pqMax = new PQNode[2];
    }

    public void insert(Comparable item) {
        PQNode node = new PQNode(item, N);
        if (N == pqMin.length - 1) {
            resize(2 * N + 1);
        }
        pqMax[++N] = node;
        pqMin[N] = node;
        swim(N, Order.MAX);
        swim(N, Order.MIN);
    }

    public void swim(int k, Order order) {
        if (order == Order.MAX)
            while (k > 1 && pqMax[k / 2].compareTo(pqMax[k]) < 0) {
                exch(pqMax, k / 2, k);
                pqMax[k / 2].maxHeapIndex = k / 2;
                pqMax[k].maxHeapIndex = k;
                k = k / 2;
            }
        else
            while (k > 1 && pqMin[k / 2].compareTo(pqMin[k]) > 0) {
                exch(pqMin, k / 2, k);
                pqMin[k / 2].minHeapIndex = k / 2;
                pqMin[k].minHeapIndex = k;
                k = k / 2;
            }
    }

    public void sink(int k, Order order) {
        if (order == Order.MAX)
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && pqMax[j + 1].compareTo(pqMax[j]) > 0)
                    j++;
                if (pqMax[k].compareTo(pqMax[j]) > 0) break;
                pqMax[k].maxHeapIndex = j;
                pqMax[j].maxHeapIndex = k;
                exch(pqMax, k, j);
                k = j;
            }
        else
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && pqMin[j + 1].compareTo(pqMin[j]) < 0)
                    j++;
                if (pqMin[k].compareTo(pqMin[j]) < 0) break;
                pqMin[k].minHeapIndex = j;
                pqMin[j].minHeapIndex = k;
                exch(pqMin, k, j);
                k = j;
            }

    }

    public Comparable delMax() {
        Comparable result = pqMax[1].item;
        int minHeapIndex = pqMax[1].minHeapIndex;
        pqMax[1] = pqMax[N];
        pqMin[minHeapIndex] = pqMin[N];
        N--;
        sink(1, Order.MAX);
        sink(minHeapIndex, Order.MIN);
        return result;
    }

    public Comparable delMin() {
        Comparable result = pqMin[1].item;
        int maxHeapIndex = pqMin[1].maxHeapIndex;
        pqMin[1] = pqMin[N];
        pqMax[maxHeapIndex] = pqMax[N];
        N--;
        sink(1, Order.MIN);
        sink(maxHeapIndex, Order.MAX);
        return result;
    }

    public void resize(int newSize) {
        PQNode[] temp1 = new PQNode[newSize];
        PQNode[] temp2 = new PQNode[newSize];
        for (int i = 0; i <= N; i++) {
            temp1[i] = pqMin[i];
            temp2[i] = pqMax[i];
        }
        pqMin = temp1;
        pqMax = temp2;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void exch(PQNode[] pq, int i, int j) {
        PQNode temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public void show(PQNode[] array) {
        for (int i = 1; i <= N; i++) {
            System.out.print(array[i].item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Exercise29MinMax pq = new Exercise29MinMax();
        for (int i = 0; i < 10; i++) {
            pq.insert(StdRandom.uniform());
        }
        pq.show(pq.pqMin);
        pq.show(pq.pqMax);


        while (!pq.isEmpty()) {
            if (StdRandom.uniform() < 0.33) {
                System.out.println("removing max");
                System.out.println(pq.delMax());
                pq.show(pq.pqMin);
                pq.show(pq.pqMax);
            } else if (StdRandom.uniform() < 0.66) {
                System.out.println("removing min");
                System.out.println(pq.delMin());
                pq.show(pq.pqMin);
                pq.show(pq.pqMax);
            } else {
                System.out.println("inserting item");
                pq.insert(StdRandom.uniform());
            }
        }
    }
}
