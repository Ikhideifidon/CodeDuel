package CodeDuel.undirected_graph;

public class ConnectedComponent {
    private final Graph G;
    private final boolean[] marked;
    private final int[] identifier;             // Which component does it belong?
    private int count;

    public ConnectedComponent(Graph G) {
        this.G = G;
        marked = new boolean[G.V()];
        identifier = new int[G.V()];
        count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(v);
                count++;
            }
        }
    }

    private void dfs(int source) {
        this.marked[source] = true;
        identifier[source] = count;
        for (int u : G.neighbors(source)) {
            if (!marked[u])
                dfs(u);
        }
    }
    public int identifier(int v) { return identifier[v]; }

    public boolean connected(int v, int u) { return identifier[v] == identifier[u]; }

    public int numberOfConnectedComponents() { return count; }


}
