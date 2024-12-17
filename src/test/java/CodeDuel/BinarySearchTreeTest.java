package CodeDuel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {
    private static final String filePath = "src/main/resources/tree.dot";
    private final static int LOWER_LIMIT = 21;
    private final static int UPPER_LIMIT = 100;
    private static final BinarySearchTree bst  = new BinarySearchTree();
    private static final Random random = new Random();

    @BeforeAll
    static void initializeTree() {
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i <= UPPER_LIMIT - LOWER_LIMIT; i++) {
            int val = random.nextInt(UPPER_LIMIT - LOWER_LIMIT + 1) + LOWER_LIMIT;
            if (!unique.contains(val)) {
                unique.add(val);
                bst.insert(val);
            }
        }
        System.out.println("Setting up test environment...");
    }

    @Test
    void insert() {
    }

    @Test
    void search() {
    }

    @Test
    void findMin() {
        int min = bst.findMin();
        assertTrue(min >= LOWER_LIMIT, "Minimum value should be within the expected range.");
    }

    @Test
    void findMax() {
        System.out.println(bst.findMax());
    }

    @Test
    void successor() {
    }

    @Test
    void printBST() {
        bst.printBST(filePath);
        assertTrue(new File(filePath).exists(), "DOT file should be created.");
    }
}

