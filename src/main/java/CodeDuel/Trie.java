package CodeDuel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*
    A Flexible Trie class that handles both the English lowercase and uppercase letters.
 */
public class Trie implements TrieUtils {
    private static final int R = 52;
    private final TrieNode root;
    private int size = 0;

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

    @Override
    public void insert(@NotNull String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = getIndex(c);
            if (current.children[index] == null)
                current.children[index] = new TrieNode();

            current = current.children[index];
        }

        if (!current.isEndOfWord) {
            current.isEndOfWord = true;
            size++;
        }
    }

    @Override
    public boolean search(@NotNull String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = getIndex(c);
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    @Override
    public boolean startsWith(@NotNull String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = getIndex(c);
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String longestPrefixOf(@NotNull String word) {
        TrieNode node = root;
        int lastPrefixLength = 0;
        int runningIndex = 0;
        for (char c : word.toCharArray()) {
            int index = getIndex(c);

            // Is there a trie rooted at this index?
            if (node.children[index] == null)
                break;

            node = node.children[index];
            runningIndex++;

            if (node.isEndOfWord)
                lastPrefixLength = runningIndex;
        }
        return word.substring(0, lastPrefixLength);
    }

    @Override
    public String longestCommonPrefix() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public Iterable<String> allPrefixOf(@NotNull String word) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        int runningIndex = 0;
        for (char c : word.toCharArray()) {
            int index = getIndex(c);

            // Is there a trie rooted at this index?
            if (node.children[index] == null)
                break;

            node = node.children[index];

            runningIndex++;
            if (node.isEndOfWord)
                result.add(word.substring(0, runningIndex));
        }
        return result;
    }

    private int getIndex(char c) {
        if (c >= 'a' && c <= 'z')
            return c - 'a';
        else if (c >= 'A' && c <= 'Z')
            return c - 'A' + 26;
        else
            throw new IllegalArgumentException("Character out of range for English Alphabets.");
    }

    public void printTrie() {
        printTrieHelper(root, new StringBuilder());
    }

    // Helper method to recursively print the Trie
    private void printTrieHelper(TrieNode node, StringBuilder prefix) {
        if (node == null) return;

        // If this node is the end of a word, print the prefix
        if (node.isEndOfWord)
            System.out.println(prefix.toString());

        // Recursively traverse all child nodes
        for (int i = 0; i < 52; i++) {
            if (node.children[i] != null) {
                char nextChar = (i < 26) ? (char) ('a' + i) : (char) ('A' + (i - 26));
                prefix.append(nextChar); // Add the character to the prefix
                printTrieHelper(node.children[i], prefix);
                prefix.deleteCharAt(prefix.length() - 1); // Remove the character after recursion
            }
        }
    }
}
