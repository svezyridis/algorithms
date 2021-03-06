package searching.section1;

import onefour.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

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

    public BinarySearchST() {
        items = new BinarySearchST.Item[1];
    }

    BinarySearchST(Item[] initialItems) {
        Arrays.sort(initialItems, new ItemComparator());
        items = initialItems;
        N += items.length;
    }

    public class ItemComparator implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            return o1.key.compareTo(o2.key);
        }
    }

    @Override
    public int put(Key key, Value val) {
        accesses = 0;
        if (N > 0 && key.compareTo(items[N - 1].key) >= 0) {
            if (N == items.length) resize(2 * N);
            items[N] = new Item(key, val);
            N++;
            return 1;
        }
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
        Item[] temp = new BinarySearchST.Item[newSize];
        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && items[i].key.equals(key))
            return items[i].value;
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        return items[0].key;
    }

    @Override
    public Key max() {
        return items[N - 1].key;
    }

    @Override
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

    @Override
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;
        return items[i].key;
    }

    @Override
    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            int cmp = items[mid].key.compareTo(key);
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
            return items[k].key;
        return null;
    }

    @Override
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

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<>();
        for (int i = rank(lo); i <= rank(hi); i++) {
            list.add(items[i].key);
        }
        return list;
    }
}
