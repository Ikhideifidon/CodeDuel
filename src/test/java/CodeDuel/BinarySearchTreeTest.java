package CodeDuel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {
    private static final String filePath = "src/main/resources/tree.dot";
    private final static int LOWER_LIMIT = 21;
    private final static int UPPER_LIMIT = 50;
    private static final Integer[] array = new Integer[(UPPER_LIMIT - LOWER_LIMIT) + 1];
    private static BinarySearchTree<String> bst;
    private static final Random random = new Random(5);

//    @BeforeAll
//    static void initializeTreeRandomly() {
//        bst  = new BinarySearchTree<>();
//        Set<Integer> unique = new HashSet<>();
//        for (int i = 0; i <= UPPER_LIMIT - LOWER_LIMIT; i++) {
//            int val = random.nextInt(UPPER_LIMIT - LOWER_LIMIT + 1) + LOWER_LIMIT;
//            if (!unique.contains(val)) {
//                unique.add(val);
//                array[i] = val;
//                bst.insert(val);
//            }
//        }
//        System.out.println("Setting up test environment...");
//    }

//    @BeforeAll
//    static void initializeTreeFromArray() {
//        int index = 0;
//        for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++)
//            array[index++] = i;
//        bst  = new BinarySearchTree<>(array);
//        System.out.println("Setting up test environment...");
//    }

    @BeforeAll
    static void initializeTreeFromArray() {
        String[] names = {
                "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack",
                "Kathy", "Leo", "Mona", "Nina", "Oscar", "Paul", "Quinn", "Rachel", "Sam", "Tina",
                "Ursula", "Victor", "Wendy", "Xander", "Yara", "Zane", "Anna", "Ben", "Catherine", "Daniel",
                "Ella", "Felix", "George", "Holly", "Isaac", "Jasmine", "Ken", "Lily", "Mason", "Lynda",
                "Olivia", "Peter", "Quincy", "Reed", "Sophia", "Tom", "Ulysses", "Vera", "Will", "Xena"
        };

        bst  = new BinarySearchTree<>(names);
        System.out.println("Setting up test environment...");
    }

    @Test
    void insert() {
    }

    @Test
    void search() {
        System.out.println(bst.search("Lily"));
    }

//    @Test
//    void findMin() {
//        String min = bst.findMin();
//        assertTrue(min >= LOWER_LIMIT, "Minimum value should be within the expected range.");
//    }

    @Test
    void findMax() {
        System.out.println(bst.findMax());
    }

    @Test
    void successor() {
        System.out.println(bst.successor("Lily"));
    }

    @Test
    void predecessor() {
        String predecessor = "Quinn";
        System.out.printf("The Predecessor of %s is " + bst.predecessor(predecessor) + "\n", predecessor);
    }

    @Test
    void printBST() {
        bst.printBST(filePath);
        assertTrue(new File(filePath).exists(), "DOT file should be created.");
    }

    @Test
    void height() {
        System.out.println(bst.height());
    }

    @Test
    void delete() {
        String value = "Lynda";
        System.out.println(bst.delete(value));
        System.out.println(bst.findMax());
        System.out.println(bst.inorder());
        System.out.println(bst.postorderIterative());
        System.out.println(bst.postorder().toString().equals(bst.postorderIterative().toString()));
        System.out.println("Recursive " + bst.inorder());
        System.out.println("Iterative " + bst.inorderIterative());
        System.out.println("Level Order Traversal " + bst.levelOrderTraversal());
        System.out.println("Morris Inorder "+ bst.morrisInorder());
        System.out.println(bst.preorder());
        System.out.println("Morris Postorder "+ bst.postorder());
        System.out.println("Morris Postorder "+ bst.morrisPostorder());
    }
}

