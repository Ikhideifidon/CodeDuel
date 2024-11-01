package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class BreadthFirstPathsTest {
    private static final String filePath = "src/main/resources/tinyG.txt";
    private final Graph G = new Graph(filePath);
    private static final int source = 0;
    private static final Random random = new Random();
    private BreadthFirstPaths breadthFirstPaths;


    @BeforeEach
    void setUp() {
        breadthFirstPaths = new BreadthFirstPaths(G, source);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hasPathTo() {
        int to = random.nextInt(G.V());
        if (breadthFirstPaths.hasPathTo(to)) {
            System.out.println("There exists at least a path from " + source + " to " + to);
            System.out.println("Path is " + breadthFirstPaths.pathTo(to));
        } else
            System.out.println("There is no path from " + source + " to " + to);
    }

    @Test
    void pathTo() {
    }
}
