package twofive;

import edu.princeton.cs.algs4.In;
import twoone.Exercise29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Exercise8 {
    public static class Word implements Comparable<Word> {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Word o) {
            if (o.count > this.count) return -1;
            else if (o.count < this.count) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        Arrays.sort(a);
        ArrayList<Word> words = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < a.length; i++) {
            if (i < a.length - 1 && a[i].equals(a[i + 1])) {
                count++;
            } else {
                words.add(new Word(a[i], count));
                count = 1;
            }
        }
        Collections.sort(words, Collections.reverseOrder());
        for (Word word :
                words) {
            System.out.println(word.word + " appears " + word.count);
        }
    }
}
