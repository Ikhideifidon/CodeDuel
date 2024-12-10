package CodeDuel.directed_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DigraphTest {
    private static final String filePath = "src/main/resources/tinyDG.txt";
    private static final Digraph D = new Digraph(filePath);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void v() {
        System.out.println(D.V());
    }

    @Test
    void e() {
        System.out.println(D.E());
    }

    @Test
    void addEdge() {
    }

    @Test
    void inDegree() {
        System.out.println(D.inDegree(9));
    }

    @Test
    void outDegree() {
    }

    @Test
    void maxInDegree() {
        System.out.println(D.maxInDegree());
    }

    @Test
    void maxOutDegree() {
    }

    @Test
    void aveInDegree() {
    }

    @Test
    void aveOutDegree() {
    }

    @Test
    void neighbors() {
    }

    @Test
    void reverse() {
        Digraph R = D.reverse();
        System.out.println(R);
    }

    @Test
    void degree() {
    }

    @Test
    void maxDegree() {
    }

    @Test
    void aveDegree() {
    }

    @Test
    void testToString() {
    }
}
