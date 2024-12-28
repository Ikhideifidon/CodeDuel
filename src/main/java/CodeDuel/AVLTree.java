package CodeDuel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

/*
    This AVL is based on empty tree has -1 height, singleton tree has 0 height.
 */
@SuppressWarnings("unused")
public class AVLTree<T> {

    // ..... Light-weight Node class
    static class Node<T> {
        private static final int EMPTY_HEIGHT = -1;
        private final T data;
        private int height;
        private Node<T> left = null;
        private Node<T> right = null;

        private Node() {
            this.data = null;
            this.height = EMPTY_HEIGHT;
        }

        private Node(T data) {
            this.data = data;
            this.height = 0;
        }
    }

    private Node<T> root;
    private final Comparator<T> comparator;
    private int size;

    public AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public AVLTree(Comparator<T> comparator, T data) {
        this.comparator = comparator;
        this.root = new Node<>(data);
    }

    public void insert(T data) {
        size++;
        root = insert(root, data);
    }

    public boolean search(T data) {
        return search(root, data);
    }

    public int size() { return this.size; }

    @Override
    public String toString() {
        if (root != null) {
            StringBuilder dot = new StringBuilder();
            dot.append("AVL BALANCED SEARCH TREE").append("\n").append("{\n");
            generateDOT(root, dot);
            dot.append(" }\n");
            return dot.toString();
        }
        return "";
    }

    // Utility Functions
    private Node<T> insert(Node<T> node, T key) {
        if (node == null)
            return new Node<>(key);

        else {
            if (comparator.compare(key, node.data) <= 0)
                node.left = insert(node.left, key);
            else
                node.right = insert(node.right, key);
        }

        // Update height of the ancestor
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get balance Factor
        int balance = getBalance(node);

        // Left Subtree of the Left child
        if (balance > 1 && comparator.compare(key, node.left.data) < 0)
            return rightRotation(node);

        // Right Subtree of the Right Child
        if (balance < -1 && comparator.compare(key, node.right.data) > 0)
            return leftRotation(node);

        // Left Subtree of the Right Child
        if (balance < -1 && comparator.compare(key, node.right.data) < 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        // Right Subtree of the Left Child
        if (balance > 1 && comparator.compare(key, node.left.data) > 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        return node;
    }

    // Left Rotation
    private Node<T> leftRotation(Node<T> node) {
        Node<T> temp = node.right;
        Node<T> x = temp.left;
        temp.left = node;
        node.right = x;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> temp = node.left;
        Node<T> x = temp.right;
        temp.right = node;
        node.left = x;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        temp.height = 1 + Math.max(height(temp.left), height(temp.right));

        return temp;
    }

    private boolean search(Node<T> node, T key) {
        if (node == null)
            return false;

        if (comparator.compare(key, node.data) == 0)
            return true;

        if (comparator.compare(key, node.data) < 1)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    private int height(Node<T> node) {
        return node == null ? Node.EMPTY_HEIGHT : node.height;
    }

    private int getBalance(Node<T> node) {
        return node == null ? Node.EMPTY_HEIGHT : height(node.left) - height(node.right);
    }

    public void printTree(String filePath) {
        if (root != null) {
            StringBuilder dot = new StringBuilder();
            dot.append("DIGRAPH AVL {\n");
            dot.append("  Node [shape=circle, style=filled, color=lightblue, fontname=Arial];\n");
            generateDOT(root, dot);
            dot.append("}\n");
            writeToFile(filePath, dot.toString());
        }
    }

    private void generateDOT(Node<T> node, StringBuilder dot) {
        /*
            {
                45;
                45 -> 30;
                30;
                30 -> 25;
                25;
                25 -> 15;
             }
         */
        if (node != null) {
            dot.append("\t").append(node.data).append(";\n");
            if (node.left != null) {
                dot.append("\t").append(node.data).append(" ->").append(" ").append(node.left.data).append(";\n");
                generateDOT(node.left, dot);
            } if (node.right != null) {
                dot.append("\t").append(node.data).append(" ->").append(" ").append(node.right.data).append(";\n");
                generateDOT(node.right, dot);
            }
        }
    }

    private void writeToFile(String filePath, String dot) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(dot);
            System.out.println("DOT file created at: " + filePath);
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
