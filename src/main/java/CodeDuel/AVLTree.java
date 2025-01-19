package CodeDuel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
    This AVL is based on: Empty tree has -1 height and a singleton tree has 0 height.
 */
@SuppressWarnings("unused")
public class AVLTree<T> {

    // ..... Light-weight Node class
    static class Node<T> {
        private static final int EMPTY_HEIGHT = -1;
        private T data;
        private int height;
        private Node<T> left = null;
        private Node<T> right = null;

        private Node() {
            this.data = null;
            this.height = Node.EMPTY_HEIGHT;
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

    public AVLTree(Comparator<T> comparator, T key) {
        this.comparator = comparator;
        this.root = new Node<>(key);
    }

    public void insert(T key) {
        size++;
        root = insert(root, key);
    }

    public boolean delete(T key) {
        Node<T> deleted = delete(root, key);
        if (deleted != null) {
            size--;
            return true;
        }
        return false;
    }

    public final boolean search(T key) {
        return search(root, key);
    }

    public int size() { return this.size; }

    public int height() { return root.height; }

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

    public void printTree(String filePath) {
        if (root != null) {
            StringBuilder dot = new StringBuilder();
            dot.append("DIGRAPH AVL {\n");
            dot.append("  Node [shape=circle, style=filled, color=green, fontname=Arial];\n");
            generateDOT(root, dot);
            dot.append("}\n");
            writeToFile(filePath, dot.toString());
        }
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
        return rebalanced(node, key);
    }

    // Left Rotation
    private Node<T> leftRotation(Node<T> node) {
        Node<T> temp = node.right;
        Node<T> T2 = temp.left;
        temp.left = node;
        node.right = T2;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    // Right Rotation
    private Node<T> rightRotation(Node<T> node) {
        Node<T> temp = node.left;
        Node<T> T2 = temp.right;
        temp.right = node;
        node.left = T2;

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

    private void generateDOT(Node<T> node, StringBuilder dot) {

        /*
            Preorder Traversal
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

    private Node<T> delete(Node<T> node, T key) {
        if (node == null)
            return null;

        if (comparator.compare(key, node.data) < 0)
            node.left = delete(node.left, key);

        else if (comparator.compare(key, node.data) > 0)
            node.right = delete(node.right, key);

        else {
            // Node found
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null)
                return null;

            // Case 2: One child
            if (node.left == null)
                return node.right;

            else if (node.right == null)
                return node.left;

            // Case 3: Two children
            Node<T> successor = findMin(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        // Update height of the ancestor
        return rebalanced(node, key);
    }

    private Node<T> rebalanced(Node<T> node, T key) {
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get balance Factor
        int balance = getBalance(node);

        // Left-heavy cases
        if (balance > 1) {
            if (getBalance(node.left) < 0) // Left-Left case
                node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        // Right-heavy cases
        if (balance < -1) {
            if (getBalance(node.right) > 0) // Right-Right case
                node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    // Helper method to find the minimum node in a subtree
    private Node<T> findMin(Node<T> node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public T kthSmallest(int k) {
        return kthSmallest(root, k, new int[1]);
    }

    private T kthSmallest(Node<T> node, int k, int[] count) {
        if (node == null)
            return null;

        T left = kthSmallest(node.left, k, count);
        if (left != null)
            return left;
        count[0]++;
        if (count[0] == k)
            return node.data;
        return kthSmallest(node.right, k, count);
    }

    public List<List<T>> zigzagLevelOrder() { return zigzagLevelOrder(root); }

    private List<List<T>> zigzagLevelOrder(Node<T> node) {
        if (node == null)
            return new ArrayList<>();

        List<List<T>> result = new ArrayList<>();
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(node);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<T> levelDeque = new ArrayDeque<>(size);

            for (int i = 0; i < size; i++) {
                Node<T> current = queue.poll();
                assert current != null;

               if (leftToRight)
                    levelDeque.offerLast(current.data);
                else
                    levelDeque.offerFirst(current.data);

                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            leftToRight = !leftToRight;
            result.add(new ArrayList<>(levelDeque));
        }
        return result;
    }
}
