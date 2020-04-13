package searching.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new ST<String, Integer>() {
            @Override
            public int put(String s, Integer val) {
                return -1;
            }

            @Override
            public Integer get(String s) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterable<String> keys() {
                return null;
            }
        };
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
