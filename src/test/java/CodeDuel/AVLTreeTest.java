package CodeDuel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class AVLTreeTest {
    private static final String filePath = "src/main/resources/avl_tree.dot";
    private static final int LOWER_BOUND = 21;
    private static final int UPPER_BOUND = 100;
    private static AVLTree<Integer> tree;

    @BeforeAll
    public static void initialize() {
        Random random = new Random();
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
    }

    @Test
    void delete() {
    }

    @Test
    void size() {
    }

    @Test
    void testToString() {
        System.out.println(tree);
    }

    @Test
    void printTree() {
        tree.printTree(filePath);
    }
}
