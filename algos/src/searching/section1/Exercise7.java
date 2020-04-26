package searching.section1;

import java.security.SecureRandom;


public class Exercise7 {
    public static void main(String[] args) {
        OrderedST<Integer, Integer> st = new BinarySearchST<>();
        SecureRandom sr = new SecureRandom();
        for (int N = 10; N <= 1000; N *= 10) {
            int[] ints = new int[N];
            for (int i = 0; i < N; i++) {
                ints[i] = sr.nextInt(100);
            }
            for (int i = 0; i < N; i++) {
                if (!st.contains(ints[i]))
                    st.put(ints[i], 1);
                else
                    st.put(ints[i], st.get(ints[i]) + 1);
            }
            System.out.println("For N=" + N + " " + st.size() + " distinct Integers were found");

        }
        for (int key :
                st.keys()) {
            System.out.println(key + " " + st.get(key));
        }

    }
}
