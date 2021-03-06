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
        int height;
        int internalPath;

        public Node(Key key, Value value) {
            this.key = key;
            this.val = value;
            this.count = 1;
            this.height = 0;
            this.internalPath = 0;
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
        } else if (cmp > 0) {
            root.right = put(root.right, key, val);
        } else {
            root.left = put(root.left, key, val);
        }
        root.count = size(root.left) + size(root.right) + 1;
        root.height = 1 + Math.max(height(root.right), height(root.left));
        root.internalPath = size(root.left) + size(root.right) + internalPath(root.left) + internalPath(root.right);
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
        return size(root);
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
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return key;
        else if (cmp < 0) {
            return floor(root.left, key);
        } else {
            Key bigger = floor(root.right, key);
            if (bigger == null) return root.key;
            return bigger;
        }
    }

    @Override
    public Key ceiling(Key key) {
        return ceiling(root, key);
    }

    private Key ceiling(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return key;
        else if (cmp > 0) {
            return ceiling(root.right, key);
        } else {
            Key smaller = ceiling(root.left, key);
            if (smaller == null) return root.key;
            return smaller;
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
        if (cmp > 0) return size(root.left) + 1 + rank(root.right, key);
        return rank(root.left, key);
    }

    @Override
    public Key select(int k) {
        return select(root, k);
    }

    public Key select(Node root, int k) {
        if (root == null) return null;
        if (size(root.left) > k) return select(root.left, k);
        if (size(root.left) == k) return root.key;
        else return select(root.right, k - size(root.left) - 1);
    }

    public int recursiveHeight() {
        return recursiveHeight(root);
    }

    public int height() {
        return height(root);
    }


    public int recursiveAvgCompare() {
        return recursiveInternalPath(root) / size(root) + 1;
    }

    public int internalPath(Node root) {
        if (root == null) return 0;
        return root.internalPath;
    }

    public int avgCompare() {
        if (root == null) return 0;
        return root.internalPath / root.count + 1;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node root) {
        if (root == null) return null;
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.count = size(root.right) + size(root.left) + 1;
        return root;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node root) {
        if (root == null) return null;
        if (root.right == null) return root.left;
        root.right = deleteMax(root.right);
        root.count = size(root.right) + size(root.left) + 1;
        return root;
    }


    private int recursiveInternalPath(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        if (root.right == null) return size(root.left) + internalPath(root.left);
        if (root.left == null) return size(root.right) + internalPath(root.right);
        return size(root.right) + size(root.left) + internalPath(root.left) + internalPath(root.right);

    }


    private int height(Node root) {
        if (root == null) return 0;
        return root.height;
    }

    private int recursiveHeight(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        return 1 + Math.max(recursiveHeight(root.left), recursiveHeight(root.right));
    }

    public Key random() {
        int dice = (int) Math.floor(Math.random() * size());
        return select(dice);
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

