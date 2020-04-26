package searching.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
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
            OrderedST<String, Integer> st = new BST<>();
            OrderedST<String, Integer> st2 = new BinarySearchST();
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
            System.out.println("select 5129 : " + st.select(5129) + " " + st2.select(5129));
            System.out.println(st.select(6));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return;
        } catch (IOException e) {
            System.out.println("io exception");
            return;
        }
    }
}
