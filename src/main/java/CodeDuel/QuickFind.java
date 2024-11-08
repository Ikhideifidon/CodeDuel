package CodeDuel;

import java.util.Arrays;

/*
    Various Union Find Implementations
 */
@SuppressWarnings("unused")
public class QuickFind {
    private final int[] id;
    private int count;

    public QuickFind(int N) {
        id = new int[N];
        Arrays.setAll(id, i -> i + 1);
        count = 0;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId)
            return;                     // Do nothing
        // Attempt to link them by renaming p's component to q's name.
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId)
                id[i] = qId;
        }
        count--;
    }

    public int find(int p) { return id[p]; }
    public boolean connected(int p, int q) { return find(p) == find(q); }
    public int count() { return count; }
}

@SuppressWarnings("unused")
class QuickUnion {
    private final int[] id;
    private int count;

    public QuickUnion(int N) {
        id = new int[N];
        Arrays.setAll(id, i -> i + 1);
        count = 0;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;                     // Do nothing
        // Attempt to link them by renaming p's component to q's name.
        id[pRoot] = qRoot;
        count--;
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];

        return p;
    }
}


@SuppressWarnings("unused")
class WeightedQuickUnion {
    private final int[] id;
    private final int[] size;
    private int count;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        size = new int[N];
        Arrays.setAll(id, i -> i + 1);
        Arrays.fill(size, 1);
        count = 0;
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }
}

@SuppressWarnings("unused")
class WeightedQuickUnionWithPathCompression {
    private final int[] id;
    private final int[] size;
    private int count;

    public WeightedQuickUnionWithPathCompression(int N) {
        id = new int[N];
        size = new int[N];
        Arrays.setAll(id, i -> i + 1);
        Arrays.fill(size, 1);
        count = 0;
    }

    public int find(int p) {
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

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }
}
