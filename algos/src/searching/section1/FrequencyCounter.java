package searching.section1;

import app.VisualAccumulator;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        OrderedST<String, Integer> st = new OrderedSequentialSearchST<>();
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        VisualAccumulator accumulator = new VisualAccumulator(17511, 8928);
        while (scanner.hasNext()) { // Build symbol table and count frequencies.
            String word = scanner.next();
            if (word.length() < minlen) continue; // Ignore short keys.
            if (!st.contains(word)) {
                count++;
                int temp = st.put(word, 1);
                accumulator.addDataValue(temp);
            } else {
                int temp = st.put(word, st.get(word) + 1);
                accumulator.addDataValue(temp);
            }
        }
        String max = "";
        st.put(max, 0);
        for (String wrd : st.keys()) {
            if (st.get(wrd) > st.get(max))
                max = wrd;
        }
        System.out.println(st.size());
        System.out.println(count);

        StdOut.println(max + " " + st.get(max));
    }
}
