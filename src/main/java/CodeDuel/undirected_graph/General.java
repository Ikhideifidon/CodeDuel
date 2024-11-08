package CodeDuel.undirected_graph;

import java.util.*;

@SuppressWarnings({"unchecked", "unused"})
public class General {
    /*
    Given an adjacency list Graph representation, find the largest component count
     */
    public static int largestComponentCount(int[][] edges) {
        if (edges == null || edges.length == 0)
            return 0;

        Map<Integer, List<Integer>> graph = convertToGraphMap(edges);

        int maxCount = 0;
        Set<Integer> marked = new HashSet<>();
        for (int vertex : graph.keySet()) {
            if (!marked.contains(vertex)) {
                int count = dfs(graph, vertex, marked);
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    private static int dfs(Map<Integer, List<Integer>> graph, int source, Set<Integer> marked) {
        marked.add(source);
        int result = 1;
        if (graph.get(source) != null) {
            for (int neighbor : graph.get(source)) {
                if (!marked.contains(neighbor))
                    result += dfs(graph, neighbor, marked);
            }
        }
        return result;
    }

    // Hash Map-based Adjacency List
    private static Map<Integer, List<Integer>> convertToGraphMap(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int v = edge[0];
            int u = edge[1];
            // Add edge from v to u
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
            // Add edge from u to v
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        return graph;
    }

    // Array-based Adjacency List
    private static List<Integer>[] convertToGraphAdjacencyList(int[][] edges) {
        // Step 1: Count unique vertices
        Set<Integer> vertices = new HashSet<>();
        for (int[] edge : edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }

        // Step 2: Initialize the adjacency list
        int V = vertices.size();
        List<Integer>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        // Step 3: Map vertex indices to original vertex values
        Map<Integer, Integer> vertexIndexMap = new HashMap<>();
        int index = 0;
        for (int vertex : vertices)
            vertexIndexMap.put(vertex, index++);

        // Step 4: Populate adjacency list using edges
        for (int[] edge : edges) {
            int v = vertexIndexMap.get(edge[0]);
            int u = vertexIndexMap.get(edge[1]);

            graph[v].add(u);
            graph[u].add(v); // Since it’s an undirected graph
        }

        return graph;
    }

    // 684. Redundant Connection
//    public int[] findRedundantConnection(int[][] edges) {
//        /*
//        STEPS:
//        1. Convert edges to array-based adjacent list
//        2. Initialize maxPQ
//        3. Iterate through the graph and check if a cycle exists
//        4. If it exists, return maxPQ.poll()
//         */
//        int n = edges.length;
//        List<Integer>[] graph = convertToGraph(edges);
//        return hasCycle(graph, )
//    }

//    private int[] hasCycle(List<Integer>[] graph, int source, int target, boolean[] marked, int parent) {
//        marked[source] = true;
//        for (int neighbor : graph[source]) {
//            if (!marked[neighbor]) {
//                if (hasCycle(graph, neighbor, target, marked, source))
//                    ret
//                else
//                    maxPQ.offer(new int[]{v, source});
//                dfs(graph, v, source, marked, maxPQ);
//            } else if (v != from) {
//                if (v > source)
//                    maxPQ.offer(new int[]{source, v});
//                else
//                    maxPQ.offer(new int[]{v, source});
//                return maxPQ.poll();
//            }
//        }
//        return new int[] {};
//    }

    private List<Integer>[] convertToGraph(int[][] edges) {
        int n = edges.length;
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int[] edge : edges) {
            int v = edge[0];
            int u = edge[1];

            if (graph[v] == null) graph[v] = new ArrayList<>();
            if (graph[u] == null) graph[u] = new ArrayList<>();

            graph[v].add(u);
            graph[u].add(v);
        }
        return graph;
    }

    public static int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];

            if (graph.containsKey(x) && hasPath(graph, x, y, -1))
                return edge;

            graph.computeIfAbsent(x, k -> new HashSet<>()).add(y);
            graph.computeIfAbsent(y, k -> new HashSet<>()).add(x);
        }
        return new int[] {-1, -1};
    }

    private static boolean hasPath(Map<Integer, Set<Integer>> graph, int source, int target, int parent) {
        if (graph.get(source).contains(target))
            return true;
        for (int vertex : graph.get(source)) {
            // Avoid revisiting
            if (vertex == parent)
                continue;
            if (hasPath(graph, vertex, target, source))
                return true;
        }
        return false;
    }
}
