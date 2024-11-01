package CodeDuel.undirected_graph;

public class DepthFirstSearch {
    private final Graph G;
    private final boolean[] marked;
    private int connectedVertexCount;

    public DepthFirstSearch(Graph G, int source) {
        this.G = G;
        marked = new boolean[this.G.V()];
        connectedVertexCount = 0;
        dfs(source);
    }

    private void dfs(int source) {
        this.marked[source] = true;
        this.connectedVertexCount++;
        for (int i : G.neighbors(source)) {
            if (!marked[i])
                dfs(i);
        }
    }

    public boolean isConnected(int v) { return marked[v]; }

    public int count() { return this.connectedVertexCount; }




}
