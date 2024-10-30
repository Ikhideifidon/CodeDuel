package CodeDuel;

@SuppressWarnings("unused")
public interface GraphUtils {
    int V();
    int E();
    void addEdge(int v, int w);
    Iterable<Integer> neighbors(int v);
}
