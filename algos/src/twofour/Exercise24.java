package twofour;

import java.util.ArrayList;

public class Exercise24<Item extends Comparable<Item>> {

    Node root;
    Node nextParent;
    ArrayList<Node> lowestParents;
    int N = 0;

    private class Node {
        Node parent;
        Node leftChild;
        Node rightChild;
        Item item;

        public Node(Item item) {
            this.item = item;
        }
    }


    public void insert(Item item) {
        Node newNode = new Node(item);
        if (N == 0) {
            root = newNode;
        } else {
            if (nextParent.leftChild == null) {
                nextParent.leftChild = newNode;
            } else
                nextParent.rightChild = newNode;
        }
        N++;
    }

    public boolean assignToNextAvailableLeaf(Node root, Node item) {
        if (root.leftChild == null) {
            root.leftChild = item;
            return true;
        } else if (root.rightChild == null) {
            root.rightChild = item;
            return true;
        } else if (!assignToNextAvailableLeaf(root.leftChild, item))
            assignToNextAvailableLeaf(root.rightChild, item);
        return false;
    }


}
