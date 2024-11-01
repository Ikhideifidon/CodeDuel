package CodeDuel.undirected_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paths {
    private final Graph G;
    private final int source;
    private final int[] from;
    private final boolean[] marked;

    public Paths(Graph G, int source) {
        this.G = G;
        this.source = source;
        this.marked = new boolean[G.V()];
        from = new int[G.V()];
        dfs(source);
    }

    private void dfs(int source) {
        marked[source] = true;
        for (int v : G.neighbors(source)) {
            if (!marked[v]) {
                from[v] = source;
                dfs(v);
            }
        }
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        // There is a path.
        List<Integer> path = new ArrayList<>();
        for (int i = v; i != source; i = from[i])
            path.add(i);
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
