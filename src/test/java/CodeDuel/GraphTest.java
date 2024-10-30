package CodeDuel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GraphTest {
    private final static String path = "src/main/resources/mediumG.txt";
    private Graph G;

    @BeforeEach
    void setUp() { G = new Graph(path); }

    @AfterEach
    void tearDown() {
    }

    @Test
    void v() {
        Assertions.assertEquals(250, G.V());
    }

    @Test
    void e() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void neighbors() {
    }
}
