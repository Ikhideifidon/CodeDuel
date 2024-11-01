package CodeDuel.undirected_graph;

import java.util.*;

public class BreadthFirstPaths {
    private final Graph G;
    private final int source;
    private final int[] from;
    private final boolean[] marked;

    public BreadthFirstPaths(Graph G, int source) {
        this.G = G;
        this.source = source;
        from = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(source);
    }

    private void bfs(int source) {
        this.marked[source] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : G.neighbors(v)) {
                if (!marked[u]) {
                    from[u] = v;
                    marked[u] = true;
                    queue.offer(u);
                }
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
