package CodeDuel.directed_graph;

import org.junit.jupiter.api.Test;

class DirectedCycleTest {
    private static final String filePath = "src/main/resources/tinyDG.txt";
    private static final Digraph G = new Digraph(filePath);
    private final DirectedCycle directedCycle = new DirectedCycle(G);

    @Test
    void hasCycle() {
        System.out.println(directedCycle.hasCycle());
    }

    @Test
    void cycle() {
        System.out.println(directedCycle.cycle());
    }
}
