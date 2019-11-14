package onepointthree;

import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;

/**
 * RandomBag
 */
public class RandomBag<Item> implements Iterable<Item> {

    private int size;
    Item[] arr;

    public int size() {
        return size;
    }

    public RandomBag() {
        arr = (Item[]) new Object[1];
    }

    public void add(Item item) {
        if (size == arr.length)
            resize(2 * size);
        arr[size] = item;
        size++;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    public class RandomIterator implements Iterator<Item> {
        int counter;
        Item[] array;

        public RandomIterator() {
            counter = 0;
            array = (Item[]) new Object[size()];
            for (int i = 0; i < size; i++)
                array[i] = arr[i];
            List<Item> list = Arrays.asList(array);
            Collections.shuffle(list);
            list.toArray(array);
        }

        @Override
        public Item next() {
            Item res = array[counter];
            counter++;
            return res;
        }

        @Override
        public boolean hasNext() {
            return counter < size();
        }
    }

    public static void main(String[] args) {

        RandomBag<Integer> bag = new RandomBag<Integer>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);
        bag.add(6);
        bag.add(7);
        for (int x : bag)
            StdOut.print(" " + x);
        System.out.println();
        for (int x : bag)
            StdOut.print(" " + x);
    }
}