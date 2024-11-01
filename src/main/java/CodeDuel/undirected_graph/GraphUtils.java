package CodeDuel.undirected_graph;

public interface GraphUtils {
    int V();
    int E();
    void addEdge(int v, int w);
    int degree(int v);
    int maxDegree();
    int aveDegree();
    Iterable<Integer> neighbors(int v);
}
