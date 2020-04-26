package searching.section2;

import onepointthree.Queue;
import searching.section1.OrderedST;

public class BST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.val = value;
            count = 1;
        }
    }

    public int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }

    Node root;
    int accesses;

    @Override
    public int put(Key key, Value val) {
        accesses = 0;
        root = put(root, key, val);
        return accesses;
    }

    public Node put(Node root, Key key, Value val) {
        accesses++;
        if (root == null) return new Node(key, val);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.val = val;
        } else if (cmp > 0) root.right = put(root.right, key, val);
        else root.left = put(root.left, key, val);
        root.count = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root.val;
        if (cmp > 0) return get(root.right, key);
        else return get(root.left, key);
    }

    @Override
    public int size() {
        if (root == null) return 0;
        return root.count;
    }

    @Override
    public Key min() {
        return min(root);
    }

    private Key min(Node root) {
        if (root == null) return null;
        if (root.left == null) return root.key;
        return min(root.left);
    }


    @Override
    public Key max() {
        return max(root);
    }

    private Key max(Node root) {
        if (root == null) return null;
        if (root.right == null) return root.key;
        return max(root.right);
    }

    @Override
    public Key floor(Key key) {
        return floor(root, key);
    }

    private Key floor(Node root, Key key) {
        if (root == null) return null;
        int cmp = root.key.compareTo(key);
        if (cmp == 0) return key;
        if (cmp > 0) return floor(root.left, key);
        else {
            if (root.right != null && root.right.key.compareTo(key) <= 0) return floor(root.right, key);
            return root.key;
        }
    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(root, key);
    }

    private Key ceiling(Node root, Key key) {
        if (root == null) return null;
        int cmp = root.key.compareTo(key);
        if (cmp == 0) return key;
        if (cmp < 0) return ceiling(root.right, key);
        else {
            if (root.left != null && root.left.key.compareTo(key) >= 0) return ceiling(root.left, key);
            return root.key;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    public int rank(Node root, Key key) {
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return size(root.left);
        if (cmp > 0) return root.count + rank(root.right, key);
        return rank(root.left, key);
    }

    @Override
    public Key select(int k) {
        return select(root, k);
    }

    public Key select(Node root, int k) {
        if (root == null) return null;
        System.out.println(k);
        System.out.println(root.count);
        if (size(root.left) > k) return select(root.left, k);
        if (size(root.left) == k) return root.key;
        else return select(root.right, k - root.left.count - 1);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue();
        if (hi.compareTo(lo) < 0) return queue;
        addToQueue(queue, root, lo, hi);
        return queue;
    }

    public void addToQueue(Queue<Key> queue, Node root, Key lo, Key hi) {
        if (root == null) return;
        int cmp1 = lo.compareTo(root.key);
        int cmp2 = hi.compareTo(root.key);
        if (cmp1 == 0) queue.enqueue(root.key);
        if (cmp1 < 0) {
            addToQueue(queue, root.left, lo, hi);
            if (cmp2 >= 0)
                queue.enqueue(root.key);
            else return;
        }
        addToQueue(queue, root.right, lo, hi);
    }
}
