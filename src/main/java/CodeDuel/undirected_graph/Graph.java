package CodeDuel.undirected_graph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

        if (path == null)
            throw new NullPointerException("Path must be nonnull.");
        File file = new File(path);
        // Validate the file path and properties
        if (!file.exists() || file.isDirectory() || file.isHidden() || file.length() == 0 || !file.canRead())
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
    public int degree(int v) { return neighbors[v].size(); }

    @Override
    public int maxDegree() {
        int maximumDegree = 0;
        for (int i = 0; i < V; i++)
            maximumDegree = Math.max(maximumDegree, degree(i));
        return maximumDegree;
    }

    @Override
    public int aveDegree() { return 2 * E / V; }

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

    public void writeToFile(String filePath) {
        /*
        STEPS:
        1. Check if the file exits
            a. If it exists, check if the old write content is the same with the new content to write
            b. If it is, just return it
        2. If the file does not exist, create it and write content.
        */

        File file = new File(filePath);
        String currentContent = this.toString();

        try {
            if (file.exists()) {
                String existingContent = Files.readString(Path.of(filePath));
                if (existingContent.equals(currentContent))
                    return;
            } else {
                boolean createFile = file.createNewFile();
            }

            // Write the current content to the file.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(currentContent);
            }
        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }

    public void redirectOutputToFile(String filePath) {
        try {
            File file = new File(filePath);

            // Create the file (create directory too) if it does not exist
            if (!file.exists()) {
                boolean createDirectory = file.getParentFile().mkdirs(); // Create parent directories if needed
                boolean createFile = file.createNewFile();
            }

            // Redirect System.out to the file
            PrintStream fileOut = new PrintStream(new FileOutputStream(file));
            System.setOut(fileOut);
        } catch (IOException e) {
            System.err.println("Error handling file: " + filePath + ", " + e.getMessage());
        }
    }
}
