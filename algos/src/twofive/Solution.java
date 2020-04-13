package twofive;


import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

    public static int xor(String a) {
        int[] result = new int[256];
        for (int i = 0; i < a.length(); i++) {
            result[a.charAt(i)]++;
        }
        for (int i = 0; i < 256; i++) {
            StdOut.print(result[i] + " ");
        }
        System.out.println();
        return Arrays.hashCode(result);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<Integer, List<String>> map = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            int xor = xor(strs[i]);

            if (map.containsKey(xor)) {
                map.get(xor).add(strs[i]);
            } else {
                List<String> temp = new ArrayList();
                temp.add(strs[i]);
                map.put(xor, temp);
            }
        }
        List<List<String>> result = new ArrayList<List<String>>(map.values());
        return result;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }
}

