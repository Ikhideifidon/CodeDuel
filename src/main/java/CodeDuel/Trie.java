package CodeDuel;

/*
    A Flexible CodeDuel.Trie class that handles both the English lowercase letters and uppercase letters.
 */
public class Trie {
    private static final int R = 52;
    private final TrieNode root;

    // Create a lightweight inner class
    private static class TrieNode {
        private boolean isEndOfWord;
        private final TrieNode[] children;

        private TrieNode() {
            this.isEndOfWord = false;
            this.children = new TrieNode[R];
        }
    }

    public Trie() { this.root = new TrieNode(); }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = getIndex(c);
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
                current.isEndOfWord = false;
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        if (word == null)
            return false;
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = getIndex(c);
            if (node.children[index] == null)
                return false;

            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = getIndex(c);
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return true;
    }

    private int getIndex(char c) {
        if (c >= 'a' && c <= 'z')
            return c - 'a';
        else if (c >= 'A' && c <= 'Z')
            return c - 'A' + 26;
        else
            throw new IllegalArgumentException("Character out of range for English Alphabets.");
    }
}
