package searching.section1;

import app.VisualAccumulator;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        ST<String, Integer> st = new SequentialSearchST<>();
        Scanner scanner = new Scanner(System.in);
        VisualAccumulator accumulator = new VisualAccumulator(14350, 5737);
        while (scanner.hasNext()) { // Build symbol table and count frequencies.
            String word = scanner.next();
            if (word.length() < minlen) continue; // Ignore short keys.
            if (!st.contains(word)) {
                int temp = st.put(word, 1);
                accumulator.addDataValue(temp);
            } else {
                int temp = st.put(word, st.get(word) + 1);
                accumulator.addDataValue(temp);
            }
            System.out.println(st.size());
        }
        String max = "";
        st.put(max, 0);

        for (String wrd : st.keys())
            if (st.get(wrd) > st.get(max))
                max = wrd;
        StdOut.println(max + " " + st.get(max));
    }
}
