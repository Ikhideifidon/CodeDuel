package CodeDuel.undirected_graph;

import org.junit.jupiter.api.Test;


class SymbolGraphTest {
    private static final String filePath = "src/main/resources/routes.txt";
    private final SymbolGraph symbolGraph = new SymbolGraph(filePath);

    @Test
    void contains() {
        System.out.println(symbolGraph.contains("JFP"));
        System.out.println(symbolGraph);

    }

    @Test
    void index() {
        System.out.println(symbolGraph.index("MCO"));
    }

    @Test
    void name() {
        System.out.println(symbolGraph.name(5));
    }

}
