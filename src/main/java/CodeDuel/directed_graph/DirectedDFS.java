package CodeDuel.directed_graph;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class DirectedDFS {
    private final Digraph G;
    private final boolean[] marked;

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        this.G = G;
        marked = new boolean[G.V()];
        for (int i : sources) {
            if (!marked[i])
                dfs(i);
        }
    }

    public DirectedDFS(Digraph G, int source) {
        this(G, new ArrayList<>(List.of(source)));
    }

    private void dfs(int source) {
        marked[source] = true;
        for (int v : G.neighbors(source)) {
            if (!marked[v])
                dfs(v);
        }
    }

    public boolean marked(int v) { return marked[v]; }
}
