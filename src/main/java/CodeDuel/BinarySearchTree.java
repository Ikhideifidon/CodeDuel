package CodeDuel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class BinarySearchTree<T extends Comparable<T>> {
    // Light-weight class
    static class TreeNode<T> {
        private T val;
        private TreeNode<T> left;
        private TreeNode<T> right;

        private TreeNode() {}
        private TreeNode(T val) { this.val = val; }
        private TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode<T> root;
    private List<T> result;

    public BinarySearchTree() { this.root = null; }
    public BinarySearchTree(T val) { this.root = new TreeNode<>(val); }
    public BinarySearchTree(T[] array) {
        Arrays.sort(array);
        createBalancedBSTHelper(array, 0, array.length - 1);
    }

    public void insert(T val) {
        if (root == null || val == null)
            root = new TreeNode<>(val);
        else
            insertDFS(root, val);
    }

    public boolean search(T val) {
        if (root == null || val == null)
            throw new IllegalArgumentException("Tree is empty!");
        return searchDFS(root, val);
    }

    public boolean delete(T val) {
        if (root == null || val == null)
           return false;

        TreeNode<T> current = root;
        TreeNode<T> parent = null;

        // Step 1: Find the node to delete and its parent
        while (current != null && val.compareTo(current.val) != 0) {
            parent = current;
            if (val.compareTo(current.val) < 0)
                current = current.left;
            else
                current = current.right;
        }

        if (current == null)
            return false;

        // Step 2: Handle the 3 cases for deletion

        // Case 1: Node to delete has no children (leaf node)
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (parent.left == current)
                parent.left = null;
            else
                parent.right = null;
        }
        // Case 2: Node to delete has one child
        else if (current.left == null || current.right == null) {
            TreeNode<T> child = (current.left != null) ? current.left : current.right;
            if (current == root)
                root = child;  // If deleting the root with one child
            else if (parent.left == current)
                parent.left = child;
            else
                parent.right = child;
        }
        // Case 3: Node to delete has two children
        else {
            // Find the in-order successor (smallest node in the right subtree)
            TreeNode<T> successorParent = current;
            TreeNode<T> successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Replace the value of `current` with the successor's value
            current.val = successor.val;

            // Delete the successor node
            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }
        return true;
    }

    public int height() { return height(root); }

    public T findMin() {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        TreeNode<T> current = root;
        while (current.left != null)
            current = current.left;
        return current.val;
    }

    public T findMax() {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        TreeNode<T> current = root;
        while (current.right != null)
            current = current.right;
        return current.val;
    }

    public T successor(T val) {
        TreeNode<T> target = searchNode(root, val);
        if (target == null)
            throw new IllegalArgumentException(String.format("Node with the value '%s' does not exist in the Tree!", val));

        // Check if target has a right subtree
        if (target.right != null)
            return findMin(target.right);

        // If no right subtree
        TreeNode<T> successor = null;
        TreeNode<T> current = root;
        while (current != null) {
            if (val.compareTo(current.val) < 0) {
                successor = current;
                current = current.left;
            }
            else if (val.compareTo(current.val) > 0)
                current = current.right;
            else
                break;
        }

        if (successor != null)
            return successor.val;
        throw new IllegalArgumentException(String.format("'%s' does not have a successor in the Tree!", val));
    }

    public T predecessor(T val) {
        TreeNode<T> target = searchNode(root, val);
        if (target == null)
            throw new IllegalArgumentException(String.format("Node with the value '%s' does not exist in the Tree!", val));

        // If left subtree exists
        if (target.left != null)
            return findMax(target.left).val;

        // If no left subtree, traverse up to find the predecessor
        TreeNode<T> predecessor = null;
        TreeNode<T> current = root;
        while (current != null) {
            if (val.compareTo(current.val) < 0)
                current = current.left;
            else if (val.compareTo(current.val) > 0) {
                predecessor = current;
                current = current.right;
            } else
                break;
        }
        if (predecessor != null)
            return predecessor.val;
        throw new IllegalArgumentException(String.format("'%s' does not have a predecessor in the Tree!", val));
    }

    // Traversals
    public List<T> preorder() {
        result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    public List<T> inorder() {
        result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    public List<T> postorder() {
        result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public List<T> preorderIterative() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> current = root;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(current);

        while (!stack.isEmpty()) {
            TreeNode<T> temp = stack.pop();
            result.add(temp.val);
            // If temp has a right subtree
            if (temp.right != null)
                stack.push(temp.right);
            // If temp has a left subtree
            if (temp.left != null)
                stack.push(temp.left);
        }
        return result;
    }

    public List<T> postorderIterative() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> current = root;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        T lastVisited = root.val;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (current == null) {
                TreeNode<T> temp = stack.getFirst();
                if (temp.right != null && temp.right.val.compareTo(lastVisited) != 0)
                    current = temp.right;
                else {
                    stack.pop();
                    result.add(temp.val);
                    lastVisited = temp.val;
                }
            }
        }
        return result;
    }

    public List<T> inorderIterative() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> current = root;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (current == null) {
                TreeNode<T> temp = stack.peek();
                assert temp != null;
                if (!visited.contains(temp.val)) {
                    result.add(temp.val);
                    visited.add(temp.val);

                    if (temp.right != null)
                        current = temp.right;
                    else
                        stack.pop();
                } else
                    stack.pop();
            }
        }
        return result;
    }

    public List<T> levelOrderTraversal() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> temp = queue.poll();
            result.add(temp.val);
            if (temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
        return result;
    }

    // Morris Traversal (constant Space Traversal)
    public List<T> morrisPreorder() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode<T> predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = current;
                    result.add(current.val);
                    current = current.left;
                } else {
                    predecessor.right = null;
                    current = current.right;
                }
            } else {
                result.add(current.val);
                current = current.right;
            }
        }
        return result;
    }

    public List<T> morrisInorder() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode<T> predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            } else {
                result.add(current.val);
                current = current.right;
            }
        }
        return result;
    }

    public List<T> morrisPostorder() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        TreeNode<T> dummyRoot = new TreeNode<>(null);
        dummyRoot.left = root;
        TreeNode<T> current = dummyRoot;

        while (current != null) {
            if (current.left == null)
                current = current.right;

            else {
                TreeNode<T> predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Reverse the nodes in the left subtree to traverse in reverse order
                    reverseAddPath(current.left, predecessor, result);
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }

    // Helper function to reverse and add the path to the result
    private void reverseAddPath(TreeNode<T> from, TreeNode<T> to, List<T> result) {
        reversePath(from, to);
        TreeNode<T> node = to;
        while (true) {
            result.add(node.val);
            if (node == from) break;
            node = node.right;
        }

        reversePath(to, from);
    }

    // Helper function to reverse the right pointers in the path
    private void reversePath(TreeNode<T> from, TreeNode<T> to) {
        if (from == to)
            return;

        TreeNode<T> x = from, y = from.right, z;
        while (x != to) {
            z = y.right;
            y.right = x;
            x = y;
            y = z;
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

    // ................................Utility Functions...........................

    // Insertion Helper
    private void insertDFS(TreeNode<T> node, T val) {
        if (val.compareTo(node.val) <= 0) {
            if (node.left == null)
                node.left = new TreeNode<>(val);
            else
                insertDFS(node.left, val);
        } else {
            if (node.right == null)
                node.right = new TreeNode<>(val);
            else
                insertDFS(node.right, val);
        }
    }

    // Search Helper
    private boolean searchDFS(TreeNode<T> node, T val) {
        if (node == null || val == null)
            return false;
        if (val.compareTo(node.val) == 0)
            return true;
        if (val.compareTo(node.val) <= 0)
            return searchDFS(node.left, val);
        return searchDFS(node.right, val);
    }

    // Create BST from Array Helper
    private void createBalancedBSTHelper(T[] array, int start, int end) {
        if (start > end)
            return;

        int mid = (start + end) / 2;
        insert(array[mid]);

        // Recursively insert left and right halves
        createBalancedBSTHelper(array, start, mid - 1);
        createBalancedBSTHelper(array, mid + 1, end);
    }

    // Visualization: Generate DOT format for the tree
    private static <T> void generateDOT(TreeNode<T> node, StringBuilder dot) {
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

    // Find Minimum element in BST Helper
    private T findMin(TreeNode<T> current) {
        if (current == null)
            throw new IllegalArgumentException("Tree is empty!");
        while (current.left != null)
            current = current.left;
        return current.val;
    }

    private TreeNode<T> findMax(TreeNode<T> current) {
        if (root == null)
            throw new IllegalArgumentException("Tree is empty!");
        while (current.right != null)
            current = current.right;
        return current;
    }

    private TreeNode<T> searchNode(TreeNode<T> node, T val) {
        if (node == null || val == null)
            return null;

        if (val.compareTo(node.val) == 0)
            return node;

        if (val.compareTo(node.val) <= 0)
            return searchNode(node.left, val);
        return searchNode(node.right, val);
    }

    private int height(TreeNode<T> node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // Traversals
    private void preorder(TreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.val);
            preorder(node.left, result);
            preorder(node.right, result);
        }
    }

    private void inorder(TreeNode<T> node, List<T> result) {
        if (node != null) {
            inorder(node.left, result);
            result.add(node.val);
            inorder(node.right, result);
        }
    }

    private void postorder(TreeNode<T> node, List<T> result) {
        if (node != null) {
            postorder(node.left, result);
            postorder(node.right, result);
            result.add(node.val);
        }
    }

}

