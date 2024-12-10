package CodeDuel.directed_graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DirectedCycle {
    private final Digraph G;
    private final boolean[] marked;
    private final int[] edgeTo;
    private final boolean[] onStack;
    private Deque<Integer> cycle;

    public DirectedCycle(Digraph G) {
        this.G = G;
        marked = new boolean[this.G.V()];
        edgeTo = new int[this.G.V()];
        onStack = new boolean[this.G.V()];
        for (int v = 0; v < this.G.V(); v++) {
           if (!marked[v])
               dfs(v);
        }
    }

    private void dfs(int source) {
        marked[source] = true;
        onStack[source] = true;
        for (int v : this.G.neighbors(source)) {
            if (this.hasCycle())
                return;
            else if (!marked[v]) {
                edgeTo[v] = source;
                dfs(v);
            }
            else if (onStack[v]) {
                cycle = new ArrayDeque<>();
                for (int x = source; x != v; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(v);
                cycle.push(source);
            }
        }
        onStack[source] = false;
    }

    public boolean hasCycle() { return cycle != null; }

    public Iterable<Integer> cycle() { return cycle; }


}
