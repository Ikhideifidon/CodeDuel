package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class DepthFirstSearchTest {
    private final static Graph G = new Graph("src/main/resources/tinyG.txt");
    private DepthFirstSearch search;
    private final Random random = new Random();
    private int source;

    @BeforeEach
    void setUp() {
        source = random.nextInt(G.V());
        search = new DepthFirstSearch(G, source);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isConnected() {
        int v = random.nextInt(G.V());
        while (v == source)
            v = random.nextInt(G.V());
        System.out.println("The value of source is: " + source);
        System.out.println("The value of v is: " + v);
        Assertions.assertFalse(search.isConnected(v));
    }

    @Test
    void count() {
        System.out.println(search.count());
    }
}
