package searching.section2;

import onepointthree.Queue;
import searching.section1.OrderedST;

public class Exercise12<Key extends Comparable<Key>, Value> {
    int accesses;
    int N;
    Node root;

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

    public int put(Key key, Value val) {
        accesses = 0;
        root = put(root, key, val);
        return accesses;
    }

    public Node put(Node root, Key key, Value val) {
        accesses++;
        if (root == null) {
            N++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.val = val;
        } else if (cmp > 0) {
            root.right = put(root.right, key, val);
        } else {
            root.left = put(root.left, key, val);
        }
        return root;
    }


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


    public int size() {
        return N;
    }


    public Key min() {
        return min(root);
    }

    private Key min(Node root) {
        if (root == null) return null;
        if (root.left == null) return root.key;
        return min(root.left);
    }


    public Key max() {
        return max(root);
    }

    private Key max(Node root) {
        if (root == null) return null;
        if (root.right == null) return root.key;
        return max(root.right);
    }


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
