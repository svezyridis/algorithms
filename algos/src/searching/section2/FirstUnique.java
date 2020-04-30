package searching.section2;

import java.util.HashMap;

class FirstUnique {
    HashMap<Integer, Node> map;
    Node head;
    Node last;

    class Node {
        int val;
        Node prev;
        Node next;
    }

    public FirstUnique(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                Node node = new Node();
                node.val = nums[i];
                if (head == null) {
                    head = node;
                    last = head;
                } else {
                    node.prev = last;
                    last.next = node;
                    last = node;
                }
                map.put(nums[i], node);
            } else {
                Node node = map.get(nums[i]);
                if (node == null) continue;
                if (node != head) node.prev.next = node.next;
                else head = head.next;
                if (node != last) node.next.prev = node.prev;
                else last = last.prev;
                map.put(nums[i], null);
            }
        }
    }

    public int showFirstUnique() {
        if (head == null) return -1;
        return head.val;
    }

    public void add(int value) {
        if (!map.containsKey(value)) {
            Node node = new Node();
            node.val = value;
            if (head == null) {
                head = node;
                last = head;
            } else {
                node.prev = last;
                last.next = node;
                last = node;
            }
            map.put(value, node);
        } else {
            Node node = map.get(value);
            if (node == null) return;
            if (node != head) node.prev.next = node.next;
            else head = head.next;
            if (node != last) node.next.prev = node.prev;
            else last = last.prev;
            map.put(value, null);
        }
    }

    public static void main(String[] args) {
        FirstUnique obj = new FirstUnique(new int[]{7, 7, 7, 7, 7, 7});
        System.out.println(obj.showFirstUnique());
        obj.add(7);
        obj.add(3);
        obj.add(3);
        obj.add(7);
        obj.add(17);
        System.out.println(obj.showFirstUnique());
    }
}


/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */