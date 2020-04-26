package searching.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import searching.section2.BST;

import java.io.*;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        String filename = args[1];
        try {
            FileInputStream fstream_school = new FileInputStream(filename);
            DataInputStream data_input = new DataInputStream(fstream_school);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;
            OrderedST<String, Integer> st = new BST<>();
            int count = 0;
            VisualAccumulator accumulator = new VisualAccumulator(14350, 5737);
            while ((str_line = buffer.readLine()) != null) {
                String[] words = str_line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                if ((words.length != 0)) {
                    for (String word : words) {
                        if (word.length() < minlen) continue;
                        if (!st.contains(word)) {
                            count++;
                            int temp = st.put(word, 1);
                            accumulator.addDataValue(temp);
                        } else {
                            int temp = st.put(word, st.get(word) + 1);
                            accumulator.addDataValue(temp);
                        }
                    }
                }
            }

            String max = "";
            st.put(max, 0);
            Queue<String> queue = new Queue<>();
            for (String wrd : st.keys()) {
                if (st.get(wrd) > st.get(max)) {
                    while (!queue.isEmpty())
                        queue.dequeue();
                    queue.enqueue(wrd);
                    max = wrd;
                } else if (st.get(wrd) == st.get(max)) {
                    queue.enqueue(wrd);
                }

            }
            while (!queue.isEmpty()) {
                String word = queue.dequeue();
                StdOut.println(word + " " + st.get(word));
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return;
        } catch (IOException e) {
            System.out.println("io exception");
            return;
        }
        /**
         * ST<String, Integer> st = new ArrayST<>();
         *         Scanner scanner = new Scanner(System.in);
         *         int count = 0;
         *         VisualAccumulator accumulator = new VisualAccumulator(17511, 8928);
         *         while (scanner.hasNext()) { // Build symbol table and count frequencies.
         *             String word = scanner.next();
         *             if (word.length() < minlen) continue; // Ignore short keys.
         if (!st.contains(word)) {
         count++;
         int temp = st.put(word, 1);
         accumulator.addDataValue(temp);
         } else {
         int temp = st.put(word, st.get(word) + 1);
         accumulator.addDataValue(temp);
         }
         }
         */


    }
}
