package CodeDuel;

import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("unused")
public class BinarySearchTree {
    // Light-weight class
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode() {}
        private TreeNode(int val) { this.val = val; }
        private TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode root;

    public BinarySearchTree() { this.root = null; }
    public BinarySearchTree(int val) { this.root = new TreeNode(val); }

    public void insert(int val) {
        if (root == null)
            root = new TreeNode(val);
        else
            insertDFS(root, val);
    }

    private void insertDFS(TreeNode node, int val) {
        if (val <= node.val) {
            if (node.left == null)
                node.left = new TreeNode(val);
            else
                insertDFS(node.left, val);
        } else {
            if (node.right == null)
                node.right = new TreeNode(val);
            else
                insertDFS(node.right, val);
        }
    }

    public boolean search(int val) {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        return searchDFS(root, val);
    }

    private boolean searchDFS(TreeNode node, int val) {
        if (node == null)
            return false;
        if (node.val == val)
            return true;
        if (val <= node.val)
            return searchDFS(node.left, val);
        return searchDFS(node.right, val);
    }

    public int findMin() {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        TreeNode current = root;
        while (current.left != null)
            current = current.left;
        return current.val;
    }

    public int findMax() {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        TreeNode current = root;
        while (current.right != null)
            current = current.right;
        return current.val;
    }

    public int successor(int val) {
        /*
        STEPS:
        1. Search for val.
            if found, find the smallest node whose value is greater than val.
            if not found, throw exception.
         */
        TreeNode node = searchNode(root, val);
        if (node == null)
            throw new IllegalArgumentException(String.format("%d is not found", val));
        // If node has a right subtree
        if (node.right != null)
            return findMin(node.right);

        // If node has no right subtree, traverse up
        TreeNode parent = root;
        TreeNode current = root;
        while (current != node) {
            parent = current;
            if (val <= current.val )
                current = current.left;
            else
                throw new IllegalArgumentException(String.format("%d has no successor", val));
        }
        if (parent != root)
            return parent.val;
        throw new IllegalArgumentException(String.format("%d has no successor", val));
    }

    private int findMin(TreeNode current) {
        if (current == null)
            throw new IllegalArgumentException("Tree is empty!");
        while (current.right != null)
            current = current.right;
        return current.val;
    }

    private TreeNode searchNode(TreeNode node, int val) {
        if (node == null)
            return null;

        if (node.val == val)
            return node;

        if (val <= node.val)
            return searchNode(node.left, val);
        return searchNode(node.right, val);
    }

    // Visualization class
    // Generate DOT format for the tree
    private static void generateDOT(TreeNode node, StringBuilder dot) {
        // Use Preorder Traversal
        /*
            45;
            45 -> 25;
            25;
            25 -> 15;
            15;
            15 -> 10;
         */
        if (node != null) {
            dot.append("\t").append(node.val).append(";\n");

            if (node.left != null) {
                dot.append("\t").append(node.val).append(" ").append("->").append(" ").append(node.left.val).append(";\n");
                generateDOT(node.left, dot);
            }

            if (node.right != null) {
                dot.append("\t").append(node.val).append(" ").append("->").append(" ").append(node.right.val).append(";\n");
                generateDOT(node.right, dot);
            }
        }
    }

    // Create DOT file from the tree
    public void printBST(String filePath) {
        StringBuilder dot = new StringBuilder();
        dot.append("DIGRAPH BST {\n");
        dot.append("  Node [shape=circle, style=filled, color=lightblue, fontname=Arial];\n");
        generateDOT(root, dot);
        dot.append("}\n");

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(dot.toString());
            System.out.println("DOT file created at: " + filePath);
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}

