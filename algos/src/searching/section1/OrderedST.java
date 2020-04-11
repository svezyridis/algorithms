package searching.section1;

public interface OrderedST<Key extends Comparable<Key>, Value> {
    void put(Key key, Value val);

    Value get(Key key);

    default void delete(Key key) {
        put(key, null);
    }

    default boolean contains(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int k);

    default void deleteMin() {
        delete(min());
    }

    default void deleteMax() {
        delete(max());
    }

    default int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    Iterable<Key> keys(Key lo, Key hi);

    default Iterable<Key> keys() {
        return keys(min(), max());
    }
}
