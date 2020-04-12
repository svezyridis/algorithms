package searching.section1;

import java.util.ArrayList;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

    private Key[] keys;
    private Value[] values;
    int N;
    int accesses;

    BinarySearchST() {
        keys = (Key[]) new Comparable[1];
        values = (Value[]) new Object[1];
    }

    @Override
    public int put(Key key, Value val) {
        accesses = 0;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return accesses + 1;
        }
        if (N == keys.length) resize(2 * N);
        for (int j = N; j > i; j--) {
            values[j] = values[j - 1];
            keys[j] = keys[j - 1];
            accesses++;
        }
        keys[i] = key;
        values[i] = val;
        N++;
        accesses++;
        return accesses;
    }

    public void resize(int newSize) {
        Key[] temp1 = (Key[]) new Comparable[newSize];
        Value[] temp2 = (Value[]) new Object[newSize];
        for (int i = 0; i < keys.length; i++) {
            temp1[i] = keys[i];
        }
        for (int i = 0; i < values.length; i++) {
            temp2[i] = values[i];
        }
        keys = temp1;
        values = temp2;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].equals(key))
            return values[i];
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[N - 1];
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;
        return keys[i];
    }

    @Override
    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
            accesses++;
            if (cmp > 0)
                hi = mid - 1;
            else if (cmp < 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    @Override
    public Key select(int k) {
        if (k < N)
            return keys[k];
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            list.add(keys[i]);
        }
        return list;
    }
}
