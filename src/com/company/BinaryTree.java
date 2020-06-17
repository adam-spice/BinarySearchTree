package com.company;

public class BinaryTree {
    // method to search for an element in a tree
    public static Node search(int x, Node n) {
        if (n == null || n.getData() == x) //if data of root is x then the element is found
        {
            return n;
        } else if (n.getData() > x) // x is greater, so we will search the right subtree
        {
            return search(x, n.getLeftChild());
        } else //x is smaller than the data, so we will search the left subtree
        {
            return search(x, n.getRightChild());
        }
    }

    //method to find the minimum value in a tree
    public static Node findMinimum(Node root) {
        if (root == null) {
            return null;
        } else if (root.getLeftChild() != null) // node with minimum value will have no left child
        {
            return findMinimum(root.getLeftChild()); // left most element will be minimum
        }
        return root;
    }

    // method to insert a new node
    public static Node insert(Node root, int x) {
        if (root == null) {
            return new Node(x);
        } else if (x > root.getData()) // x is greater. Should be inserted to right
        {
            root.setRightChild(insert(root.getRightChild(), x));
        } else // x is smaller should be inserted to left
        {
            root.setLeftChild(insert(root.getLeftChild(), x));
        }
        return root;
    }

    // method to delete a node
    public static Node delete(Node root, int x) {
        //searching for the item to be deleted
        if (root == null) {
            return null;
        }
        if (x > root.getData()) {
            root.setRightChild(delete(root.getRightChild(), x));
        } else if (x < root.getData()) {
            root.setLeftChild(delete(root.getLeftChild(), x));
        } else {
            //No Children
            if (root.getLeftChild() == null && root.getRightChild() == null) {
                root = null;
                return null;
            } //One Child
            else if (root.getLeftChild() == null || root.getRightChild() == null) {
                Node temp;
                if (root.getLeftChild() == null) {
                    temp = root.getRightChild();
                } else {
                    temp = root.getLeftChild();
                }
                root = null;
                return temp;
            } //Two Child
            else {
                Node temp = findMinimum(root.getRightChild());
                root.setData(temp.getData());
                root.setRightChild(delete(root.getRightChild(), temp.getData()));
            }
        }
        return root;
    }

    //method for inorder
    public static void inorder(Node root) {
        if (root != null) { // checking if the root is not null
            inorder(root.getLeftChild()); // visiting left child
            System.out.print(" " + root.getData() + " "); // printing data at root
            inorder(root.getRightChild()); // visiting right child
        }
    }

    /*
                   10
                 /    \
                /      \
               5       15
             /   \       \
            /     \       \
           1       6       18
                    \     /
                     \   17
                      8
                        \
                         \
                          12
     */
    public static void main(String[] args) {
        Node root;
        root = new Node(10);
        int[] nodes = new int[]{5, 15, 6, 1, 8, 12, 18, 17};
        int i;
        for (i = 0; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        inorder(root);
        System.out.println("");

        root = delete(root, 1);
        /*
                   10
                 /    \
                /      \
               5       15
                 \       \
                  \       \
                   6       18
                    \     /
                     \   17
                      8
                        \
                         \
                          12
         */

        root = delete(root, 18);
        /*
                   10
                 /    \
                /      \
               5       15
                 \       \
                  \       \
                   6       17
                    \
                     \
                      8
                        \
                         \
                          12
         */

        root = delete(root, 15);
        /*
                   10
                 /    \
                /      \
               5       15
                 \
                  \
                   6
                    \
                     \
                      8
                        \
                         \
                          12
         */

        inorder(root);

        System.out.println("");
    }
}
