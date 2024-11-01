package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class PathsTest {
    private final static String filePath = "src/main/resources/tinyG.txt";
    private final Graph G = new Graph(filePath);
    private final Random random = new Random();
    private final int source = 0;
    private Paths paths;

    @BeforeEach
    void setUp() {
        paths = new Paths(G, source);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hasPathTo() {
        int to = random.nextInt(G.V());
        while (to == source)
            to = random.nextInt(G.V());
        if (paths.hasPathTo(to)) {
            System.out.println("There exists at least a path to the vertex: " + to + " from the source: " + source);
            System.out.println(paths.pathTo(to));
        }
        else
            System.out.println("There is no path to the vertex: " + to);
    }

    @Test
    void pathTo() {
    }
}
