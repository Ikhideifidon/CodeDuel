package CodeDuel;

import CodeDuel.directed_graph.Digraph;

@SuppressWarnings("unused")
public interface GraphUtils {
    int V();
    int E();
    void addEdge(int v, int w);
    int degree(int v);
    int inDegree(int v);
    int outDegree(int v);
    int maxDegree();
    int maxInDegree();
    int maxOutDegree();
    int aveDegree();
    int aveInDegree();
    int aveOutDegree();
    Digraph reverse();
    Iterable<Integer> neighbors(int v);
}
