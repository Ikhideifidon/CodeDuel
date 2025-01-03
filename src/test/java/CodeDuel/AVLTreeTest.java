package CodeDuel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class AVLTreeTest {
    private static final String filePath = "src/main/resources/avl_tree.dot";
    private static final int LOWER_BOUND = 21;
    private static final int UPPER_BOUND = 50;
    private static final Random random = new Random(0);
    private static AVLTree<Integer> tree;

    @BeforeAll
    public static void initialize() {
        final Set<Integer> unique = new HashSet<>();
        Comparator<Integer> comparator = Integer::compareTo;
        tree = new AVLTree<>(comparator);
        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            int index = random.nextInt(LOWER_BOUND, UPPER_BOUND + 1);
            if (!unique.contains(index)) {
                tree.insert(index);
                unique.add(index);
            }
        }
    }

    @Test
    void insert() {
    }

    @Test
    void search() {
        int value = 42;
        tree.insert(value);
        assertTrue(tree.search(value), String.format("The tree should find the inserted value of %d", value));
        assertFalse(tree.search(UPPER_BOUND + 2), String.format("The tree should not find a value of %d that was not inserted", UPPER_BOUND + 2));
    }

    @Test
    void delete() {
        System.out.println(tree.delete(26));
        tree.printTree(filePath);
    }

    @Test
    void size() {
        int initialSize = tree.size();
        tree.insert(55);
        tree.insert(65);
        assertEquals(initialSize + 2, tree.size(), "The size should increase by 2 after inserting two elements.");
    }

    @Test
    void testToString() {
        System.out.println(tree);
    }

    @Test
    void printTree() {
        tree.printTree(filePath);
        System.out.println(tree.height());
    }

    @Test
    void kthSmallest() {
        int k = 3;
        System.out.println(tree.kthSmallest(k));
    }
    @Test
    void zigzagLevelOrder() {
        System.out.println(tree.zigzagLevelOrder());
    }

    private static int[] fisherYates() {
        int[] result = new int[UPPER_BOUND - LOWER_BOUND + 1];
        Arrays.setAll(result, i -> LOWER_BOUND + i);
        for (int j = result.length - 1, i; j > 0; j--) {
            i = random.nextInt(j + 1);
            int temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }
        return result;
    }
}
