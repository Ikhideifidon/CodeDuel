package CodeDuel.undirected_graph;

public class Cycle {
    private final Graph G;
    private final boolean[] marked;
    private boolean hasCycle = false;
    private int countCycles = 0;

    public Cycle(Graph G) {
        this.G = G;
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(s, s, false);
        }
    }

    private void dfs(int source, int parent, boolean cycleBound) {
        marked[source] = true;
        for (int neighbor : G.neighbors(source)) {
            if (!marked[neighbor])
                dfs(neighbor, source, false);
            else if (neighbor != parent) {
                hasCycle = true;
                if (!cycleBound) {
                    countCycles++;
                    cycleBound = true;
                }
            }
        }
    }

    public boolean hasCycle() { return hasCycle; }

    public int countCycles() { return countCycles; }

    // Number of cycles in a cyclic undirected graph
    // Largest cycle (in terms of edge count) in a cyclic graph.
}
