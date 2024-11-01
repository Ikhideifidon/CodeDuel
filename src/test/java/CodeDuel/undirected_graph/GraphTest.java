package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GraphTest {
    private final static String path = "src/main/resources/mediumG.txt";
    private final static String outputPath = "src/main/resources/graph_output.txt";
    private Graph G;

    @BeforeEach
    void setUp() {
        G = new Graph(path);
        G.redirectOutputToFile(outputPath);
        System.out.println("Graph Information:");
        System.out.println("Number of vertices: " + G.V());
        System.out.println("Number of edges: " + G.E());
    }


    @AfterEach
    void tearDown() {}

    @Test
    void v() {
        Assertions.assertEquals(250, G.V());
    }

    @Test
    void e() {
        int edge = 2546;
        Assertions.assertEquals(edge, G.E());
    }

    @Test
    void addEdge() {
    }

    @Test
    void neighbors() {
        int vertex = 248;
        List<Integer> neighbors = new ArrayList<>(List.of(232, 231, 201, 187, 185, 168, 144, 104, 97, 93, 59, 50, 49, 48, 44, 32));
        List<Integer> adjacent = (List<Integer>) G.neighbors(vertex);
        Assertions.assertEquals(neighbors, adjacent);
    }


    @Test
    void degree() {
        int neighbors = 21;
        int vertex = 0;
        List<Integer> adjacent = (List<Integer>) G.neighbors(vertex);
        Assertions.assertEquals(neighbors, adjacent.size());
    }

    @Test
    void maxDegree() {
        int maximumDegree = 21;
        Assertions.assertEquals(maximumDegree, G.maxDegree());
    }

    @Test
    void aveDegree() {
        int averageDegree = 20;
        Assertions.assertEquals(averageDegree, G.aveDegree());
    }

    @Test
    void writeToFile() {
        System.out.println(G);
    }
}
