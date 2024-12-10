package CodeDuel.directed_graph;

import CodeDuel.GraphUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Digraph implements GraphUtils {
    private final int V;
    private int E;
    private final List<Integer>[] neighbors;

    @SuppressWarnings("unchecked")
    public Digraph(int N) {
        V = N;
        neighbors = new ArrayList[V];
        for (int i = 0; i < V; i++)
            neighbors[i] = new ArrayList<>();
        E = 0;
    }

    public Digraph(String filePath) {
        this(getVertexCount(filePath));

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextInt();

            int edges;
            if (scanner.hasNext())
                edges = scanner.nextInt();
            else
                throw new IllegalArgumentException("File format incorrect: expected number of edges.");

            for (int i = 0; i < edges; i++) {
                int v, w;
                if (scanner.hasNext()) {
                    v = scanner.nextInt();
                    if (scanner.hasNext()) {
                        w = scanner.nextInt();
                        addEdge(v, w);
                    } else
                        throw new IllegalArgumentException("File format incorrect: expected second vertex for edge.");
                }
            }
        } catch (IOException e) {
            System.err.println("File not found " + new File(filePath).getAbsolutePath());
        }
    }

    @Override
    public int V() { return V; }

    @Override
    public int E() { return E; }

    @Override
    public void addEdge(int v, int w) {
        neighbors[v].add(w);
        E++;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        for (int i = 0; i < V; i++) {
            for (int j : neighbors[i]) {
                if (j == v)
                    count++;
            }
        }
        return count;
    }

    @Override
    public int outDegree(int v) { return neighbors[v].size(); }

    @Override
    public int maxInDegree() {
        int max = 0;
        for (int i = 0; i < V; i++)
            max = Math.max(max, inDegree(i));
        return max;
    }

    @Override
    public int maxOutDegree() {
        int max = 0;
        for (int i = 0; i < V; i++)
            max = Math.max(max, neighbors[i].size());
        return max;
    }

    @Override
    public int aveInDegree() { return this.E / this.V; }

    @Override
    public int aveOutDegree() { return aveInDegree(); }

    @Override
    public Iterable<Integer> neighbors(int v) { return neighbors[v]; }

    @Override
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int u : neighbors[v])
                R.addEdge(u, v);
        }
        return R;
    }

    // Unsupported Operations
    @Override
    public int degree(int v) {
        throw new UnsupportedOperationException("This operation is not supported by this graph");
    }

    @Override
    public int maxDegree() {
        throw new UnsupportedOperationException("This operation is not supported by this graph");
    }

    @Override
    public int aveDegree() {
        throw new UnsupportedOperationException("This operation is not supported by this graph");
    }

    @Override
    public String toString() {
        /*
        [0] --> [1]-->[2]-->[3]
        [1] --> [0]-->[4]
        [2]
         */

        if (this.V == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            sb.append("[").append(i).append("]");
            int size = neighbors[i].size();
            if (size != 0)
                sb.append(" -->");

            for (int j = 0; j < size; j++) {
                int edge = neighbors[i].get(j);
                sb.append("[").append(edge).append("]");
                if (j + 1 != size)
                    sb.append("-->");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Helper method to extract vertex count before chaining
    private static int getVertexCount(String path) {
        /*
            V
            E
            2 1
            3 1
            0 2

         Steps
           Check the validity of this file path
           Check if the file present in this path is not empty
           Check if it is readable
           Extract V
        */

        if (path == null)
            throw new NullPointerException("Path must be nonnull.");

        File file = new File(path);
        // Validate the file path and properties
        if (!file.exists() || file.isDirectory() || file.isHidden() || file.length() == 0 || !file.canRead())
            throw new IllegalArgumentException("Invalid file path or properties.");

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            else
                throw new IllegalArgumentException("File format incorrect: expected number of vertices.");
        } catch (IOException e) {
            throw new RuntimeException("File not found: " + path, e);
        }
    }
}
