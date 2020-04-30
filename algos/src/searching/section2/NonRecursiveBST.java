package searching.section2;

import onepointthree.Queue;
import onepointthree.Stack;
import org.w3c.dom.ls.LSOutput;
import searching.section1.OrderedST;

public class NonRecursiveBST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.val = value;
            this.count = 1;
        }
    }

    public int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }

    Node root;


    @Override
    public int put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
            return 0;
        }
        int compares = 0;
        Node current = root;
        Stack<Node> parents = new Stack<>();
        while (current != null) {
            compares++;
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                current.val = val;
                break;
            }
            parents.push(current);
            if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, val);
                    for (Node parent : parents)
                        parent.count++;
                    break;
                } else
                    current = current.right;
            } else {
                if (current.left == null) {
                    current.left = new Node(key, val);
                    for (Node parent : parents)
                        parent.count++;
                    break;
                } else
                    current = current.left;
            }
        }
        return compares;

    }

    @Override
    public Value get(Key key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.val;
            else if (cmp > 0) current = current.right;
            else current = current.left;
        }
        return null;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Key min() {
        if (root == null) return null;
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.key;
    }

    @Override
    public Key max() {
        if (root == null) return null;
        Node current = root;
        while (current.right != null)
            current = current.right;
        return current.key;
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
        if (root == null) return 0;
        Node current = root;
        int res = 0;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return res + size(current.left);
            else if (cmp < 0) current = current.left;
            else {
                res += size(current.left) + 1;
                current = current.right;
            }
        }
        return res;
    }


    @Override
    public Key select(int k) {
        if (root == null) return null;
        Node current = root;
        while (current != null) {
            if (size(current.left) == k) return current.key;
            if (size(current.left) > k)
                current = current.left;
            else {
                k = k - 1 - size(current.left);
                current = current.right;
            }
        }
        return null;
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

