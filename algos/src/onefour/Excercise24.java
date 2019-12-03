package onefour;

import edu.princeton.cs.algs4.StdOut;

/**
 * Excercise21
 */
public class Excercise24 {

    public static int findFlogN(int[] stores) {
        int lo = 0;
        int hi = stores.length - 1;
        int median;
        int current = -1;
        int cnt = 0;
        while (lo <= hi) {
            cnt++;
            median = lo + (hi - lo) / 2;
            if (stores[median] == 0) {
                current = median;
                lo = median + 1;
            } else
                hi = median - 1;
        }
        System.out.println(cnt);
        return current + 1;
    }

    public static int findFlog2F(int[] floors) {
        int index = 0;
        int cnt = 0;
        while (index < floors.length - 1) {
            cnt++;
            if (floors[index] == 0) {
                if (index == 0)
                    index = 1;
                else
                    index *= 2;
            } else
                break;
        }
        int lo = index / 2;
        int hi = index < floors.length - 1 ? index : floors.length - 1;
        int median;
        while (lo <= hi) {
            cnt++;
            median = lo + (hi - lo) / 2;
            if (floors[median] == 0) {
                index = median;
                lo = median + 1;
            } else
                hi = median - 1;
        }

        System.out.println(cnt);
        return index + 1;
    }

    public static void main(String[] args) {
        int[] floors = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 };
        int floor = findFlogN(floors);
        StdOut.println("Floor: " + floor + " Expected: 6");
        int[] lotOfFloors = new int[1000000];
        for (int i = 0; i < lotOfFloors.length; i++) {
            lotOfFloors[i] = i < 4 ? 0 : 1;
        }
        int floor2 = findFlogN(lotOfFloors);
        StdOut.println("Floor: " + floor2 + " Expected: 4");

        floor2 = findFlog2F(lotOfFloors);
        StdOut.println("Floor: " + floor2 + " Expected: 4");
    }
}