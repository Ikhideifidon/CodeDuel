package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class ConnectedComponentTest {
    private static final String filePath = "src/main/resources/tinyG.txt";
    private static final Graph G = new Graph(filePath);
    private static final Random random = new Random();
    private ConnectedComponent cc;

    @BeforeEach
    void setUp() {
        cc = new ConnectedComponent(G);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void identifier() {
        int v = random.nextInt(G.V());
        System.out.println(cc.identifier(v));
    }

    @Test
    void connected() {
        int v = random.nextInt(G.V());
        System.out.println("First Vertex " + v);
        int u = random.nextInt(G.V());
        while (u == v)
            u = random.nextInt(G.V());
        System.out.println("Second Vertex " + u);
        System.out.println("Are these vertices connected: " + cc.connected(v, u));
    }

    @Test
    void numberOfConnectedComponents() {
        System.out.println("Number of Connected Components is " + cc.numberOfConnectedComponents());
    }
}
