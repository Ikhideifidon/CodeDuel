package CodeDuel.undirected_graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*
    Using Union Find
 */

public class SymbolGraph {

    // Union Find class
    @SuppressWarnings("unused")
    private static class UnionFind {
        private int count;
        private final int[] id;
        private final int[] size;

        private UnionFind(int N) {
            count = N;
            id = new int[N];
            size = new int[N];
            Arrays.setAll(id, i -> i);
            Arrays.fill(size, 1);
        }

        private void union(int p, int q) {
            int i = find(p);
            int j = find(q);

            if (i == j)
                return;

            if (size[i] > size[j]) {
                size[i] += size[j];
                id[j] = i;
            } else {
                size[j] += size[i];
                id[i] = j;
            }
            this.count--;
        }

        private int find(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];

            // Path Compression
            while (p != root) {
                int next = id[p];
                id[p] = root;
                p = next;
            }
            return root;
        }

        private int count() { return this.count; }
    }


//  private UnionFind unionFind;
    private int N;
    private String[] keys;
    private final Map<String, Integer> keyToVertex = new HashMap<>();
    private Graph G;

    public SymbolGraph(String fileName, String delimiter) {
        if (fileName == null)
            throw new IllegalArgumentException("'filePath' cannot be null.");
        /*
            STEPS:
            1.  Check if fileName is null
            2.  Check if file exists
            3.  Check if file is hidden
            4.  Check if file can be read
            5.  Check if file is empty

         */
        File file = new File(fileName);
        if (!file.exists() || file.isHidden() || !file.canRead() || file.length() == 0)
            throw new IllegalArgumentException("'File Path' has some problems.");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] vertices = line.split(delimiter);
                if (vertices.length < 2)
                    throw new IllegalArgumentException("File format incorrect: expected second vertex for edge.");

                String key1 = vertices[0];
                String key2 = vertices[1];

                if (!keyToVertex.containsKey(key1))
                    keyToVertex.put(key1, keyToVertex.size());

                if (!keyToVertex.containsKey(key2))
                    keyToVertex.put(key2, keyToVertex.size());
            }

            this.N = keyToVertex.size();
            keys = new String[this.N];

            for (String name : keyToVertex.keySet())
                keys[keyToVertex.get(name)] = name;

            G = new Graph(this.N);
//          unionFind = new UnionFind(N);


            try (Scanner scan = new Scanner(file)) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] vertices = line.split(delimiter);
                    int v = keyToVertex.get(vertices[0]);
                    int u = keyToVertex.get(vertices[1]);

                    G.addEdge(v, u);
//                  unionFind.union(v, u);
                }
            }
        } catch (IOException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        }
    }

    public SymbolGraph(String fileName) { this(fileName, " "); }

    public int index(String key) {
        if (keyToVertex.containsKey(key))
            return keyToVertex.get(key);
        throw new IllegalArgumentException(String.format("'%s' does not exist", key));
    }

    public boolean contains(String key) { return this.keyToVertex.get(key) != null; }
    public String name(int v){ return keys[v]; }

    public String toString() {
        /*
        [JFK] --> [ORD]-->[ATL]-->[MCO]
        [ORD] --> [JFK]
        [PHX]
         */
        if (N == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = keys[i];
            sb.append("[").append(s).append("]");
            int size = G.degree(i);
            if (size != 0)
                sb.append(" -->");

            List<Integer> neighbors = (List<Integer>) G.neighbors(i);
            for (int j = 0; j < size; j++) {
                int neighbor = neighbors.get(j);
                sb.append("[").append(this.name(neighbor)).append("]");
                if (j + 1 != size)
                    sb.append("-->");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
