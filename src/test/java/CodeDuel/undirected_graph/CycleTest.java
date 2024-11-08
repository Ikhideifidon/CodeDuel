package CodeDuel.undirected_graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CycleTest {
    private static final String filePath = "src/main/resources/cycleG.txt";
    private static final Graph G = new Graph(filePath);
    private Cycle cycle;

    @BeforeEach
    void setUp() {
        cycle = new Cycle(G);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hasCycle() {
        System.out.println(cycle.hasCycle());
    }

    @Test
    void countCycles() {
        System.out.println(cycle.countCycles());
    }
}
