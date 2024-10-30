package CodeDuel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings({"unused", "unchecked"})
public class Graph implements GraphUtils {
    private int V;
    private int E;
    private List<Integer>[] neighbors;

    public Graph(int V) {
        this.V = V;
        neighbors = new ArrayList[V];
        for (int i = 0; i < V; i++)
            neighbors[i] = new ArrayList<>();
    }

    public Graph(String path) {
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
           Extract E
           Iterate over the remainder and for each iter, get the edge and addEdge appropriately.
        */

        File file = new File(path);
        // Validate the file path and properties
        if (!file.exists() || file.isDirectory() || file.isHidden() || file.length() == 0 || file.canRead())
            throw new IllegalArgumentException("Invalid file path or properties.");

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextInt()) {
                this.V = scanner.nextInt();
                neighbors = new ArrayList[V];
                for (int i = 0; i < V; i++)
                    neighbors[i] = new ArrayList<>();
            } else
                throw new IllegalArgumentException("File format incorrect: expected number of vertices.");

            if (scanner.hasNextInt())
                E = scanner.nextInt();
            else
                throw new IllegalArgumentException("File format incorrect: expected number of edges.");

            // Read edges and add them to the graph
            for (int i = 0; i < E; i++) {
                if (scanner.hasNextInt()) {
                    int v = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        int w = scanner.nextInt();
                        addEdge(v, w);
                    } else
                        throw new IllegalArgumentException("File format incorrect: expected second vertex for edge.");
                }
            }
        }

        catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
    }

    @Override
    public int V() { return V; }

    @Override
    public int E() { return E; }

    @Override
    public void addEdge(int v, int w) {
        neighbors[v].add(w);
        neighbors[w].add(v);
        E++;
    }

    @Override
    public Iterable<Integer> neighbors(int v) { return neighbors[v]; }

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
}
