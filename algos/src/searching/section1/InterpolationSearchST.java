package searching.section1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class InterpolationSearchST<Key extends Integer, Value> {

    Item[] items;
    int N;
    int accesses;

    public class Item {
        Key key;
        Value value;

        public Item(Key key, Value val) {
            this.key = key;
            this.value = val;
        }
    }

    InterpolationSearchST() {
        items = new InterpolationSearchST.Item[1];
    }


    public int put(Key key, Value val) {
        accesses = 0;
        int i = rank(key);
        if (i < N && items[i].key.compareTo(key) == 0) {
            items[i].value = val;
            return accesses + 1;
        }
        if (N == items.length) resize(2 * N);
        for (int j = N; j > i; j--) {
            items[j] = items[j - 1];
            accesses++;
        }
        items[i] = new Item(key, val);
        N++;
        accesses++;
        return accesses;
    }

    public void resize(int newSize) {
        Item[] temp = new InterpolationSearchST.Item[newSize];
        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }


    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && items[i].key.equals(key))
            return items[i].value;
        return null;
    }

    private boolean isEmpty() {
        return N == 0;
    }


    public int size() {
        return N;
    }


    public Key min() {
        return items[0].key;
    }


    public Key max() {
        return items[N - 1].key;
    }


    public Key floor(Key key) {
        int i = rank(key);
        if (i == N)
            return items[N - 1].key;
        if (i == 0)
            return items[0].key;
        if (items[i].key.equals(key))
            return key;
        else
            return items[i - 1].key;

    }


    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;
        return items[i].key;
    }

    public Integer calculate(Integer lo, Integer key, Integer hi) {
        return ((key - lo) / (hi - key));
    }


    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        int mid = calculate(items[0].key, key, items[N - 1].key);
        while (hi >= lo) {
            int cmp = items[mid].key.compareTo(key);
            accesses++;
            if (cmp > 0)
                hi = mid - 1;
            else if (cmp < 0)
                lo = mid + 1;
            else
                return mid;
            mid = lo + (hi - lo) / 2;
        }
        return lo;
    }


    public Key select(int k) {
        if (k < N)
            return items[k].key;
        return null;
    }


    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if (i < N && items[i].key.equals(key)) {
            for (int j = i; j < N - 1; j++) {
                items[i] = items[i + 1];
            }
            items[N - 1] = null;
            N--;
        }
    }


    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<>();
        for (int i = rank(lo); i <= rank(hi); i++) {
            list.add(items[i].key);
        }
        return list;
    }
}
