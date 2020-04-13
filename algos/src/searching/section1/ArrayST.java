package searching.section1;

import java.util.ArrayList;

public class ArrayST<Key, Value> implements ST<Key, Value> {
    Key[] keys;
    Value[] values;
    int N;


    ArrayST() {
        keys = (Key[]) new Object[1];
        values = (Value[]) new Object[1];
    }

    @Override
    public int put(Key key, Value val) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                values[i] = val;
                return i;
            }
        }
        if (N == keys.length) resize(2 * N);
        keys[N] = key;
        values[N] = val;
        N++;
        return N;
    }

    @Override
    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    private void resize(int newSize) {
        Key[] temp1 = (Key[]) new Object[newSize];
        Value[] temp2 = (Value[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            temp1[i] = keys[i];
            temp2[i] = values[i];
        }
        keys = temp1;
        values = temp2;
    }

    @Override
    public Iterable<Key> keys() {
        ArrayList<Key> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(keys[i]);
        }
        return list;
    }
}
