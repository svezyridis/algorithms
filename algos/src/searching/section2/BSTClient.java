package searching.section2;

import searching.section1.BinarySearchST;
import searching.section1.OrderedST;


import java.io.*;

public class BSTClient {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        String filename = args[1];
        try {
            FileInputStream fstream_school = new FileInputStream(filename);
            DataInputStream data_input = new DataInputStream(fstream_school);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;
            BST<String, Integer> st = new BST<>();
            OrderedST<String, Integer> st2 = new NonRecursiveBST<>();
            while ((str_line = buffer.readLine()) != null) {
                String[] words = str_line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                if ((words.length != 0)) {
                    for (String word : words) {
                        if (word.length() < minlen) continue;
                        if (!st.contains(word)) {
                            st.put(word, 1);
                        } else {
                            st.put(word, st.get(word) + 1);
                        }
                        if (!st2.contains(word)) {
                            st2.put(word, 1);
                        } else {
                            st2.put(word, st2.get(word) + 1);
                        }
                    }
                }
            }
            System.out.println("sizes: " + st.size() + " " + st2.size());
            System.out.println("max: " + st.max() + " " + st2.max());
            System.out.println("min: " + st.min() + " " + st2.min());
            System.out.println(783 + " " + st.select(783) + " " + st2.select(783) + " " + st.rank(st.select(783)) + " " + st2.rank(st2.select(783)));
            System.out.println("floor combiner: " + st.floor("combiner") + " " + st2.floor("combiner"));
            System.out.println("ceiling combinea: " + st.ceiling("combinea") + " " + st2.ceiling("combinea"));
            System.out.println("recursive height: " + st.recursiveHeight() + " height:" + st.height());
            System.out.println("recursive avgCompare: " + st.recursiveAvgCompare() + " avg compare:" + st.avgCompare());
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return;
        } catch (IOException e) {
            System.out.println("io exception");
            return;
        }
    }
}
