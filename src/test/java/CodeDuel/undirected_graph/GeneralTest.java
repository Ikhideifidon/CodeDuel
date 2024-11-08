package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GeneralTest {
    private static final int[][] edges = {
            {0, 5},
            {5, 8},
            {0, 8},
            {1, 0},
            {4, 2},
            {3, 2},
            {3, 4}
    };

    @BeforeEach
    void setUp() {
        General.largestComponentCount(edges);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void largestComponentCount() {
        int count = 4;
        Assertions.assertEquals(count, General.largestComponentCount(edges));
    }

    @Test
    void findRedundantConnection() {
        final int[][] edges = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 4},
                {1, 5}
        };
        System.out.println(Arrays.toString(General.findRedundantConnection(edges)));
    }
}
